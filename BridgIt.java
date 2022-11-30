import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

// represents an abstracted class node
interface ANode {
  // draws the node
  WorldImage drawAt();
  // links this node to given nodes
  void link(ANode up, ANode down, ANode left, ANode right);
}

// represents a colored node 
class Node implements ANode {
  Color color;
  ANode up;
  ANode down;
  ANode left;
  ANode right;

  // full constructor
  Node(Color color, ANode up, ANode down, ANode left, ANode right) {
    this.color = color;
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }

  // skeleton constructor
  Node(Color color) {
    this.color = color;
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
  }

  // draws this node
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", this.color);
  }

  // links this node to the given nodes
  public void link(ANode up, ANode down, ANode left, ANode right) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
}

// represents a white node
class Empty implements ANode {
  Color color;
  ANode up;
  ANode down;
  ANode left;
  ANode right;
  boolean changed;

  Empty() {
    this.color = Color.WHITE;
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
    this.changed = false;
  }

  // draws the node
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", this.color);
  }

  // links this node to the given nodes
  public void link(ANode up, ANode down, ANode left, ANode right) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
}

// represents a placeholder edge tile
class Edge implements ANode {

  Edge() {
    
  }

  // "draws" this node -> should never be called
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", Color.RED);
  }

  // "links" this node -> probably will never be called
  public void link(ANode up, ANode down, ANode left, ANode right) {
    // empty lel
  }
}

//represents a fifteen game using tiles
class BridgIt extends World {
  int size;
  ArrayList<ArrayList<ANode>> nodes;

  // normal constructor
  BridgIt(int size) {
    if (size % 2 == 0) {
      throw new IllegalArgumentException("Game side length must be odd!");
    } else {
      this.size = size;
    }
    
    this.nodes = this.initNodes();
    
    // a for loop that links each node
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (i == 0) {
          if (j == 0) {
            this.nodes.get(i).get(j).link(new Edge(), this.nodes.get(i + 1).get(j), new Edge(), this.nodes.get(i).get(j + 1));
          } else if (j == this.size - 1) {
            this.nodes.get(i).get(j).link(new Edge(), this.nodes.get(i + 1).get(j), this.nodes.get(i).get(j - 1), new Edge());
          } else {
            this.nodes.get(i).get(j).link(new Edge(), this.nodes.get(i + 1).get(j), this.nodes.get(i).get(j - 1), this.nodes.get(i).get(j + 1));
          }
        } else if (i == this.size - 1) {
          if (j == 0) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j), new Edge(), new Edge(), this.nodes.get(i).get(j + 1));
          } else if (j == this.size - 1) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j), new Edge(), this.nodes.get(i).get(j - 1), new Edge());
          } else {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j), new Edge(), this.nodes.get(i).get(j - 1), this.nodes.get(i).get(j + 1));
          }
        } else {
          if (j == 0) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j), this.nodes.get(i + 1).get(j), new Edge(), this.nodes.get(i).get(j + 1));
          } else if (j == this.size - 1) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j), this.nodes.get(i + 1).get(j), this.nodes.get(i).get(j - 1), new Edge());
          } else {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j), this.nodes.get(i + 1).get(j), this.nodes.get(i).get(j - 1), this.nodes.get(i).get(j + 1));
          }
        }
      }
    }
  }

  // initializes the game board in a checkerboard pattern
  public ArrayList<ArrayList<ANode>> initNodes() {
    this.nodes = new ArrayList<ArrayList<ANode>>();

    // init tempRow holder
    ArrayList<ANode> tempRow = new ArrayList<ANode>();

    // generate matrix of patterned nodes
    for (int i = 0; i < this.size; i++) {
      tempRow = new ArrayList<ANode>();

      for (int j = 0; j < this.size; j++) {
        if (i % 2 == 0) {
          if (j % 2 == 0) {
            tempRow.add(new Empty());
          } else {
            tempRow.add(new Node(Color.PINK));
          }
        } else {
          if (j % 2 == 0) {
            tempRow.add(new Node(Color.MAGENTA));
          } else {
            tempRow.add(new Empty());
          }
        }
      }

      this.nodes.add(tempRow);
    }

    return this.nodes;
  }

  // draws the gameboard
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.size * 50, this.size * 50);

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        scene.placeImageXY(this.nodes.get(i).get(j).drawAt(), (i * 50) + 25, (j * 50) + 25);
      }
    }

    return scene;
  }
}

class ExamplesBridgIt {
  ExamplesBridgIt() {
    
  }
  
  // runs the game
  void testGame(Tester t) {
    BridgIt g = new BridgIt(11);
    g.bigBang(1000, 1000);
  }
}