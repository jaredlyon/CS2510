import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

interface ANode {
  WorldImage drawAt();
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

  @Override
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", this.color);
  }
}

class Empty implements ANode {
  Color color;

  Empty() {
    this.color = Color.WHITE;
  }

  @Override
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", this.color);
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
  }

  public ArrayList<ArrayList<ANode>> initNodes() {
    this.nodes = new ArrayList<ArrayList<ANode>>();

    // init tempRow holder
    ArrayList<ANode> tempRow = new ArrayList<ANode>();

    // generate matrix of patterned nodes
    for (int i = 0; i < this.size - 1; i++) {
      tempRow = new ArrayList<ANode>();

      for (int j = 0; j < this.size - 1; j++) {
        if (i % 2 == 0) {
          if (j % 2 == 0) {
            tempRow.add(new Empty());
          } else {
            tempRow.add(new Node(Color.MAGENTA));
          }
        } else {
          if (j % 2 == 0) {
            tempRow.add(new Node(Color.PINK));
          } else {
            tempRow.add(new Empty());
          }
        }
      }

      this.nodes.add(tempRow);
    }

    return this.nodes;
  }

  @Override
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.size * 50, this.size * 50);

    for (int i = 0; i < this.size - 1; i++) {
      for (int j = 0; j < this.size - 1; j++) {
        scene.placeImageXY(this.nodes.get(i).get(j).drawAt(), (i * 50) + 25, (j * 50) + 25);
      }
    }

    return scene;
  }
}

class ExamplesBridgIt {
  ExamplesBridgIt() {
    
  }
  
  void testGame(Tester t) {
    BridgIt g = new BridgIt(11);
    g.bigBang(1000, 1000);
  }
}