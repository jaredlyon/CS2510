import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;

class Player {
  int speed;
  int size;
  Color color;
  int x;
  int y;
  
  // generate the player
  Player(int speed, Color color) {
    this.speed = speed;
    this.size = 10;
    this.color = color;
    this.x = 300; // middle of screen
    this.y = 200; // middle of screen
  }
  
  // constructor for moving the player
  Player(int speed, int size, Color color, int x, int y) {
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.x = x;
    this.y = y;
  }
  
  // draw this Dot as CircleImage with its size and color
  WorldImage draw() {
    return new CircleImage(this.size, "solid", this.color);
  }
  
  // move the player up
  Player moveUp() {
    return new Player(this.speed, this.size, this.color, this.x, this.y + this.speed);
  }
  
  // move the player down
  Player moveDown() {
    return new Player(this.speed, this.size, this.color, this.x, this.y - this.speed);
  }
  
  // move the player right
  Player moveRight() {
    return new Player(this.speed, this.size, this.color, this.x + this.speed, this.y);
  }
  
  
  // move the player left
  Player moveLeft() {
    return new Player(this.speed, this.size, this.color, this.x - this.speed, this.y);
  }
  
  // determines if a collision occurs
  Player collide(Enemy e) {                // needs to be able to grow player and kill enemy
    if (this.distanceTo(e) == 0) {
      if (this.size > e.size) {
        return this.grow();
          }
        else if(this.size == e.size) {
          return this;
        }
        else {
          return this.kill();
        }
      }
    else {
      return this;
    }
  }
  
  // determines the distance between player and enemy
  double distanceTo(Enemy e) {
    return Math.sqrt((this.x - e.x)*(this.x - e.x) + (this.y - e.y)*(this.y - e.y));
  }
  
  // kill the player
  Player kill() {
    return new Player(this.speed, 0, this.color, this.x, this.y);
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
  Enemy(int speed, int size, Color color) {
    this.speed = speed;
    this.color = color;
    Random rand = new Random(); // randomly places the enemy on screen
    this.x = rand.nextInt(600);
    this.y = rand.nextInt(400);
    this.size = rand.nextInt(25); // gives this enemy a random size
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
  Enemy grow() {
    return new Enemy(this.speed, this.size + 1, this.color, this.x, this.y);
  }
}

interface IList<T> {
  // draws fish from this list onto the given scene
  WorldScene draw(WorldScene acc);
  
  // moves this list of Fish
  IList<T> move();
}

class MtList<T> implements IList<T> {
  
  MtList() {
    
  }

  @Override
  public WorldScene draw(WorldScene acc) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IList<T> move() {
    // TODO Auto-generated method stub
    return null;
  }
  
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
  
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public WorldScene draw(WorldScene acc) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IList<T> move() {
    // TODO Auto-generated method stub
    return null;
  }
  
}

class FishWorld extends World {
  
}

class ExamplesGame {
  ExamplesGame() {
    
  }
  
  // examples of Player and enemies
  Player p1 = new Player (10, 10, Color.BLUE, 300, 200);
  Enemy e1 = new Enemy (10, 5, Color.GREEN, 300, 200);       // give random starting pts for enemies
  Enemy e2 = new Enemy (10, 5, Color.GREEN, 500, 100); 
  Enemy e3 = new Enemy (10, 10, Color.YELLOW, 300, 200);
  Enemy e4 = new Enemy (10, 10, Color.YELLOW, 0, 0); 
  Enemy e5 = new Enemy (10, 20, Color.RED, 300, 200);
  Enemy e6 = new Enemy (10, 20, Color.RED, 57, 337); 
  
  
  // tests for distanceTo method
  boolean testDistanceTo(Tester t) {
    return t.checkInexact(this.p1.distanceTo(e1), 0.0, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e2), 223.60679, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e3), 0.0, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e4), 360.55513, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e5), 0.0, 0.00001)
        && t.checkInexact(this.p1.distanceTo(e6), 278.95877, 0.00001);
  }
  
  // tests for collide method
  boolean testCollide(Tester t) {
    return t.checkExpect(this.p1.collide(this.e1), 
            new Player(this.p1.speed, this.p1.size + 1, this.p1.color, this.p1.x, this.p1.y))
        && t.checkExpect(this.p1.collide(this.e2), this.p1)
        && t.checkExpect(this.p1.collide(this.e3), this.p1)
        && t.checkExpect(this.p1.collide(this.e4), this.p1)
        && t.checkExpect(this.p1.collide(this.e5), 
            new Player(this.p1.speed, 0, this.p1.color, this.p1.x, this.p1.y))
        && t.checkExpect(this.p1.collide(this.e6), this.p1);
  }
  
  // tests for kill method
  boolean testKill(Tester t) {
    return t.checkExpect(this.p1.kill(), new Player(this.p1.speed, 0, this.p1.color, this.p1.x, this.p1.y))
        && t.checkExpect(this.e1.kill(), new Enemy(this.e1.speed, 0, this.e1.color, this.e1.x, this.e1.y))
        && t.checkExpect(this.e2.kill(), new Enemy(this.e2.speed, 0, this.e2.color, this.e2.x, this.e2.y))
        && t.checkExpect(this.e3.kill(), new Enemy(this.e3.speed, 0, this.e3.color, this.e3.x, this.e3.y));
  }
  
  // tests for grow method
  boolean testGrow(Tester t) {
    return t.checkExpect(this.p1.grow(), new Player(this.p1.speed, this.p1.size + 1, this.p1.color, this.p1.x, this.p1.y))
        && t.checkExpect(this.e1.grow(), new Enemy(this.e1.speed, this.e1.size + 1, this.e1.color, this.e1.x, this.e1.y))
        && t.checkExpect(this.e2.grow(), new Enemy(this.e2.speed, this.e2.size + 1, this.e2.color, this.e2.x, this.e2.y))
        && t.checkExpect(this.e3.grow(), new Enemy(this.e3.speed, this.e3.size + 1, this.e3.color, this.e3.x, this.e3.y));  
  }
}