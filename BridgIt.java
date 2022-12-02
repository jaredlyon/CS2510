
import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

// represents an abstracted class node
interface INode {
  // draws the node
  WorldImage drawAt();
  
  // links this node to given nodes
  void link(INode up, INode down, INode left, INode right);
}
// represents a colored node 
class Node implements INode {
  Color color;
  INode up;
  INode down;
  INode left;
  INode right;

  // full constructor
  Node(Color color, INode up, INode down, INode left, INode right) {
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
  public void link(INode up, INode down, INode left, INode right) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
}

// represents a white node
class Empty implements INode {
  Color color;
  INode up;
  INode down;
  INode left;
  INode right;
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
  public void link(INode up, INode down, INode left, INode right) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
}

// represents a placeholder edge tile
class Edge implements INode {

  Edge() {

  }

  // "draws" this node -> should never be called
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", Color.RED);
  }

  // "links" this node -> probably will never be called
  public void link(INode up, INode down, INode left, INode right) {
    // empty lel
  }
}

//represents a fifteen game using tiles
class BridgIt extends World {
  int size;
  ArrayList<ArrayList<INode>> nodes;
  int counter;

  // normal constructor
  BridgIt(int size) {
    if (size % 2 == 0 || size < 2) {
      throw new IllegalArgumentException("Game side length must be odd and greater than one!");
    } else {
      this.size = size;
    }

    this.nodes = this.initNodes();
    this.counter = 0;

    // a for loop that links each node
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (i == 0) {
          if (j == 0) {
            this.nodes.get(i).get(j).link(new Edge(),
                this.nodes.get(i + 1).get(j),
                new Edge(),
                this.nodes.get(i).get(j + 1));
          } else if (j == this.size - 1) {
            this.nodes.get(i).get(j).link(new Edge(),
                this.nodes.get(i + 1).get(j),
                this.nodes.get(i).get(j - 1),
                new Edge());
          } else {
            this.nodes.get(i).get(j).link(new Edge(),
                this.nodes.get(i + 1).get(j),
                this.nodes.get(i).get(j - 1),
                this.nodes.get(i).get(j + 1));
          }
        } else if (i == this.size - 1) {
          if (j == 0) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j),
                new Edge(),
                new Edge(),
                this.nodes.get(i).get(j + 1));
          } else if (j == this.size - 1) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j),
                new Edge(),
                this.nodes.get(i).get(j - 1),
                new Edge());
          } else {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j),
                new Edge(),
                this.nodes.get(i).get(j - 1),
                this.nodes.get(i).get(j + 1));
          }
        } else {
          if (j == 0) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j),
                this.nodes.get(i + 1).get(j),
                new Edge(),
                this.nodes.get(i).get(j + 1));
          } else if (j == this.size - 1) {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j),
                this.nodes.get(i + 1).get(j),
                this.nodes.get(i).get(j - 1),
                new Edge());
          } else {
            this.nodes.get(i).get(j).link(this.nodes.get(i - 1).get(j),
                this.nodes.get(i + 1).get(j),
                this.nodes.get(i).get(j - 1),
                this.nodes.get(i).get(j + 1));
          }
        }
      }
    }
  }

  // test constructor w/o linkage for makeScene
  BridgIt() {
    this.size = 1;
    this.nodes = this.initNodes();
    this.counter = 0;
  }

  // initializes the game board in a checkerboard pattern
  public ArrayList<ArrayList<INode>> initNodes() {
    this.nodes = new ArrayList<ArrayList<INode>>();

    // init tempRow holder
    ArrayList<INode> tempRow = new ArrayList<INode>();

    // generate matrix of patterned nodes
    for (int i = 0; i < this.size; i++) {
      tempRow = new ArrayList<INode>();

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

  // examples for tests
  Node node1 = new Node(Color.PINK);
  Node node2 = new Node(Color.MAGENTA);
  Empty empty1 = new Empty();
  Edge edge1 = new Edge();

  BridgIt game1 = new BridgIt(11);

  // init test data
  void initData() {
    node1 = new Node(Color.PINK);
    node2 = new Node(Color.MAGENTA);
    empty1 = new Empty();
    edge1 = new Edge();
    game1 = new BridgIt(11);
  }

  // test drawAt
  void testDrawAt(Tester t) {
    this.initData();

    t.checkExpect(this.node1.drawAt(), new RectangleImage(50, 50, "solid", Color.PINK));
    t.checkExpect(this.node2.drawAt(), new RectangleImage(50, 50, "solid", Color.MAGENTA));
    t.checkExpect(this.empty1.drawAt(), new RectangleImage(50, 50, "solid", Color.WHITE));
    t.checkExpect(this.edge1.drawAt(), new RectangleImage(50, 50, "solid", Color.RED));
  }

  // test link
  void testLink(Tester t) {
    this.initData();

    this.node1.link(this.node2, this.empty1, this.empty1, this.edge1);
    this.node2.link(this.empty1, this.empty1, this.empty1, this.empty1);

    t.checkExpect(this.node1.up, this.node2);
    t.checkExpect(this.node1.down, this.empty1);
    t.checkExpect(this.node1.left, this.empty1);
    t.checkExpect(this.node1.right, this.edge1);
    t.checkExpect(this.node2.up, this.empty1);
    t.checkExpect(this.node2.down, this.empty1);
    t.checkExpect(this.node2.left, this.empty1);
    t.checkExpect(this.node2.right, this.empty1);
  }

  // test initNodes
  void testInitNodes(Tester t) {
    this.initData();

    t.checkExpect(game1.nodes.size(), 11);
    t.checkExpect(game1.nodes.get(0).size(), 11);
  }

  // test BridgIt constructor
  void testBridgItConstructor(Tester t) {
    this.initData();

    t.checkConstructorException(
        new IllegalArgumentException("Game side length must be odd and greater than one!"),
        "BridgIt", 2);
    t.checkConstructorException(
        new IllegalArgumentException("Game side length must be odd and greater than one!"),
        "BridgIt", 2);
  }

  BridgIt makeSceneTest = new BridgIt();

  // test makeScene
  void testMakeScene(Tester t) {
    this.initData();

    WorldScene expected = new WorldScene(50, 50);
    expected.placeImageXY(new RectangleImage(50, 50, "solid", Color.WHITE), 25, 25);
    t.checkExpect(this.makeSceneTest.makeScene(), expected);
  }
}