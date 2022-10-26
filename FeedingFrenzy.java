import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

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
  
  // constructor for moving the player
  Player(int speed, int size, Color color, int x, int y) {
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.x = x;
    this.y = y;
    this.lives = 3;
  }
  
  // draw this Dot as CircleImage with its size and color
  WorldImage draw() {
    return new CircleImage(this.size, "solid", this.color);
  }
  
  // move the player up
  Player moveUp() {
    return new Player(this.speed, this.size, this.color, this.x, this.y - this.speed);
  }
  
  // move the player down
  Player moveDown() {
    return new Player(this.speed, this.size, this.color, this.x, this.y + this.speed);
  }
  
  // move the player right
  Player moveRight() {
    return new Player(this.speed, this.size, this.color, this.x + this.speed, this.y);
  }
  
  
  // move the player left
  Player moveLeft() {
    return new Player(this.speed, this.size, this.color, this.x - this.speed, this.y);
  }
  
  // determines the distance between player and enemy
  double distanceTo(Enemy e) {
    return Math.sqrt((this.x - e.x)*(this.x - e.x) + (this.y - e.y)*(this.y - e.y));
  }
  
  // grow the player
  Player grow() {
    return new Player(this.speed, this.size + 1, this.color, this.x, this.y);
  }
}

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
  
  // moves the enemy
  Enemy move() {
    return new Enemy(this.speed, this.size, this.color, this.x + this.speed, this.y);
  }
  
  // determines the distance between player and enemy
  double distanceTo(Enemy e) {
    return Math.sqrt((this.x - e.x)*(this.x - e.x) - (this.y - e.y)*(this.y - e.y));
  }
  
  // kill the enemy
  Enemy kill() {
    return new Enemy(this.speed, 0, this.color, this.x, this.y);
  }
  
  // Do we want the enemy to be able to behave as a player?
  // grow the enemy                
  Enemy grow(Enemy enemy) {
    return new Enemy(this.speed, this.size + enemy.size, this.color, this.x, this.y);
  }
}

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

class MtList<T> implements IList<T> {
  
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

class Collide implements Predicate<Enemy> {
  Player player;
  
  Collide(Player player) {
    this.player = player;
  }

  //is the given enemy next to the player?
  public boolean test(Enemy e) {
    return (Math.sqrt((player.x - e.x) * (player.x - e.x) + (player.y - e.y) * (player.y - e.y))) < player.size;
  } 
}

class Move implements Function<Enemy, Enemy> {

  // moves the enemy
  public Enemy apply(Enemy x) {
    return new Enemy(x.speed, x.size, x.color, x.x + x.speed, x.y);
  }
}

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

class MakeScene implements BiFunction<Enemy, WorldScene, WorldScene> {
  
  // draws the scene
  public WorldScene apply(Enemy enemy, WorldScene scene) {
    return scene.placeImageXY(enemy.draw(), enemy.x, enemy.y);
  }
}

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
  
  // gets called by endOfWorld
  public WorldScene lastScene() {
    return new WorldScene(600, 400).placeImageXY(new TextImage("GAME OVER - YOU DIED", 10, Color.BLACK), 300, 400);
  }

  // move the enemies onTick
  public World onTick() {
    // lists the fish close to the player
    IList<Enemy> closest = this.enemies.foldr(new Closest(this.player), new MtList<Enemy>());
    
    // adds new enemies
    this.tickCounter += 1;
    if (tickCounter % 20 == 0) {
      IList<Enemy> add = new ConsList<Enemy>(new Enemy(1, Color.RED), this.enemies);
      return new FishWorld(this.player, add.map(new Move()));
    } else if (closest.ormap(e -> e.size > this.player.size)) {
      // checks if any of the close fish are bigger

      if (player.lives == 0) {
        // ends the game if the player is out of lives
        this.endOfWorld("You died.");
      } else {
        // returns a new list of enemies with the bigger ones removed
        this.enemies = this.enemies.filter(e -> e.size < this.player.size);
        // takes a life from the player
        this.player.lives = this.player.lives - 1;
        // return the new world
        return new FishWorld(this.player, enemies.map(new Move()));
      }
    } else if (closest.ormap(e -> e.size < this.player.size)) {
      // checks if any of the close fish are bigger
      
      // returns a new list of enemies with the smaller ones removed
      this.enemies = this.enemies.filter(e -> e.size > this.player.size);
      // grows the player
      this.player.grow();
      // return the new world
      return new FishWorld(this.player, enemies.map(new Move()));
    } else {
      return this;
    }
  }

  // move up
  public World onKeyEvent(String key) {
    if (key.equals("up")) {
      return new FishWorld(this.player.moveUp(), this.enemies);
    } else if (key.equals("down")) {
      return new FishWorld(this.player.moveDown(), this.enemies);
    } else if (key.equals("right")) {
      return new FishWorld(this.player.moveRight(), this.enemies);
    } else if (key.equals("left")) {
      return new FishWorld(this.player.moveLeft(), this.enemies);
    } else {
      return this;
    }
  }
}

class ExamplesGame {
  ExamplesGame() {
    
  }
  
  // examples of Player and enemies
  Player p1 = new Player (10, 10, Color.ORANGE, 300, 200);
  Enemy e1 = new Enemy (10, 5, Color.RED, 300, 200);       // give random starting pts for enemies
  Enemy e2 = new Enemy (10, 5, Color.RED, 500, 100); 
  Enemy e3 = new Enemy (10, 10, Color.RED, 300, 200);
  Enemy e4 = new Enemy (10, 10, Color.RED, 0, 0); 
  Enemy e5 = new Enemy (10, 20, Color.RED, 300, 200);
  Enemy e6 = new Enemy (10, 20, Color.RED, 57, 337); 
  
  IList<Enemy> mtEnemies = new MtList<Enemy>();
      
  // run the game
  boolean testBigBang(Tester t) {
    FishWorld world = new FishWorld(this.p1, this.mtEnemies);
    int worldWidth = 600;
    int worldHeight = 400;
    double tickRate = .1;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }
  
  
  // tests for distanceTo method
  boolean testDistanceTo(Tester t) {
    return t.checkInexact(this.p1.distanceTo(e1), 0.0, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e2), 223.60679, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e3), 0.0, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e4), 360.55513, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e5), 0.0, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e6), 278.95877, 0.00001);
  }
}