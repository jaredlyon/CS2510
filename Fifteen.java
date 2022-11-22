import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;


// Represents an individual tile
class Tile {
  // The number on the tile.  Use 0 to represent the hole
  int value;
  
  Tile(int value) {
    this.value = value;
  }
  
  // swaps the data of two tiles
  void swap(Tile t) {
    int temp = this.value;
    this.value = t.value;
    t.value = temp;
  }
  
  // Draws this tile onto the background at the specified logical coordinates
  WorldImage drawAt(int col, int row, WorldImage background) { 
    return new OverlayImage(new TextImage(this.value.toString(), 5, Color.black), (new OverlayOffsetImage(new RectangleImage(10, 10, "solid", Color.BLUE), col * 30, row * 30, background)));
  }
}

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
  
  // constructor
  FifteenGame() {
    this.tiles = this.initTiles();
  }

  // Initialize the tiles in a random order
  public ArrayList<ArrayList<Tile>> initTiles() {
    // create rand instance
    Random rand = new Random();
    // use values 0-15
    ArrayList<Integer> tempVals = new ArrayList<Integer>(Arrays.asList(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
    // init tempRow holder
    ArrayList<Tile> tempRow = new ArrayList<Tile>();
    
    // generate 4x4 matrix of random tiles
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j ++) {
        tempRow = new ArrayList<Tile>();
        tempRow.add(new Tile(tempVals.remove(rand.nextInt(tempVals.size()))));
      }
      
      this.tiles.add(tempRow);
    }
    
    return this.tiles;
  }
  
  // draws the game
  public WorldScene makeScene() { 
    WorldImage background = new RectangleImage(120, 120, "solid", Color.WHITE);
    // use below iterator to draw the stuff
  }
  
  
  // displays win screen
  World isWon() {
    World win = new World(120, 120).placeImageXY(new TextImage("YOU WON", 20, Color.BLACK), 60, 60);
    if (this.checkWin()) {
      return win;
    }
    else {
      return this;
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
    
    this.findValue(0);                           // finds the zero tile, stores coordinates as indices
    
    if (k.equals("up")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x).get(y - 1));
      // check win 
                                                      // swaps two tiles depending on where it is located
    } else if (k.equals("down")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x).get(y + 1));
      // check win
      
    } else if (k.equals("left")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x - 1).get(y));
      // check win
      
    } else if (k.equals("right")) {
      this.tiles.get(x).get(y).swap(this.tiles.get(x + 1).get(y));
      // check win
      
    } else if (k.equals("u")) {
      this.tiles = this.prev;
    }
    this.prev = this.tiles; 
  }
  
  void findValue(int data) {
    for (int i = 0; i < this.tiles.size(); i++) {
      for (int j = 0; j < this.tiles.get(i).size(); j++)
        if (this.tiles.get(i).get(j).value == data) {
          this.x = i;
          this.y = j;
        }
    }
  }
}

class ExampleFifteenGame {
  void testGame(Tester t) {
    FifteenGame g = new FifteenGame();
    g.bigBang(120, 120);
  }
}

class ListOfLists<T> implements Iterable<T> {
  ArrayList<ArrayList<T>> list;

  ListOfLists(ArrayList<ArrayList<T>> list) {
    this.list = list;
  }

  ListOfLists() {
    this.list = new ArrayList<ArrayList<T>>();
  }

  void addNewList() {
    ArrayList<T> newList = new ArrayList<T>();
    list.add(newList);
  }

  void add(int index, T object) {
    if (index > (list.size() - 1)) {
      throw new IndexOutOfBoundsException("Invalid index!");
    } else {
      ArrayList<T> target = list.get(index);
      target.add(object);
    }
  }

  public ArrayList<T> get(int index) {
    if (index > (list.size() - 1)) {
      throw new IndexOutOfBoundsException("Invalid index!");
    } else {
      return this.list.get(index);
    }
  }
  
  //produces an Iterator for this list
  public Iterator<T> iterator() {
    return new LoLIterator<T>(this);
  }
}

class LoLIterator<T> implements Iterator<T> {
  ListOfLists<T> items;
  int outerIndex;
  int innerIndex;

  LoLIterator(ListOfLists<T> items) {
    this.items = items;
    this.outerIndex = 0;
    this.innerIndex = 0;
  }

  //are there any more items to produce?
  public boolean hasNext() {
    return this.outerIndex < this.items.size()
        && this.innerIndex < this.items.get(outerIndex).size();
  }

  //gets the next item
  //EFFECT: the currentIndex is incremented by 1
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!");
    }

    T temp = this.items.get(this.outerIndex).get(innerIndex);

    if ((this.items.get(this.outerIndex).size() - 1) > innerIndex) {
      innerIndex++;
    } else {
      innerIndex = 0;
      outerIndex++;
    }

    return temp;
  }
}

class ExamplesFifteen {
  void testListOfLists(Tester t) {
    ListOfLists<Integer> lol = new ListOfLists<Integer>();
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();

    //add elements 1,2,3 in first list
    lol.add(0,1);
    lol.add(0,2);
    lol.add(0,3);

    //add elements 4,5,6 in second list
    lol.add(1,4);
    lol.add(1,5);
    lol.add(1,6);

    //add elements 7,8,9 in third list
    lol.add(2,7);
    lol.add(2,8);
    lol.add(2,9);

    //iterator should return elements in order 1,2,3,4,5,6,7,8,9
    int number = 1;
    for (Integer num : lol) {
      t.checkExpect(num, number);
      number = number + 1;
    }
  }
  
  Tile t1 = new Tile(1);
  Tile t2 = new Tile(2);
  
  // tests for swap method
  void testSwap(Tester t) {
    this.t1.value = 1;
    this.t2.value = 2;
    this.t1.swap(this.t2);
    this.t1.value = 2;
    this.t2.value = 1;
  }
}