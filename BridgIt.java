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

  // updates this node when clicked
  void update(Color col);

  // checks if this tile has been changed
  boolean checkChange();
}

// represents a colored node 
class Node implements INode {
  Color color;
  INode up;
  INode down;
  INode left;
  INode right;
  boolean changed;

  // full constructor
  Node(Color color, INode up, INode down, INode left, INode right) {
    this.color = color;
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
    this.changed = true;
  }

  // skeleton constructor
  Node(Color color) {
    this.color = color;
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
    this.changed = true;
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

  // "updates" this node when clicked
  public void update(Color col) {
    // do nothing
  }

  // checks if this node has been changed
  public boolean checkChange() {
    return this.changed;
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

  // full constructor
  Empty(Color color, INode up, INode down, INode left, INode right, boolean changed) {
    this.color = color;
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
    this.changed = changed;
  }

  // skeleton questions
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

  // updates this node when clicked
  // EFFECT: colors an empty tile
  public void update(Color col) {
    this.color = col;
    this.changed = true;
  }

  // checks if this node has been changed
  public boolean checkChange() {
    return this.changed;
  }
}

// represents a placeholder edge tile
class Edge implements INode {
  Color color;
  boolean changed;

  Edge() {
    this.color = Color.RED;
    this.changed = true;
  }

  // "draws" this node -> should never be called
  public WorldImage drawAt() {
    return new RectangleImage(50, 50, "solid", this.color);
  }

  // "links" this node -> will never be called
  public void link(INode up, INode down, INode left, INode right) {
    // empty lel
  }

  // "updates" this node when clicked
  public void update(Color col) {
    // do nothing
  }

  // checks if this node has been changed
  public boolean checkChange() {
    return this.changed;
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

  // changes an empty piece upon click
  public void onMouseClicked(Posn pos) {
    int rowIndex = pos.x / 50;
    int colIndex = pos.y / 50;

    Color newColor = null;
    if (this.counter % 2 == 0) {
      newColor = Color.magenta; 
    } else {
      newColor = Color.pink;
    }

    if (!this.nodes.get(rowIndex).get(colIndex).checkChange()) {
      this.nodes.get(rowIndex).get(colIndex).update(newColor);
      this.counter++;
      this.checkWin(newColor, rowIndex, colIndex);
    }
  }

  //  public WorldScene checkWin(Color col, int rowIndex, int colIndex) {
  //    INode currentNode = this.nodes.get(rowIndex).get(colIndex);
  //    ArrayList<INode> nodeList = new ArrayList<INode>();
  //    nodeList.add(currentNode);
  //    
  //    if (col == Color.PINK) {
  //      
  //    } else if (col == Color.MAGENTA) {
  //      
  //    }
  //  }

  //In Graph
  boolean bfs(INode from, INode to) {
    return searchHelp(from, to, new ArrayList<INode>());
  }

  boolean searchHelp(INode from, INode to, ArrayList<INode> worklist) {
    ArrayList<INode> alreadySeen = new ArrayList<INode>();

    // Initialize the worklist with the from vertex
    worklist.add(from);
    // As long as the worklist isn't empty...
    while (!worklist.isEmpty()) {
      INode next = worklist.remove(0);
      if (next.equals(to)) {
        return true; // Success!
      }
      else if (alreadySeen.contains(next)) {
        // do nothing: we've already seen this one
      }
      else {
        // add all the neighbors of next to the worklist for further processing
        worklist.add(next.up);
        worklist.add(next.down);
        worklist.add(next.left);
        worklist.add(next.right);
        
        // add next to alreadySeen, since we're done with it
        alreadySeen.add(next);
      }
    }
    // We haven't found the to vertex, and there are no more to try
    return false;
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