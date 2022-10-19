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
  Player collide(Enemy e) {
    if (this.distanceTo(e) == 0) {
      if (this.size > e.size) {
        return this.grow();
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
    return Math.sqrt((this.x - e.x)*(this.x - e.x) - (this.y - e.y)*(this.y - e.y));
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
  
  // kills the enemy ?
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
}