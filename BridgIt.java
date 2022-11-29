import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

class Piece {
  Color color;
  Piece up;
  Piece down;
  Piece left;
  Piece right;
  
  Piece(Color color, Piece up, Piece down, Piece left, Piece right) {
    this.color = color;
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
  
  Piece(Color color) {
    this.color = color;
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
  }
}

// create an empty piece for edge pieces

//represents a fifteen game using tiles
class BridgIt extends World {
  
  int size;

// normal constructor
  BridgIt(int size) {
    if(this.size <= 3 || this.size % 2 == 0) {
      this.size = size + 1;
      else {
        this.size = size;
      }
    }
  }

@Override
public WorldScene makeScene() {
  // TODO Auto-generated method stub
  return null;
}
}