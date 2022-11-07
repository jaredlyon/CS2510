import javalib.funworld.*;
import javalib.worldimages.*;
import tester.Tester;
import java.awt.Color;
import java.util.ArrayList;


// Represents an individual tile
class Tile {
  // The number on the tile.  Use 0 to represent the hole
  int value;
  
  // Draws this tile onto the background at the specified logical coordinates
  WorldImage drawAt(int col, int row, WorldImage background) { 
    return new OverlayOffsetImage(new RectangleImage(10, 10, "solid", Color.BLUE), col * 30, row * 30, background);
  }
  
}

class FifteenGame extends World {
  // represents the rows of tiles
  ArrayList<ArrayList<Tile>> tiles;
  
  // draws the game
  public WorldScene makeScene() { 
    WorldImage background = new RectangleImage(120, 120, "solid", Color.WHITE);
    
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.tiles.get(i).get(j).drawAt(i, j, background);
      }
    }
  }
  
  // swaps two tiles positions
  WorldScene swap(Tile t) {
    this.tiles.get
  }
  
  // handles keystrokes
  public void onKeyEvent(String k) {
    // WorldScene prev = this;
    
    if (k.equals("up")) {
      // change needs to be stored as previous
      
    } else if (k.equals("down")) {
      // change needs to be stored as previous
      
    } else if (k.equals("left")) {
      // change needs to be stored as previous
      
    } else if (k.equals("right")) {
      // change needs to be stored as previous
      
    } else if (k.equals("u")) {
      // needs to call previous state
    }
  }
}

class ExampleFifteenGame {
  void testGame(Tester t) {
    FifteenGame g = new FifteenGame();
    g.bigBang(120, 120);
  }
}