import java.util.ArrayList;
import java.util.Iterator;

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
  
  // Draws this tile onto the background at the specified logical coordinates
  WorldImage drawAt(int col, int row, WorldImage background) { 
    return new OverlayImage(new TextImage(this.value.toString(), 5, Color.black), (new OverlayOffsetImage(new RectangleImage(10, 10, "solid", Color.BLUE), col * 30, row * 30, background));
  }
  
}

class FifteenGame extends World {
  // represents the rows of tiles
  // outer arraylist is four in length
  // inner are four in length (one will be 0 in value)
  ArrayList<ArrayList<Tile>> tiles;
  ArrayList<ArrayList<Tile>> prev;
  
  // draws the game
  public WorldScene makeScene() { 
    WorldImage background = new RectangleImage(120, 120, "solid", Color.WHITE);
    
    // use below iterator to draw the stuff
  }
  
  // swaps two tiles positions
  WorldScene swap(Tile t) {
    Tile temp = this.tiles.get(__);
    this.tiles.set(____, t);
    t.set(____, temp);
    
  }
  
  // displays win screen
  WorldScene isWon() {
    WorldScene win = new WorldScene(120, 120).placeImageXY(new TextImage("YOU WON", 20, Color.BLACK), 60, 60);
    if (this.checkWin()) {
      return win;
    }
    else {
      return this;
    }
  }
  
  // checks if the player won
  boolean checkWin() {                                                 // compares order of tiles
    for (i = 0, i < this.tiles.size(), i++) {                          // flag is false upon a singular false case
      boolean flag = true;
      int prev = 0;                                                    // store the previous value for comparison
      for (j = 0, j < this.tiles.listIterator(), j++) {
        if (this.tiles.listIterator().get(j) < prev) {                 // needs to check for zero, it is going to be in bottom right
          flag = false;
        }
        else {
          prev = this.tiles.listIterator().get(j);
        }
      }
     return flag;                                                      // true if all tiles are in order
    }
  }
  
  // handles keystrokes
  public void onKeyEvent(String k) {
    
    if (k.equals("up")) {
      // call swap
      // check win
      
    } else if (k.equals("down")) {
      // call swap
      // check win
      
    } else if (k.equals("left")) {
      // call swap
      // check win
      
    } else if (k.equals("right")) {
      // call swap
      // check win
      
    } else if (k.equals("u")) {
      this = this.prev;
    }
  }
  this.prev = this;                                       // stores previous state for potential undo
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

  public int size() {
    return this.list.size();
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

class ExamplesLoL {
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
}
