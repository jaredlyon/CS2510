import tester.*;
import java.util.Random;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;

//a class to represent a dot with a radius, color and location
class Dot {
  int radius;
  Color c;
  int x;
  int y;

  Dot(int radius, Color c) {
    Random rand = new Random();
    this.radius = radius;
    this.c = c;
    this.x = rand.nextInt(600);
    this.y = rand.nextInt(400);
  }

  Dot(int radius, Color c, int seed) {
    Random rand = new Random(seed);
    this.radius = radius;
    this.c = c;
    this.x = rand.nextInt(600);
    this.y = rand.nextInt(400);
  }

  Dot(int radius, Color c, int x, int y) {
    this.radius = radius;
    this.c = c;
    this.x = x;
    this.y = y;
  }

  /* TEMPLATE
   * 
   * fields:
   * 
   * this.radius -- int
   * this.c -- Color
   * this.x -- int
   * this.y -- int
   * this.seed -- int
   * 
   * methods:
   * 
   * this.draw() -- WorldImage
   * this.move() -- Dot
   * this.setGreen() -- Dot
   * 
   * methods for fields: none
   */
  
  // draw this Dot as CircleImage with its size and color
  WorldImage draw() {
    return new CircleImage(this.radius, "solid", this.c);
  }

  // create a new dot that is like this Dot but is shifted on the x-axis
  Dot move() {
    return new Dot(this.radius, this.c, this.x + 5, this.y + 3);
  }

  // recolor the dot
  Dot setGreen() {
    return new Dot(this.radius, Color.green, this.x, this.y);
  }

}

//represents a list of Dots
interface ILoDot {
  
  /* TEMPLATE
   * 
   * fields: none
   * 
   * methods:
   * 
   * this.draw() -- WorldScene
   * this.move() -- ILoDot
   * this.recolor() -- ILoDot
   * 
   * methods for fields: none
   */
  
  // draws Dots from this list onto the given scene
  WorldScene draw(WorldScene acc);

  // moves this list of Dots
  ILoDot move();

  // recolor the dots
  ILoDot recolor();
}

//represents an empty list of Dots
class MtLoDot implements ILoDot {

  /* TEMPLATE
   * 
   * fields: none
   * 
   * methods:
   * 
   * this.draw() -- WorldScene
   * this.move() -- ILoDot
   * this.recolor() -- ILoDot
   * 
   * methods for fields: none
   */
  
  // draws Dots from this empty list onto the accumulated
  // image of the scene so far
  public WorldScene draw(WorldScene acc) {
    return acc;
  }

  // move the Dots in this empty list
  public ILoDot move() {
    return this;
  }

  // recolor the dots
  public ILoDot recolor() {
    return this;
  }
}

//represents a non-empty list of Dots
class ConsLoDot implements ILoDot {
  Dot first;
  ILoDot rest;

  ConsLoDot(Dot first, ILoDot rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* TEMPLATE
   * 
   * fields: none
   * 
   * methods:
   * 
   * this.draw() -- WorldScene
   * this.move() -- ILoDot
   * this.recolor() -- ILoDot
   * 
   * methods for fields:
   * 
   * this.first.draw() -- Dot
   * this.first.move() -- Dot
   * this.first.setGreen() -- Dot
   * this.rest.draw() -- WorldScene
   * this.rest.move() -- ILoDot
   * this.rest.recolor() -- ILoDot
   */

  // draws dots from this non-empty list onto the accumulated
  // image of the scene so far
  public WorldScene draw(WorldScene acc) {
    return this.rest.draw(acc.placeImageXY(this.first.draw(), this.first.x, this.first.y));
  }

  // move the dots in this non-empty list
  public ILoDot move() {
    return new ConsLoDot(this.first.move(), this.rest.move());
  }

  // recolor the dots
  public ILoDot recolor() {
    return new ConsLoDot(this.first.setGreen(), this.rest.recolor());
  }
}

//represents a world class to animate a list of Dots on a scene
class DotsWorld extends World {
  ILoDot dots;

  DotsWorld(ILoDot dots) {
    this.dots = dots;
  }
  
  /* TEMPLATE
   * 
   * fields:
   * 
   * this.dots -- ILoDot
   * 
   * methods:
   * 
   * this.makeScene() -- WorldScene
   * this.onTick() -- World
   * this.onKeyEvent() -- World
   * 
   * methods for fields:
   * 
   * this.dots.draw() -- WorldScene
   * this.dots.move() -- ILoDot
   * this.dots.recolor() -- ILoDot
   * 
   */

  // draws the dots onto the background
  public WorldScene makeScene() {
    return this.dots.draw(new WorldScene(600, 400));
  }

  // move the Dots on the scene. Adds a new Dot at a random location at every tick
  // of the clock
  public World onTick() {
    ILoDot add = new ConsLoDot(new Dot(10, Color.magenta), this.dots);
    return new DotsWorld(add.move());
  }

  // add a key event to change the colors of all of the existing Dots in this
  // World to green when the "g" key is pressed
  public World onKeyEvent(String key) {
    if (key.equals("g")) {
      return new DotsWorld(this.dots.recolor());
    }
    else {
      return this;
    }
  }
}

class ExamplesAnimation {
  Dot d1Rand = new Dot(10, Color.magenta);
  Dot d2Rand = new Dot(10, Color.magenta);
  Dot d3Rand = new Dot(10, Color.magenta);

  Dot d1 = new Dot(10, Color.magenta, 2);
  Dot d2 = new Dot(10, Color.magenta, 5);
  Dot d3 = new Dot(10, Color.magenta, 10);
  Dot d4 = new Dot(10, Color.magenta, 10, 10);
  Dot d5 = new Dot(10, Color.magenta, 15, 20);
  Dot d6 = new Dot(10, Color.magenta, 15, 13);
  Dot d7 = new Dot(10, Color.magenta, 20, 23);

  Dot d8 = new Dot(10, Color.green, 2);
  Dot d9 = new Dot(10, Color.green, 5);
  Dot d10 = new Dot(10, Color.green, 10);
  Dot d11 = new Dot(10, Color.green, 10, 10);
  Dot d12 = new Dot(10, Color.green, 15, 20);
  Dot d13 = new Dot(10, Color.green, 15, 13);
  Dot d14 = new Dot(10, Color.green, 20, 23);

  ILoDot mt = new MtLoDot();
  ILoDot lod1 = new ConsLoDot(this.d1, this.mt);
  ILoDot lod2 = new ConsLoDot(this.d2, this.lod1);
  ILoDot lod3 = new ConsLoDot(this.d3, this.lod2);
  ILoDot lod4 = new ConsLoDot(this.d5, new ConsLoDot(this.d4, this.mt));
  ILoDot lod5 = new ConsLoDot(this.d7, new ConsLoDot(this.d6, this.mt));

  ILoDot lod6 = new ConsLoDot(this.d8, this.mt);
  ILoDot lod7 = new ConsLoDot(this.d9, this.lod6);
  ILoDot lod8 = new ConsLoDot(this.d10, this.lod7);
  ILoDot lod9 = new ConsLoDot(this.d12, new ConsLoDot(this.d11, this.mt));
  ILoDot lod10 = new ConsLoDot(this.d14, new ConsLoDot(this.d13, this.mt));

  DotsWorld dw1 = new DotsWorld(this.lod1);
  DotsWorld dw2 = new DotsWorld(this.lod2);
  DotsWorld dw3 = new DotsWorld(this.lod3);
  DotsWorld dw4 = new DotsWorld(this.lod4);
  DotsWorld dw5 = new DotsWorld(this.lod5);

  DotsWorld dw6 = new DotsWorld(this.lod6);
  DotsWorld dw7 = new DotsWorld(this.lod7);
  DotsWorld dw8 = new DotsWorld(this.lod8);
  DotsWorld dw9 = new DotsWorld(this.lod9);
  DotsWorld dw10 = new DotsWorld(this.lod10);

  // add test for all methods above and any that you add
  boolean testBigBang(Tester t) {
    DotsWorld world = new DotsWorld(this.mt);
    int worldWidth = 600;
    int worldHeight = 400;
    double tickRate = .1;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }

  // test draw method
  boolean testDraw(Tester t) {
    return t.checkExpect(this.d4.draw(), new CircleImage(10, "solid", Color.magenta))
        && t.checkExpect(this.d5.draw(), new CircleImage(10, "solid", Color.magenta));
  }

  // test move method
  boolean testMove(Tester t) {
    return t.checkExpect(this.d4.move(), this.d6) 
        && t.checkExpect(this.d5.move(), this.d7)
        && t.checkExpect(this.lod3.move(), this.lod4)
        && t.checkExpect(this.lod4.move(), this.lod5);
  }

  // test setGreen method
  boolean testSetGreen(Tester t) {
    return t.checkExpect(this.d1.setGreen(), this.d8) && t.checkExpect(this.d2.setGreen(), this.d9)
        && t.checkExpect(this.d5.setGreen(), this.d12)
        && t.checkExpect(this.d6.setGreen(), this.d13)
        && t.checkExpect(this.d1Rand.setGreen(),
            new Dot(10, Color.green, this.d1Rand.x, this.d1Rand.y))
        && t.checkExpect(this.d2Rand.setGreen(),
            new Dot(10, Color.green, this.d2Rand.x, this.d2Rand.y));
  }

  // test recolor method
  boolean testRecolor(Tester t) {
    return t.checkExpect(this.lod1.recolor(), this.lod6)
        && t.checkExpect(this.lod2.recolor(), this.lod7)
        && t.checkExpect(this.lod3.recolor(), this.lod8)
        && t.checkExpect(this.lod4.recolor(), this.lod9)
        && t.checkExpect(this.lod5.recolor(), this.lod10);
  }

  // test onTick method
  boolean testOnTick(Tester t) {
    return t.checkExpect(this.dw1.onTick(), new DotsWorld((new ConsLoDot(new Dot(10, Color.magenta), this.dw1.dots)).move()));
  }
  
  // test onKeyEvent method
  boolean testOnKeyEvent(Tester t) {
    return t.checkExpect(this.dw1.onKeyEvent("g"), this.dw6)
        && t.checkExpect(this.dw2.onKeyEvent("g"), this.dw7)
        && t.checkExpect(this.dw4.onKeyEvent("g"), this.dw9)
        && t.checkExpect(this.dw5.onKeyEvent("g"), this.dw10);
  }
}