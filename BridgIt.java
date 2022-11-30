import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

interface ANode {
  
}

class Node implements ANode {
  Color color;
  Node up;
  Node down;
  Node left;
  Node right;
  
  Node(Color color, Node up, Node down, Node left, Node right) {
    this.color = color;
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
  
  Node(Color color) {
    this.color = color;
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
  }
}

class Empty implements ANode {   // represents a node at a boundary
  Color color;
  
  Empty(Color color) {           // create an empty piece for edge pieces
    this.color = Color.WHITE;
  }
}



//represents a fifteen game using tiles
class BridgIt extends World {
  
  int size;

// normal constructor
  BridgIt(int size) {
    if(this.size <= 3 || this.size % 2 == 0) {
      this.size = size + 1;
    }
      else {
        this.size = size;
      }
    }

  // create in new method, put in constructor
  // the creation of all the pieces belongs in the game constructor, depends on gameboard size (in indices)
  for (i = 0; i <= this.size; i = i + 2) {                   // increment by two, alternates nodes and empty nodes
    for (j = 1; j <= this.size; j = j + 2) {
      // Node temp = new Node(Color, empty, empty, empty, empty)
      // next node -> new Node(Color, empty, empty, list.get(i - 1)., empty)
    }
  }
  
  
  
@Override
public WorldScene makeScene() {
  // TODO Auto-generated method stub
  return null;
}
}