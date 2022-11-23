import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;


// Represents an individual tile
class Tile {
  // The number on the tile.  Use 0 to represent the hole
  Integer value;

  // constructor
  Tile(Integer value) {
    this.value = value;
  }

  // swaps the data of two tiles
  void swap(Tile t) {
    int temp = this.value;
    this.value = t.value;
    t.value = temp;
  }

  // Draws this tile onto the background at the specified logical coordinates
  WorldScene drawAt(int col, int row, WorldScene background) {     
    // generates the tile
    WorldImage tile = new OverlayImage(new TextImage(Integer.toString(this.value), 25, Color.black),
        new RectangleImage(100, 100, "solid", Color.blue));

    // puts the tile on the background
    background.placeImageXY(tile, (col * 100) + 50, (row * 100) + 50);
    
    return background;
  }
}

// represents a fifteen game using tiles
class FifteenGame extends World {
  // represents the rows of tiles
  // outer arraylist is four in length
  // inner are four in length (one will be 0 in value)
  ArrayList<ArrayList<Tile>> tiles;
  ArrayList<ArrayList<Tile>> prev;
  int x;
  int y;

  // A random number generator
  Random rand = new Random();

  // normal constructor
  FifteenGame() {
    this.tiles = this.initTiles();
    this.findValue(0);
  }
  
  // test constructor
  FifteenGame(ArrayList<ArrayList<Tile>> tiles) {
    this.tiles = tiles;
    this.findValue(0);
  }

  // Initialize the tiles in a random order
  public ArrayList<ArrayList<Tile>> initTiles() {
    this.tiles = new ArrayList<ArrayList<Tile>>();

    // create rand instance
    Random rand = new Random();

    // use values 0-15
    ArrayList<Integer> tempVals = new ArrayList<Integer>(Arrays.asList(
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

    // init tempRow holder
    ArrayList<Tile> tempRow = new ArrayList<Tile>();

    // generate 4x4 matrix of random tiles
    for (int i = 0; i < 4; i++) {
      tempRow = new ArrayList<Tile>();
      
      for (int j = 0; j < 4; j++) {
        tempRow.add(new Tile(tempVals.remove(rand.nextInt(tempVals.size()))));
      }

      this.tiles.add(tempRow);
    }

    return this.tiles;
  }

  // draws the game
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(600, 600);

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        this.tiles.get(i).get(j).drawAt(i, j, scene);
      }
    }

    return scene;
  }

  // displays win screen
  WorldScene isWon() {
    WorldScene empty = new WorldScene(120, 120);
    empty.placeImageXY(new TextImage("YOU WON", 20, Color.BLACK), 60, 60);    // place win screen on top of empty scene
    if (this.checkWin()) {
      return empty;
    }
    else {
      return this.makeScene();
    }
  }

  // checks if the player won
  boolean checkWin() {
    boolean flag = true;
    int temp = 0; // start at 0

    // while loop breaks the for loop if any comparisons are false
    while (flag) {
      for (int i = 0; i < 4; i++) {
        for (int j = 1; j < 4; j++) {
          flag = temp < this.tiles.get(i).get(j).value; // check if next tile is greater
          temp = this.tiles.get(i).get(j).value;
        }
      }
    }

    return flag;
  }

  // handles keystrokes
  public void onKeyEvent(String k) {

    this.findValue(0);                         

    if (k.equals("up")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x).get(y - 1));
      this.isWon();                                            
    } else if (k.equals("down")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x).get(y + 1));
      this.isWon();
    } else if (k.equals("left")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x - 1).get(y));
      this.isWon();
    } else if (k.equals("right")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x + 1).get(y));
      this.isWon();
    } else if (k.equals("u")) {
      this.tiles = this.prev;
    }

    this.prev = this.tiles; 
  }

  void findValue(int data) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (this.tiles.get(i).get(j).value == data) {
          this.x = i;
          this.y = j;
        }
      }
    }
  }
}

class ExamplesFifteenGame {

  // game examples
  Tile t1 = new Tile(1);
  Tile t2 = new Tile(2);
  Tile t3 = new Tile(3);
  Tile t4 = new Tile(4);
  Tile t5 = new Tile(5);
  Tile t6 = new Tile(6);
  Tile t7 = new Tile(7);
  Tile t8 = new Tile(8);
  Tile t9 = new Tile(9);
  Tile t10 = new Tile(10);
  Tile t11 = new Tile(11);
  Tile t12 = new Tile(12);
  Tile t13 = new Tile(13);
  Tile t14 = new Tile(14);
  Tile t15 = new Tile(15);
  Tile t0 = new Tile(0);

  FifteenGame fg1;
  ArrayList<Tile> upper;
  ArrayList<Tile> lower;
  ArrayList<ArrayList<Tile>> twoByTwo;

  FifteenGame fgWon;
  ArrayList<Tile> firstRow;
  ArrayList<Tile> secondRow;
  ArrayList<Tile> thirdRow;
  ArrayList<Tile> fourthRow;
  ArrayList<ArrayList<Tile>> wonGame;
  
  ArrayList<Tile> row1 = new ArrayList<Tile>(Arrays.asList(new Tile(0), new Tile(1), new Tile(2), new Tile(3)));
  ArrayList<Tile> row2 = new ArrayList<Tile>(Arrays.asList(new Tile(4), new Tile(5), new Tile(6), new Tile(7)));
  ArrayList<Tile> row3 = new ArrayList<Tile>(Arrays.asList(new Tile(8), new Tile(9), new Tile(10), new Tile(11)));
  ArrayList<Tile> row4 = new ArrayList<Tile>(Arrays.asList(new Tile(12), new Tile(13), new Tile(14), new Tile(15)));
  
  ArrayList<ArrayList<Tile>> initGameTest = new ArrayList<ArrayList<Tile>>(Arrays.asList(row1, row2, row3, row4));
  
  FifteenGame initTest = new FifteenGame(initGameTest);
  
  ArrayList<Tile> row1CheckWin = new ArrayList<Tile>(Arrays.asList(new Tile(1), new Tile(0), new Tile(2), new Tile(3)));
  ArrayList<Tile> row2CheckWin = new ArrayList<Tile>(Arrays.asList(new Tile(4), new Tile(5), new Tile(6), new Tile(7)));
  ArrayList<Tile> row3CheckWin = new ArrayList<Tile>(Arrays.asList(new Tile(8), new Tile(9), new Tile(10), new Tile(11)));
  ArrayList<Tile> row4CheckWin = new ArrayList<Tile>(Arrays.asList(new Tile(12), new Tile(13), new Tile(14), new Tile(15)));
  
  ArrayList<ArrayList<Tile>> checkWinFalseTiles = new ArrayList<ArrayList<Tile>>(Arrays.asList(row1, row2, row3, row4));
  
  FifteenGame checkWinFalse = new FifteenGame(checkWinFalseTiles);

  void initData() {
    this.fg1 = new FifteenGame();
    this.upper = new ArrayList<Tile>();
    upper.add(this.t1);
    upper.add(this.t2);
    this.lower = new ArrayList<Tile>();
    lower.add(this.t3);
    lower.add(this.t4);

    this.twoByTwo = new ArrayList<ArrayList<Tile>>();
    twoByTwo.add(upper);
    twoByTwo.add(lower);
    fg1.tiles = twoByTwo;

    this.fgWon = new FifteenGame();
    this.firstRow = new ArrayList<Tile>();
    firstRow.add(this.t1);
    firstRow.add(this.t2);
    firstRow.add(this.t3);
    firstRow.add(this.t4);
    this.secondRow = new ArrayList<Tile>();
    secondRow.add(this.t5);
    secondRow.add(this.t6);
    secondRow.add(this.t7);
    secondRow.add(this.t8);
    this.thirdRow = new ArrayList<Tile>();
    thirdRow.add(this.t9);
    thirdRow.add(this.t10);
    thirdRow.add(this.t11);
    thirdRow.add(this.t12);
    this.fourthRow = new ArrayList<Tile>();
    fourthRow.add(this.t13);
    fourthRow.add(this.t14);
    fourthRow.add(this.t15);
    fourthRow.add(this.t0);
    this.wonGame = new ArrayList<ArrayList<Tile>>();
    wonGame.add(firstRow);
    wonGame.add(secondRow);
    wonGame.add(thirdRow);
    wonGame.add(fourthRow);
    this.fgWon.tiles = this.wonGame;
  }

  void testGame(Tester t) {
    FifteenGame g = new FifteenGame();
    g.bigBang(600, 600);
  }


  // tests for swap method
  void testSwap(Tester t) {
    this.t1.value = 1;
    this.t2.value = 2;
    this.t1.swap(this.t2);
    this.t1.value = 2;
    this.t2.value = 1;
  }

  // tests for findValue method
  void testFindValue(Tester t) {
    this.initData();

    this.fg1.findValue(1);
    this.fg1.x = 0;
    this.fg1.y = 0;
    this.fg1.findValue(3);
    this.fg1.x = 0;
    this.fg1.y = 1;

    this.fgWon.findValue(0);
    this.fgWon.x = 3;
    this.fgWon.y = 3;
  }

  // tests for isWon method
  void testIsWon(Tester t) {
    this.initData();
    t.checkExpect(this.fgWon.isWon(), new WorldScene(120, 120).placeImageXY(new TextImage("YOU WON", 20, Color.BLACK), 60, 60));
  }

  // tests for checkWin method
  void testCheckWin(Tester t) {
    this.initData();
    t.checkExpect(this.fgWon.checkWin(), true);
    t.checkExpect(this.checkWinFalse.checkWin(), false);
  }

  ArrayList<ArrayList<Tile>> makeSceneTiles = new ArrayList<ArrayList<Tile>>();
  FifteenGame makeSceneTest = new FifteenGame(makeSceneTiles);
  
  // tests for makeScene method
  void testMakeScene(Tester t) {
    this.initData();
    t.checkExpect(this.makeSceneTest.makeScene(), new WorldScene(600, 600));
  }
  
  // test initEncoder
  void testInitTiles(Tester t) {
    t.checkExpect(initTest.tiles.size(), 4);
    t.checkExpect(initTest.tiles.contains(row1), true);
    t.checkExpect(initTest.tiles.contains(row2), true);
    t.checkExpect(initTest.tiles.contains(row3), true);
    t.checkExpect(initTest.tiles.contains(row4), true);   
  }
}