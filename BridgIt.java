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
  // EFFECT: links this node to given neighbors
  void link(INode up, INode down, INode left, INode right);

  // updates this node when clicked
  // EFFECT: updates a node
  void update(Color col);

  // checks if this tile has been changed
  boolean checkChange();
  
  // adds the linkages to the given arraylist for the search algo
  // EFFECT: updates a given arraylist only with the color matches
  void addLinks(ArrayList<INode> worklist, Color col);
  
  // checks if this node matches a given color
  boolean match(Color col);
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
  // EFFECT: links this node to given neighbors
  public void link(INode up, INode down, INode left, INode right) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }

  // "updates" this node when clicked
  // EFFECT: nothing -> this node will never be updated
  public void update(Color col) {
    // do nothing
  }

  // checks if this node has been changed
  public boolean checkChange() {
    return this.changed;
  }

  // adds the linkages to the given arraylist for the search algo
  // EFFECT: updates a given worklist with this node's neighbors only if the color matches
  public void addLinks(ArrayList<INode> worklist, Color col) {
    if (this.up.match(col)) {
      worklist.add(this.up);
    }
    
    if (this.down.match(col)) {
      worklist.add(this.down);
    }
    
    if (this.left.match(col)) {
      worklist.add(this.left);
    }
    
    if (this.right.match(col)) {
      worklist.add(this.right);
    }
  }

  // checks if this node matches a given color
  public boolean match(Color col) {
    return this.color.equals(col);
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
  // EFFECT: links this node with given neighbors
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
  
  // adds the linkages to the given arraylist for the search algo
  // EFFECT: updates a given worklist with this node's neighbors only if the color matches
  public void addLinks(ArrayList<INode> worklist, Color col) {
    if (this.up.match(col)) {
      worklist.add(this.up);
    }
    
    if (this.down.match(col)) {
      worklist.add(this.down);
    }
    
    if (this.left.match(col)) {
      worklist.add(this.left);
    }
    
    if (this.right.match(col)) {
      worklist.add(this.right);
    }
  }
  
  // checks if this node matches a given color
  public boolean match(Color col) {
    return this.color.equals(col);
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
  // EFFECT: nothing -> this should never be called
  public void link(INode up, INode down, INode left, INode right) {
    // do nothing
  }

  // "updates" this node when clicked
  // EFFECT: nothing -> this should never be called
  public void update(Color col) {
    // do nothing
  }

  // checks if this node has been changed
  public boolean checkChange() {
    return this.changed;
  }
  
  // adds the linkages to the given arraylist for the search algo
  // EFFECT: nothing -> this should never be called
  public void addLinks(ArrayList<INode> worklist, Color col) {
    // do nothing
  }
  
  // checks if this node matches a given color
  public boolean match(Color col) {
    return this.color.equals(col);
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
      
      // checks for the win
      if (this.counter % 2 == 0) {
        // even -> search left to right; magenta
        for (int i = 0; i < this.size - 1; i++) {
          for (int j = 0; j < this.size - 1; j++) {
            boolean winCheck = this.bfs(this.nodes.get(i).get(0), this.nodes.get(j).get(this.size - 1), Color.MAGENTA);
            
            if (winCheck) {
              this.endOfWorld("magenta");
            }
          }
        }
      } else {
        // odd -> search top to bottom; pink
        for (int i = 0; i < this.size - 1; i++) {
          for (int j = 0; j < this.size - 1; j++) {
            boolean winCheck = this.bfs(this.nodes.get(0).get(i), this.nodes.get(this.size - 1).get(j), Color.PINK);
            
            if (winCheck) {
              this.endOfWorld("pink");
            }
          }
        }
      }
      
      this.counter++;
    }
  }
  
  // produces win screen
  public WorldScene lastScene(String s) {
    WorldScene scene = new WorldScene(this.size * 50, this.size * 50);
    
    if (s.equals("magenta")) {
      scene.placeImageXY(new TextImage("Magenta wins!", 50.0, Color.MAGENTA),(this.size * 50) / 2, (this.size * 50) / 2);
    } else if (s.equals("pink")) {
      scene.placeImageXY(new TextImage("Pink wins!", 50.0, Color.PINK),(this.size * 50) / 2, (this.size * 50) / 2);
    }
    
    return scene;
  }

  // BFS parent func
  boolean bfs(INode from, INode to, Color col) {
    return searchHelp(from, to, col, new ArrayList<INode>());
  }

  // BFS algorithm
  boolean searchHelp(INode from, INode to, Color col, ArrayList<INode> worklist) {
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
        next.addLinks(worklist, col);
        
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
  BridgIt game2 = new BridgIt(3);
  
  ArrayList<INode> addLinksTestList = new ArrayList<INode>();
  Node node3 = new Node(Color.PINK);
  Node node4 = new Node(Color.PINK);
  Node node5 = new Node(Color.MAGENTA);
  Node node6 = new Node(Color.PINK);
  Node node7 = new Node(Color.PINK);

  // init test data
  void initData() {
    this.node1 = new Node(Color.PINK);
    this.node2 = new Node(Color.MAGENTA);
    this.empty1 = new Empty();
    this.edge1 = new Edge();
    this.game1 = new BridgIt(11);
    this.game2 = new BridgIt(3);
    
    this.node3.link(node4, node5, node6, node7);
    this.addLinksTestList = new ArrayList<INode>();
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
  
  // test update
  void testUpdate(Tester t) {
    this.initData();
    
    this.node1.update(Color.MAGENTA);
    this.empty1.update(Color.MAGENTA);
    this.edge1.update(Color.MAGENTA);
    
    t.checkExpect(this.node1.color, Color.PINK);
    t.checkExpect(this.empty1.color, Color.MAGENTA);
    t.checkExpect(this.empty1.changed, true);
    t.checkExpect(this.edge1.color, Color.RED);
  }
  
  // test checkChange
  void testCheckChange(Tester t) {
    this.initData();
    
    t.checkExpect(this.node1.checkChange(), true);
    t.checkExpect(this.empty1.checkChange(), false);
    t.checkExpect(this.edge1.checkChange(), true);
    
    this.empty1.update(Color.MAGENTA);
    
    t.checkExpect(this.empty1.checkChange(), true);
  }
  
  // test addLinks
  void testAddLinks(Tester t) {
    this.initData();
    
    node3.addLinks(addLinksTestList, Color.PINK);
    
    t.checkExpect(this.addLinksTestList.size(), 3);
    t.checkExpect(this.addLinksTestList.contains(node4), true);
    t.checkExpect(this.addLinksTestList.contains(node5), false);
    t.checkExpect(this.addLinksTestList.contains(node6), true);
    t.checkExpect(this.addLinksTestList.contains(node7), true);
  }
  
  // test match
  void testMatch(Tester t) {
    this.initData();
    
    t.checkExpect(this.node1.match(Color.PINK), true);
    t.checkExpect(this.node1.match(Color.MAGENTA), false);
    t.checkExpect(this.edge1.match(Color.RED), true);
    t.checkExpect(this.empty1.match(Color.WHITE), true);
  }

  // test initNodes
  void testInitNodes(Tester t) {
    this.initData();

    t.checkExpect(this.game1.nodes.size(), 11);
    t.checkExpect(this.game1.nodes.get(0).size(), 11);
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
  
  // test onMouseClicked
  void testOnMouseClicked(Tester t) {
    this.initData();
    
    this.game2.onMouseClicked(new Posn(50, 50));
    t.checkExpect(this.game2.nodes.get(1).get(1), new Empty(Color.MAGENTA, this.game2.nodes.get(0).get(1), this.game2.nodes.get(2).get(1), this.game2.nodes.get(1).get(0), this.game2.nodes.get(1).get(2), true));
    this.game2.onMouseClicked(new Posn(100, 100));
    t.checkExpect(this.game2.nodes.get(2).get(2), new Empty(Color.PINK, this.game2.nodes.get(1).get(2), new Edge(), this.game2.nodes.get(2).get(1), new Edge(), true));
  } 
  
  // test lastScene
  void testLastScene(Tester t) {    
    this.initData();
    
    WorldScene scene1 = new WorldScene(11 * 50, 11 * 50);
    scene1.placeImageXY(new TextImage("Magenta wins!", 50.0, Color.MAGENTA),(11 * 50) / 2, (11 * 50) / 2);
    WorldScene scene2 = new WorldScene(11 * 50, 11 * 50);
    scene2.placeImageXY(new TextImage("Pink wins!", 50.0, Color.PINK),(11 * 50) / 2, (11 * 50) / 2);
    
    t.checkExpect(this.game1.lastScene("magenta"), scene1);
    t.checkExpect(this.game1.lastScene("pink"), scene2);
  } 
  
  // test bfs
  void testBFS(Tester t) {
    this.initData();
    
    t.checkExpect(this.game2.bfs(this.game2.nodes.get(1).get(0), this.game2.nodes.get(1).get(2), Color.MAGENTA), false);
    this.game2.nodes.get(1).get(1).update(Color.MAGENTA);
    t.checkExpect(this.game2.bfs(this.game2.nodes.get(1).get(0), this.game2.nodes.get(1).get(2), Color.MAGENTA), true);
    
    this.initData();
    t.checkExpect(this.game2.bfs(this.game2.nodes.get(0).get(1), this.game2.nodes.get(2).get(1), Color.PINK), false);
    this.game2.nodes.get(1).get(1).update(Color.PINK);
    t.checkExpect(this.game2.bfs(this.game2.nodes.get(0).get(1), this.game2.nodes.get(2).get(1), Color.PINK), true);
  }
  
  // test searchHelp
  void testSearchHelp(Tester t) {
    this.initData();
    ArrayList<INode> searchHelpTestList = new ArrayList<INode>();
    
    t.checkExpect(this.game2.searchHelp(this.game2.nodes.get(1).get(0), this.game2.nodes.get(1).get(2), Color.MAGENTA, searchHelpTestList), false);
    this.game2.nodes.get(1).get(1).update(Color.MAGENTA);
    t.checkExpect(this.game2.searchHelp(this.game2.nodes.get(1).get(0), this.game2.nodes.get(1).get(2), Color.MAGENTA, searchHelpTestList), true);
    
    this.initData();
    searchHelpTestList = new ArrayList<INode>();
    
    t.checkExpect(this.game2.searchHelp(this.game2.nodes.get(0).get(1), this.game2.nodes.get(2).get(1), Color.PINK, searchHelpTestList), false);
    this.game2.nodes.get(1).get(1).update(Color.PINK);
    t.checkExpect(this.game2.searchHelp(this.game2.nodes.get(0).get(1), this.game2.nodes.get(2).get(1), Color.PINK, searchHelpTestList), true);
  }
}