import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

// represents the player
class Player {
  int speed;
  int size;
  Color color;
  int x;
  int y;
  int lives;

  // generate the player
  Player(int speed, Color color) {
    this.speed = speed;
    this.size = 5;
    this.color = color;
    this.x = 300; // middle of screen
    this.y = 200; // middle of screen
    this.lives = 3;
  }

  // constructor for iterating the player
  Player(int speed, int size, Color color, int x, int y, int lives) {
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.x = x;
    this.y = y;
    this.lives = lives;
  }

  // draw this Dot as CircleImage with its size and color
  WorldImage draw() {
    return new CircleImage(this.size, "solid", this.color);
  }

  // move the player up
  Player moveUp() {
    return new Player(this.speed, this.size, this.color, this.x, this.y - this.speed, this.lives);
  }

  // move the player down
  Player moveDown() {
    return new Player(this.speed, this.size, this.color, this.x, this.y + this.speed, this.lives);
  }

  // move the player right
  Player moveRight() {
    return new Player(this.speed, this.size, this.color, this.x + this.speed, this.y, this.lives);
  }

  // move the player left
  Player moveLeft() {
    return new Player(this.speed, this.size, this.color, this.x - this.speed, this.y, this.lives);
  }

  // grow the player
  Player grow() {
    return new Player(this.speed, this.size + 1, this.color, this.x, this.y, this.lives);
  }
  
  // take a life from the player
  Player takeLife() {
    return new Player(this.speed, this.size, this.color, this.x, this.y, this.lives - 1);
  }
}

// represents an enemy
class Enemy {
  int speed;
  int size;
  Color color;
  int x;
  int y;

  // randomly generate an enemy
  Enemy(int speed, Color color) {
    this.speed = speed;
    this.color = color;
    Random rand = new Random(); // randomly places the enemy on screen
    this.x = 0; //always starts the enemies on the left
    this.y = rand.nextInt(400) + 1;
    this.size = rand.nextInt(20) + 1; // gives this enemy a random size
  }

  // constructor for moving an enemy
  Enemy(int speed, int size, Color color, int x, int y) {
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.x = x;
    this.y = y;
  }

  // draw this fish as CircleImage with its size and color
  WorldImage draw() {
    return new CircleImage(this.size, "solid", this.color);
  }
}

// represents a generic list interface
interface IList<T> {
  //filter this list by the given predicate
  IList<T> filter(Predicate<T> pred);

  //ormap this list by the given predicate
  boolean ormap(Predicate<T> pred);

  //maps a function onto each member of the list, producing a list of the results
  <U> IList<U> map(Function<T, U> fun);

  //combines the items in this list using the given function
  <U> U foldr(BiFunction<T, U, U> fun, U base);
}

// represents an empty list
class MtList<T> implements IList<T> {
  MtList() {
    
  }

  //filter this list by the given predicate
  public IList<T> filter(Predicate<T> pred) {
    return this;
  }

  //ormap this list by the given predicate
  public boolean ormap(Predicate<T> pred) {
    return false;
  }

  //maps a function onto each member of the list, producing a list of the results
  public <U> IList<U> map(Function<T, U> fun) {
    // TODO Auto-generated method stub
    return new MtList<U>();
  }

  //combines the items in this list using the given function
  public <U> U foldr(BiFunction<T, U, U> fun, U base) {
    return base;
  }
}

// represents a generic list with data T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  //filter this list by the given predicate
  public IList<T> filter(Predicate<T> pred) {
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //ormap this list by the given predicate
  public boolean ormap(Predicate<T> pred) {
    return pred.test(this.first) || this.rest.ormap(pred);
  }

  //maps a function onto each member of the list, producing a list of the results
  public <U> IList<U> map(Function<T, U> fun) {
    return new ConsList<U>(fun.apply(this.first), this.rest.map(fun));
  }

  //combines the items in this list using the given function
  public <U> U foldr(BiFunction<T, U, U> fun, U base) {
    return fun.apply(this.first, this.rest.foldr(fun, base));
  }
}

// discerns if this enemy is colliding with a given player
// note that the behavior is "reversed" to work with the filter function
class Collide implements Predicate<Enemy> {
  Player player;

  Collide(Player player) {
    this.player = player;
  }

  //is the given enemy next to the player?
  public boolean test(Enemy e) {
    return !((Math.sqrt((player.x - e.x) * (player.x - e.x) + (player.y - e.y) * (player.y - e.y))) < player.size);
  } 
}

// moves an enemy --> used with map
class Move implements Function<Enemy, Enemy> {

  // moves the enemy
  public Enemy apply(Enemy x) {
    return new Enemy(x.speed, x.size, x.color, x.x + x.speed, x.y);
  }
}

// a bifunction used with foldr to discern a list of the closest enemies to the player
class Closest implements BiFunction<Enemy, IList<Enemy>, IList<Enemy>> {
  Player player;

  Closest(Player player) {
    this.player = player;
  }

  // finds the closest enemies 
  public IList<Enemy> apply(Enemy e, IList<Enemy> enemies) {
    if ((Math.sqrt((player.x - e.x) * (player.x - e.x) + (player.y - e.y) * (player.y - e.y))) < player.size) {
      return new ConsList<Enemy>(e, enemies);
    } else {
      return enemies;
    }
  }
}

// a bifunction used with foldr that assists in the placing of enemies
class MakeScene implements BiFunction<Enemy, WorldScene, WorldScene> {

  // draws the scene
  public WorldScene apply(Enemy enemy, WorldScene scene) {
    return scene.placeImageXY(enemy.draw(), enemy.x, enemy.y);
  }
}

// represents a FeedingFrenzy game
class FishWorld extends World {
  Player player;
  IList<Enemy> enemies;
  int tickCounter;

  FishWorld(Player player, IList<Enemy> enemies) {
    this.player = player;
    this.enemies = enemies;
    this.tickCounter = 0;
  }

  // draws the dots onto the background
  public WorldScene makeScene() {
    WorldScene intermediate = new WorldScene(600, 400).placeImageXY(this.player.draw(), this.player.x, this.player.y);
    return this.enemies.foldr(new MakeScene(), intermediate);
  }

  // return the end screen if the player dies
  public WorldEnd worldEnds() {
    WorldScene endLose = new WorldScene(600, 400).placeImageXY(new TextImage("GAME OVER - YOU DIED", 20, Color.BLACK), 300, 150);
    WorldScene endWin = new WorldScene(600, 400).placeImageXY(new TextImage("YOU WON - SIZE 20 REACHED", 20, Color.BLACK), 300, 150);
    
    if (player.lives == 0) {
      return new WorldEnd(true, endLose);
    } else if (player.size == 20) {
      return new WorldEnd(true, endWin);
    } else {
      return new WorldEnd(false, endWin);
    }
  }

  // move the enemies onTick
  public World onTick() {
    // lists the fish close to the player
    IList<Enemy> closest = this.enemies.foldr(new Closest(this.player), new MtList<Enemy>());

    // adds new enemies every ten ticks
    this.tickCounter += 1;
    if (tickCounter % 10 == 0) {
      IList<Enemy> add = new ConsList<Enemy>(new Enemy(5, Color.RED), this.enemies);
      return new FishWorld(this.player, add.map(new Move()));
    }

    // checks if any of the close fish are bigger
    if (closest.ormap(e -> e.size > this.player.size)) {
      // returns a new list of enemies with the colliding ones removed
      this.enemies = this.enemies.filter(new Collide(this.player));
      // return the new world and takes a life from the player
      return new FishWorld(this.player.takeLife(), enemies.map(new Move()));
    }

    // checks if any of the close fish are smaller
    if (closest.ormap(e -> e.size <= this.player.size)) {
      // returns a new list of enemies with the colliding ones removed
      this.enemies = this.enemies.filter(new Collide(this.player));
      // return the new world with a grown player
      return new FishWorld(this.player.grow(), enemies.map(new Move()));
    } else {
      return this;
    }
  }

  // moves the player based on key input
  public World onKeyEvent(String key) {
    if (key.equals("up")) {
      return new FishWorld(this.player.moveUp(), this.enemies.map(new Move()));
    } else if (key.equals("down")) {
      return new FishWorld(this.player.moveDown(), this.enemies.map(new Move()));
    } else if (key.equals("right")) {
      return new FishWorld(this.player.moveRight(), this.enemies.map(new Move()));
    } else if (key.equals("left")) {
      return new FishWorld(this.player.moveLeft(), this.enemies.map(new Move()));
    } else {
      return new FishWorld(this.player, this.enemies.map(new Move()));
    }
  }
}

class ExamplesGame {
  ExamplesGame() {

  }

  // examples of Player and enemies
  Player p1 = new Player(10, 10, Color.ORANGE, 300, 200, 3);
  Player p2 = new Player(10, 20, Color.ORANGE, 200, 300, 3);
  Player p3 = new Player(10, 10, Color.ORANGE, 300, 200, 0);
  Enemy e1 = new Enemy(10, 5, Color.RED, 300, 200);
  Enemy e2 = new Enemy(10, 5, Color.RED, 500, 100); 
  Enemy e3 = new Enemy(10, 10, Color.RED, 300, 200);
  Enemy e4 = new Enemy(10, 10, Color.RED, 0, 0); 
  Enemy e5 = new Enemy(10, 20, Color.RED, 300, 200);
  Enemy e6 = new Enemy(10, 20, Color.RED, 57, 337);

  IList<Enemy> mtEnemies = new MtList<Enemy>();

  IList<Enemy> lE1 = new ConsList<Enemy>(this.e1,
      new ConsList<Enemy>(this.e2,
          new ConsList<Enemy>(this.e3,
              new ConsList<Enemy>(this.e4,
                  new ConsList<Enemy>(this.e5, this.mtEnemies))))); 
  
  // examples of some worlds
  FishWorld f1 = new FishWorld(this.p1, this.mtEnemies); // regular world
  FishWorld f2 = new FishWorld(this.p3, this.mtEnemies); // lose world
  FishWorld f3 = new FishWorld(this.p2, this.mtEnemies); // win world

  // run the game
  boolean testBigBang(Tester t) {
    FishWorld world = new FishWorld(this.p1, this.mtEnemies);
    int worldWidth = 600;
    int worldHeight = 400;
    double tickRate = .1;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }

  // test draw for player
  void testPlayerDraw(Tester t) {
    t.checkExpect(p1.draw(), new CircleImage(10, "solid", Color.orange));
    t.checkExpect(p2.draw(), new CircleImage(20, "solid", Color.orange));
  }

  // test moveUp
  void testMoveUp(Tester t) {
    t.checkExpect(p1.moveUp(), new Player(10, 10, Color.ORANGE, 300, 190, 3));
    t.checkExpect(p2.moveUp(), new Player(10, 20, Color.ORANGE, 200, 290, 3));
  }

  // test moveDown
  void testMoveDown(Tester t) {
    t.checkExpect(p1.moveDown(), new Player(10, 10, Color.ORANGE, 300, 210, 3));
    t.checkExpect(p2.moveDown(), new Player(10, 20, Color.ORANGE, 200, 310, 3));
  }

  // test moveRight
  void testMoveRight(Tester t) {
    t.checkExpect(p1.moveRight(), new Player(10, 10, Color.ORANGE, 310, 200, 3));
    t.checkExpect(p2.moveRight(), new Player(10, 20, Color.ORANGE, 210, 300, 3));
  }

  // test moveLeft
  void testMoveLeft(Tester t) {
    t.checkExpect(p1.moveLeft(), new Player(10, 10, Color.ORANGE, 290, 200, 3));
    t.checkExpect(p2.moveLeft(), new Player(10, 20, Color.ORANGE, 190, 300, 3));
  }

  // test grow
  void testGrow(Tester t) {
    t.checkExpect(p1.grow(), new Player(10, 11, Color.ORANGE, 300, 200, 3));
    t.checkExpect(p2.grow(), new Player(10, 21, Color.ORANGE, 200, 300, 3));
  }
  
  // test takeLife
  void testTakeLife(Tester t) {
    t.checkExpect(p1.takeLife(), new Player(10, 10, Color.ORANGE, 300, 200, 2));
    t.checkExpect(p2.takeLife(), new Player(10, 20, Color.ORANGE, 200, 300, 2));
  }

  // test draw for enemy
  void testEnemyDraw(Tester t) {
    t.checkExpect(e1.draw(), new CircleImage(5, "solid", Color.RED));
    t.checkExpect(e3.draw(), new CircleImage(10, "solid", Color.RED));
  }

  // examples for filter test
  IList<String> mtStrings = new MtList<String>();
  IList<String> strings = new ConsList<String>("hello",
      new ConsList<String>("world",
          new ConsList<String>("fundies 2", mtStrings)));
  IList<Integer> mtInts = new MtList<Integer>();
  IList<Integer> ints = new ConsList<Integer>(1,
      new ConsList<Integer>(2,
          new ConsList<Integer>(3, mtInts)));

  // test filter
  void testFilter(Tester t) {
    t.checkExpect(this.mtStrings.filter(s -> s.length() > 4), this.mtStrings);
    t.checkExpect(this.strings.filter(s -> s.length() > 5), new ConsList<String>("fundies 2", mtStrings));
    t.checkExpect(this.ints.filter(i -> i == 3), new ConsList<Integer>(3, mtInts));
  }

  // test ormap
  void testOrMap(Tester t) {
    t.checkExpect(this.mtStrings.ormap(s -> s.length() > 4), false);
    t.checkExpect(this.strings.ormap(s -> s.length() > 5), true);
    t.checkExpect(this.ints.ormap(i -> i == 34), false);
  }

  // examples for map test
  IList<String> stringsMap = new ConsList<String>("hello + 1",
      new ConsList<String>("world + 1",
          new ConsList<String>("fundies 2 + 1", mtStrings)));
  IList<Integer> intsMap = new ConsList<Integer>(2,
      new ConsList<Integer>(3,
          new ConsList<Integer>(4, mtInts)));

  // test map
  void testMap(Tester t) {
    t.checkExpect(this.mtStrings.map(s -> s + " + 1"), this.mtStrings);
    t.checkExpect(this.strings.map(s -> s + " + 1"), this.stringsMap);
    t.checkExpect(this.ints.map(i -> i + 1), this.intsMap);
  }

  // test foldr
  void testFoldr(Tester t) {
    t.checkExpect(this.mtStrings.foldr((s1,  s2) -> s1 + s2, ""), "");
    t.checkExpect(this.strings.foldr((s1,  s2) -> s1 + s2, ""), "helloworldfundies 2");
    t.checkExpect(this.ints.foldr((n1,  n2) -> n1 + n2, 0), 6);
  }

  // test Move predicate
  void testMovePred(Tester t) {
    t.checkExpect(new Move().apply(this.e1), 
        new Enemy(this.e1.speed, this.e1.size, this.e1.color, this.e1.x + this.e1.speed,  this.e1.y));
    t.checkExpect(new Move().apply(this.e2), 
        new Enemy(this.e2.speed, this.e2.size, this.e2.color, this.e2.x + this.e2.speed,  this.e2.y));
    t.checkExpect(new Move().apply(this.e2), 
        new Enemy(this.e2.speed, this.e2.size, this.e2.color, this.e2.x + this.e2.speed,  this.e2.y));
  }

  // test Collide function
  void testCollide(Tester t) {
    t.checkExpect(new Collide(this.p1).test(this.e1), false);
    t.checkExpect(new Collide(this.p1).test(this.e2), true);
    t.checkExpect(new Collide(this.p1).test(this.e3), false);
    t.checkExpect(new Collide(this.p1).test(this.e4), true);
  }

  // test Closest bifunction
  void testClosest(Tester t) {
    t.checkExpect(new Closest(this.p1).apply(this.e6, this.lE1), this.lE1);
    t.checkExpect(new Closest(this.p1).apply(this.e3, this.lE1), new ConsList<Enemy>(this.e3, this.lE1));
    t.checkExpect(new Closest(this.p1).apply(this.e2, this.lE1), this.lE1);
  }
  
  // test makeScene
  void testMakeScene(Tester t) {
    t.checkExpect(f1.makeScene(), new WorldScene(600, 400).placeImageXY(new CircleImage(10, "solid", Color.ORANGE), 300, 200));
    t.checkExpect(f2.makeScene(), new WorldScene(600, 400).placeImageXY(new CircleImage(10, "solid", Color.ORANGE), 300, 200));
  }
  
  // some worlds for worldEnds test
  WorldScene endLoseTest = new WorldScene(600, 400).placeImageXY(new TextImage("GAME OVER - YOU DIED", 20, Color.BLACK), 300, 150);
  WorldScene endWinTest = new WorldScene(600, 400).placeImageXY(new TextImage("YOU WON - SIZE 20 REACHED", 20, Color.BLACK), 300, 150);
  
  // test worldEnds
  void testWorldEnds(Tester t) {
    t.checkExpect(f1.worldEnds(), new WorldEnd(false, this.endWinTest)); // regular
    t.checkExpect(f2.worldEnds(), new WorldEnd(true, this.endLoseTest)); // lose
    t.checkExpect(f3.worldEnds(), new WorldEnd(true, this.endWinTest)); // win
  }
  
  // onTick player example
  Player pO = new Player(10, 10, Color.ORANGE, 300, 200, 3);
  // onTick enemy example
  Enemy eO1 = new Enemy(10, 5, Color.RED, 300, 200); // spawns on player and is small
  IList<Enemy> listO1 = new ConsList<Enemy>(eO1, new MtList<Enemy>());
  Enemy eO2 = new Enemy(10, 15, Color.RED, 300, 200); // spawns on player and is big
  IList<Enemy> listO2 = new ConsList<Enemy>(eO2, new MtList<Enemy>());
  // example worlds
  FishWorld fO1 = new FishWorld(this.pO, new MtList<Enemy>()); // base case
  FishWorld fO2 = new FishWorld(this.pO, this.listO1); // grow case
  FishWorld fO3 = new FishWorld(this.pO, this.listO2); // takeLife case
  
  // test onTick
  void testOnTick(Tester t) {
    t.checkExpect(fO1.onTick(), this.fO1); // regular
    t.checkExpect(fO2.onTick(), new FishWorld(this.pO.grow(), new MtList<Enemy>())); // player grows
    t.checkExpect(fO3.onTick(), new FishWorld(this.pO.takeLife(), new MtList<Enemy>())); // player loses a life
  }
  
  // test onKeyEvent
  void testOnKeyEvent(Tester t) {
    t.checkExpect(f1.onKeyEvent("up"), new FishWorld(new Player(10, 10, Color.ORANGE, 300, 190, 3), this.mtEnemies));
    t.checkExpect(f1.onKeyEvent("down"), new FishWorld(new Player(10, 10, Color.ORANGE, 300, 210, 3), this.mtEnemies));
    t.checkExpect(f1.onKeyEvent("left"), new FishWorld(new Player(10, 10, Color.ORANGE, 290, 200, 3), this.mtEnemies));
    t.checkExpect(f1.onKeyEvent("right"), new FishWorld(new Player(10, 10, Color.ORANGE, 310, 200, 3), this.mtEnemies));
    t.checkExpect(f1.onKeyEvent("v"), new FishWorld(new Player(10, 10, Color.ORANGE, 300, 200, 3), this.mtEnemies));
  }
}