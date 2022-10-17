import tester.Tester;
import java.util.Comparator;

interface IList<T> {
  // reverses this list
  IList<T> reverse();
  // helps reverse
  IList<T> reverseHelper(IList<T> data);
}

class MtList<T> implements IList<T> {
  MtList() {};

  /*
   * fields:
   *  none
   * methods:
   *  this.reverse() ... IList<T>
   *  this.reverseHelper(IList<T>) ... IList<T>
   * methods for fields:
   *  none
   */

  // reverses this list
  public IList<T> reverse() {
    return this;
  }

  // helper for reverse
  public IList<T> reverseHelper(IList<T> data) {

    /*
     * fields of data:
     *  none
     * methods of data:
     *  this.reverse() ... IList<T>
     *  this.reverseHelper(IList<T>) ... IList<T>
     */

    return data;
  }
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * fields:
   *  this.first ... T
   *  this.rest ... IList<T>
   * methods:
   *  this.reverse() ... IList<T>
   *  this.reverseHelper(IList<T>) ... IList<T>
   * methods for fields:
   *  none
   */

  // reverses this list
  public IList<T> reverse() {
    return this.rest.reverseHelper(new ConsList<T>(this.first, new MtList<T>()));
  }

  // helper for reverse
  public IList<T> reverseHelper(IList<T> data) {

    /*
     * fields of data:
     *  none
     * methods of data:
     *  this.reverse() ... IList<T>
     *  this.reverseHelper(IList<T>) ... IList<T>
     */

    return this.rest.reverseHelper(new ConsList<T>(this.first, data));
  }
}

class Book {
  String title, author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  /*
   * fields:
   *  this.title ... String
   *  this.author ... String
   *  this.price ... int
   * methods:
   *  none
   * methods for fields:
   *  none
   */
}

class BooksByTitle implements Comparator<Book> {

  /*
   * fields:
   *  none
   * methods:
   *  compare(Book, Book) ... int
   * methods for fields:
   *  none
   */

  public int compare(Book b1, Book b2) {

    /*
     * fields of b1, b2:
     *  this.title ... String
     *  this.author ... String
     *  this.price ... int
     * methods:
     *  none
     */

    return b1.title.compareTo(b2.title);
  }
}

class BooksByAuthor implements Comparator<Book> {

  /*
   * fields:
   *  none
   * methods:
   *  compare(Book, Book) ... int
   * methods for fields:
   *  none
   */

  public int compare(Book b1, Book b2) {

    /*
     * fields of b1, b2:
     *  this.title ... String
     *  this.author ... String
     *  this.price ... int
     * methods:
     *  none
     */

    return b1.author.compareTo(b2.author);
  }
}

class BooksByPrice implements Comparator<Book> {

  /*
   * fields:
   *  none
   * methods:
   *  compare(Book, Book) ... int
   * methods for fields:
   *  none
   */

  public int compare(Book b1, Book b2) {

    /*
     * fields of b1, b2:
     *  this.title ... String
     *  this.author ... String
     *  this.price ... int
     * methods:
     *  none
     */

    return b1.price - b2.price;
  }
}

interface IBST<T> {
  // inserts an item into the correct place
  ABST<T> insert(T data);
  // checks if an item in this tree has the given data
  boolean present(T data);
  // gets the leftmost item in this tree
  T getLeftmost();
  // checks if this item is the leftmost item in this tree
  T leftHelper(T data);
  // gets the right side of this tree
  ABST<T> getRight();
  // helps getRight
  ABST<T> rightHelper(T data);
  // checks if this tree is the same as the given one
  boolean sameTree(ABST<T> tree);
  // checks if this node is the same as the given one
  boolean sameNode(Node<T> node);
  // checks if this leaf is the same as the given
  boolean sameLeaf(Leaf<T> leaf);
  // checks if this tree has the same data as the given one
  boolean sameData(ABST<T> tree);
  // searches this tree for the given data
  boolean inTree(ABST<T> node);
  // lists the items in this tree
  IList<T> buildList();
  // helps buildList
  IList<T> buildHelper(IList<T> list);
}

abstract class ABST<T> implements IBST<T> {
  Comparator<T> order;

  ABST(Comparator<T> order) {
    this.order = order;
  }

  /*
   * fields:
   *  this.order ... Comparator<T>
   * methods:
   *  none
   * methods for fields:
   *  this.order.compare(Book, Book) ... int
   */

}

class Leaf<T> extends ABST<T> {

  Leaf(Comparator<T> order) {
    super(order);
  }

  /*
   * fields:
   *  none
   * methods:
   *  this.insert(T) ... ABST<T>
   *  this.present(T) ... boolean
   *  this.getLeftmost() ... T
   *  this.leftHelper(T) ... T
   *  this.getRight() ... ABST<T>
   *  this.rightHelper(T) ... ABST<T>
   *  this.sameTree(ABST<T>) ... boolean
   *  this.sameLeaf(Leaf<T>) ... boolean
   *  this.sameNode(Node<T>) ... boolean
   *  this.sameData(ABST<T>) ... boolean
   *  this.inTree(ABST<T>) ... boolean
   *  this.buildList() ... IList<T>
   *  this.buildHelper(IList<T>) ... IList<T>
   * methods for fields:
   *  none
   */

  // inserts an item into the correct place
  public ABST<T> insert(T data) {
    return new Node<T>(this.order, data, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }

  // checks if an item in this tree has the given data
  public boolean present(T data) {
    return false;
  }

  // gets the leftmost item in this tree
  public T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  // checks if this item is the leftmost item in this tree
  public T leftHelper(T data) {
    return data;
  }

  // gets the right side of this tree
  public ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  // helps getRight
  public ABST<T> rightHelper(T data) {
    return new Leaf<T>(this.order);
  }

  // checks if this tree is the same as the given one
  public boolean sameTree(ABST<T> tree) {

    /*
     * fields of tree:
     *  tree.order ... Comparator<T>
     * methods of tree:
     *  tree.insert(T) ... ABST<T>
     *  tree.present(T) ... boolean
     *  tree.getLeftmost() ... T
     *  tree.leftHelper(T) ... T
     *  tree.getRight() ... ABST<T>
     *  tree.rightHelper(T) ... ABST<T>
     *  tree.sameTree(ABST<T>) ... boolean
     *  tree.sameLeaf(Leaf<T>) ... boolean
     *  tree.sameNode(Node<T>) ... boolean
     *  tree.sameData(ABST<T>) ... boolean
     *  tree.inTree(ABST<T>) ... boolean
     *  tree.buildList() ... IList<T>
     *  tree.buildHelper(IList<T>) ... IList<T>
     */

    return tree.sameLeaf(this);
  }

  // checks if this leaf is the same as the given one
  public boolean sameLeaf(Leaf<T> leaf) {

    /*
     * fields of leaf:
     *  leaf.order ... Comparator<T>
     * methods of leaf:
     *  leaf.insert(T) ... ABST<T>
     *  leaf.present(T) ... boolean
     *  leaf.getLeftmost() ... T
     *  leaf.leftHelper(T) ... T
     *  leaf.getRight() ... ABST<T>
     *  leaf.rightHelper(T) ... ABST<T>
     *  leaf.sameTree(ABST<T>) ... boolean
     *  leaf.sameLeaf(Leaf<T>) ... boolean
     *  leaf.sameNode(Node<T>) ... boolean
     *  leaf.sameData(ABST<T>) ... boolean
     *  leaf.inTree(ABST<T>) ... boolean
     *  leaf.buildList() ... IList<T>
     *  leaf.buildHelper(IList<T>) ... IList<T>
     */

    return true;
  }

  // checks if this node is the same as the given one
  public boolean sameNode(Node<T> node) {

    /*
     * fields of node:
     *  node.order ... Comparator<T>
     *  node.data ... T
     *  node.left ... ABST<T>
     *  node.right ... ABST<T>
     * methods of node:
     *  node.insert(T) ... ABST<T>
     *  node.present(T) ... boolean
     *  node.getLeftmost() ... T
     *  node.leftHelper(T) ... T
     *  node.getRight() ... ABST<T>
     *  node.rightHelper(T) ... ABST<T>
     *  node.sameTree(ABST<T>) ... boolean
     *  node.sameLeaf(Leaf<T>) ... boolean
     *  node.sameNode(Node<T>) ... boolean
     *  node.sameData(ABST<T>) ... boolean
     *  node.inTree(ABST<T>) ... boolean
     *  node.buildList() ... IList<T>
     *  node.buildHelper(IList<T>) ... IList<T>
     */

    return false;
  }

  // checks if this tree has the same data as the given one
  public boolean sameData(ABST<T> tree) {

    /*
     * fields of tree:
     *  tree.order ... Comparator<T>
     * methods of tree:
     *  tree.insert(T) ... ABST<T>
     *  tree.present(T) ... boolean
     *  tree.getLeftmost() ... T
     *  tree.leftHelper(T) ... T
     *  tree.getRight() ... ABST<T>
     *  tree.rightHelper(T) ... ABST<T>
     *  tree.sameTree(ABST<T>) ... boolean
     *  tree.sameLeaf(Leaf<T>) ... boolean
     *  tree.sameNode(Node<T>) ... boolean
     *  tree.sameData(ABST<T>) ... boolean
     *  tree.inTree(ABST<T>) ... boolean
     *  tree.buildList() ... IList<T>
     *  tree.buildHelper(IList<T>) ... IList<T>
     */

    return tree.inTree(this)
        && this.inTree(tree);
  }

  // searches this tree for the given data
  public boolean inTree(ABST<T> node) {

    /*
     * fields of node:
     *  node.order ... Comparator<T>
     * methods of tree:
     *  node.insert(T) ... ABST<T>
     *  node.present(T) ... boolean
     *  node.getLeftmost() ... T
     *  node.leftHelper(T) ... T
     *  node.getRight() ... ABST<T>
     *  node.rightHelper(T) ... ABST<T>
     *  node.sameTree(ABST<T>) ... boolean
     *  node.sameLeaf(Leaf<T>) ... boolean
     *  node.sameNode(Node<T>) ... boolean
     *  node.sameData(ABST<T>) ... boolean
     *  node.inTree(ABST<T>) ... boolean
     *  node.buildList() ... IList<T>
     *  node.buildHelper(IList<T>) ... IList<T>
     */

    return true;
  }

  // lists the items in this tree
  public IList<T> buildList() {
    return new MtList<T>();
  }

  // helps buildList
  public IList<T> buildHelper(IList<T> list) {

    /*
     * fields of list:
     *  list.first ... T
     *  list.rest ... IList<T>
     * methods of list:
     *  list.reverse() ... IList<T>
     *  list.reverseHelper(IList<T>) ... IList<T>
     */

    return list;
  }

}

class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /*
   * fields:
   *  this.data ... T
   *  this.left ... ABST<T>
   *  this.right ... ABST<T>
   * methods:
   *  this.insert(T) ... ABST<T>
   *  this.present(T) ... boolean
   *  this.getLeftmost() ... T
   *  this.leftHelper(T) ... T
   *  this.getRight() ... ABST<T>
   *  this.rightHelper(T) ... ABST<T>
   *  this.sameTree(ABST<T>) ... boolean
   *  this.sameLeaf(Leaf<T>) ... boolean
   *  this.sameNode(Node<T>) ... boolean
   *  this.sameData(ABST<T>) ... boolean
   *  this.inTree(ABST<T>) ... boolean
   *  this.buildList() ... IList<T>
   *  this.buildHelper(IList<T>) ... IList<T>
   * methods for fields:
   *  this.left.insert(T) ... ABST<T>
   *  this.left.present(T) ... boolean
   *  this.left.getLeftmost() ... T
   *  this.left.leftHelper(T) ... T
   *  this.left.getRight() ... ABST<T>
   *  this.left.rightHelper(T) ... ABST<T>
   *  this.left.sameTree(ABST<T>) ... boolean
   *  this.left.sameLeaf(Leaf<T>) ... boolean
   *  this.left.sameNode(Node<T>) ... boolean
   *  this.left.sameData(ABST<T>) ... boolean
   *  this.left.inTree(ABST<T>) ... boolean
   *  this.left.buildList() ... IList<T>
   *  this.left.buildHelper(IList<T>) ... IList<T>
   *  this.right.insert(T) ... ABST<T>
   *  this.right.present(T) ... boolean
   *  this.right.getLeftmost() ... T
   *  this.right.leftHelper(T) ... T
   *  this.right.getRight() ... ABST<T>
   *  this.right.rightHelper(T) ... ABST<T>
   *  this.right.sameTree(ABST<T>) ... boolean
   *  this.right.sameLeaf(Leaf<T>) ... boolean
   *  this.right.sameNode(Node<T>) ... boolean
   *  this.right.sameData(ABST<T>) ... boolean
   *  this.right.inTree(ABST<T>) ... boolean
   *  this.right.buildList() ... IList<T>
   *  this.right.buildHelper(IList<T>) ... IList<T>
   */

  // inserts an item into the correct place
  public ABST<T> insert(T data) {
    if (this.order.compare(this.data, data) > 1) {
      return new Node<T>(this.order, this.data, this.left.insert(data), this.right);
    } else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(data));
    }
  }

  // checks if an item in this tree has the given data
  public boolean present(T data) {
    return (this.order.compare(data, this.data) == 0) 
        || this.left.present(data)
        || this.right.present(data);
  } 

  // gets the leftmost item in this tree
  public T getLeftmost() {
    return this.left.leftHelper(this.data);
  }

  // checks if this item is the leftmost item in this tree
  public T leftHelper(T data) {
    return this.left.leftHelper(this.data);
  }

  // gets the right side of this tree
  public ABST<T> getRight() {
    return this.rightHelper(this.getLeftmost());
  }

  // helps getRight
  public ABST<T> rightHelper(T leftmost) {
    if (this.order.compare(this.data, leftmost) == 0) {
      return new Leaf<T>(this.order);
    } else {
      return new Node<T>(this.order, this.data, this.left.rightHelper(leftmost), this.right.rightHelper(leftmost));
    } 
  }

  // checks if this tree is the same as the given one
  public boolean sameTree(ABST<T> tree) {

    /*
     * fields of tree:
     *  tree.order ... Comparator<T>
     * methods of tree:
     *  tree.insert(T) ... ABST<T>
     *  tree.present(T) ... boolean
     *  tree.getLeftmost() ... T
     *  tree.leftHelper(T) ... T
     *  tree.getRight() ... ABST<T>
     *  tree.rightHelper(T) ... ABST<T>
     *  tree.sameTree(ABST<T>) ... boolean
     *  tree.sameLeaf(Leaf<T>) ... boolean
     *  tree.sameNode(Node<T>) ... boolean
     *  tree.sameData(ABST<T>) ... boolean
     *  tree.inTree(ABST<T>) ... boolean
     *  tree.buildList() ... IList<T>
     *  tree.buildHelper(IList<T>) ... IList<T>
     */

    return tree.sameNode(this);
  }

  // checks if this node is the same as the given one
  public boolean sameNode(Node<T> node) {

    /*
     * fields of node:
     *  node.order ... Comparator<T>
     *  node.data ... T
     *  node.left ... ABST<T>
     *  node.right ... ABST<T>
     * methods of node:
     *  node.insert(T) ... ABST<T>
     *  node.present(T) ... boolean
     *  node.getLeftmost() ... T
     *  node.leftHelper(T) ... T
     *  node.getRight() ... ABST<T>
     *  node.rightHelper(T) ... ABST<T>
     *  node.sameTree(ABST<T>) ... boolean
     *  node.sameLeaf(Leaf<T>) ... boolean
     *  node.sameNode(Node<T>) ... boolean
     *  node.sameData(ABST<T>) ... boolean
     *  node.inTree(ABST<T>) ... boolean
     *  node.buildList() ... IList<T>
     *  node.buildHelper(IList<T>) ... IList<T>
     */

    return (node.order.compare(this.data, node.data) == 0)
        && this.left.sameTree(node.left)
        && this.right.sameTree(node.right);
  }

  // checks if this leaf is the same as the given one
  public boolean sameLeaf(Leaf<T> leaf) {

    /*
     * fields of leaf:
     *  leaf.order ... Comparator<T>
     * methods of leaf:
     *  leaf.insert(T) ... ABST<T>
     *  leaf.present(T) ... boolean
     *  leaf.getLeftmost() ... T
     *  leaf.leftHelper(T) ... T
     *  leaf.getRight() ... ABST<T>
     *  leaf.rightHelper(T) ... ABST<T>
     *  leaf.sameTree(ABST<T>) ... boolean
     *  leaf.sameLeaf(Leaf<T>) ... boolean
     *  leaf.sameNode(Node<T>) ... boolean
     *  leaf.sameData(ABST<T>) ... boolean
     *  leaf.inTree(ABST<T>) ... boolean
     *  leaf.buildList() ... IList<T>
     *  leaf.buildHelper(IList<T>) ... IList<T>
     */

    return false;
  }

  // checks if this tree has the same data as the given one
  public boolean sameData(ABST<T> tree) {

    /*
     * fields of tree:
     *  tree.order ... Comparator<T>
     * methods of tree:
     *  tree.insert(T) ... ABST<T>
     *  tree.present(T) ... boolean
     *  tree.getLeftmost() ... T
     *  tree.leftHelper(T) ... T
     *  tree.getRight() ... ABST<T>
     *  tree.rightHelper(T) ... ABST<T>
     *  tree.sameTree(ABST<T>) ... boolean
     *  tree.sameLeaf(Leaf<T>) ... boolean
     *  tree.sameNode(Node<T>) ... boolean
     *  tree.sameData(ABST<T>) ... boolean
     *  tree.inTree(ABST<T>) ... boolean
     *  tree.buildList() ... IList<T>
     *  tree.buildHelper(IList<T>) ... IList<T>
     */

    return tree.inTree(this)
        && this.inTree(tree);
  }

  // searches this tree for the given data
  public boolean inTree(ABST<T> node) {

    /*
     * fields of node:
     *  node.order ... Comparator<T>
     * methods of tree:
     *  node.insert(T) ... ABST<T>
     *  node.present(T) ... boolean
     *  node.getLeftmost() ... T
     *  node.leftHelper(T) ... T
     *  node.getRight() ... ABST<T>
     *  node.rightHelper(T) ... ABST<T>
     *  node.sameTree(ABST<T>) ... boolean
     *  node.sameLeaf(Leaf<T>) ... boolean
     *  node.sameNode(Node<T>) ... boolean
     *  node.sameData(ABST<T>) ... boolean
     *  node.inTree(ABST<T>) ... boolean
     *  node.buildList() ... IList<T>
     *  node.buildHelper(IList<T>) ... IList<T>
     */

    return node.present(this.data)
        && this.left.inTree(node)
        && this.right.inTree(node);
  }

  // lists the items in this tree
  public IList<T> buildList() {
    return this.buildHelper(new MtList<T>()).reverse();
  }

  // accumulates data for buildList
  public IList<T> buildHelper(IList<T> list) {

    /*
     * fields of list:
     *  list.first ... T
     *  list.rest ... IList<T>
     * methods of list:
     *  list.reverse() ... IList<T>
     *  list.reverseHelper(IList<T>) ... IList<T>
     */

    return this.right.buildHelper(new ConsList<T>(this.data, this.left.buildHelper(list)));
  }
}


class ExamplesBooks {
  ExamplesBooks() {};

  // list of books
  Book gatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 20);
  Book button = new Book("The Curious Case of Benjamin Button", "F. Scott Fitzgerald", 25);
  Book catcher = new Book("Catcher in the Rye", "J.D. Salinger", 10);
  Book prejudice = new Book("Pride and Prejudice", "Jane Austen", 80);
  Book mockingbird = new Book("To Kill a Mockingbird", "Harper Lee", 45);
  Book watchman = new Book("Go Set a Watchman", "Harper Lee", 30);
  Book eightyfour = new Book("1984", "George Orwell", 20);
  Book animal = new Book("Animal Farm", "George Orwell", 55);
  Book it = new Book("It", "Stephen King", 70);
  Book misery = new Book("Misery", "Stephen King", 40);
  Book hamlet = new Book("Hamlet", "William Shakespeare", 60);
  Book romeo = new Book("Romeo and Juliet", "William Shakespeare", 50);
  Book bartleby = new Book("Bartleby, the Scrivener", "Herman Melville", 10000);

  /*
   * TREES THAT USE PRICE
   * V V V V V V V V V V V
   */

  // make a tree for books by price
  // level 3
  ABST<Book> leaf = new Leaf<Book>(new BooksByPrice());
  ABST<Book> n3_1 = new Node<Book>(new BooksByPrice(), this.watchman, this.leaf, this.leaf);
  ABST<Book> n3_3 = new Node<Book>(new BooksByPrice(), this.animal, this.leaf, this.leaf);
  ABST<Book> n3_4 = new Node<Book>(new BooksByPrice(), this.it, this.leaf, this.leaf);
  // level 2
  ABST<Book> n2_1 = new Node<Book>(new BooksByPrice(), this.misery, this.n3_1, this.leaf);
  ABST<Book> n2_2 = new Node<Book>(new BooksByPrice(), this.hamlet, this.n3_3, this.n3_4);
  // level 1
  ABST<Book> n1_1 = new Node<Book>(new BooksByPrice(), this.romeo, this.n2_1, this.n2_2);

  // price tree for testInsert
  // level 4
  ABST<Book> n4_1_insert = new Node<Book>(new BooksByPrice(), this.gatsby, this.leaf, this.leaf);  
  // level 3
  ABST<Book> n3_1_insert = new Node<Book>(new BooksByPrice(), this.watchman, this.n4_1_insert, this.leaf);
  ABST<Book> n3_3_insert = new Node<Book>(new BooksByPrice(), this.animal, this.leaf, this.leaf);
  ABST<Book> n3_4_insert = new Node<Book>(new BooksByPrice(), this.it, this.leaf, this.leaf);
  // level 2
  ABST<Book> n2_1_insert = new Node<Book>(new BooksByPrice(), this.misery, this.n3_1_insert, this.leaf);
  ABST<Book> n2_2_insert = new Node<Book>(new BooksByPrice(), this.hamlet, this.n3_3_insert, this.n3_4_insert);
  // level 1
  ABST<Book> n1_1_insert = new Node<Book>(new BooksByPrice(), this.romeo, this.n2_1_insert, this.n2_2_insert);

  //price tree for getRight
  ABST<Book> n2_2_right_1 = new Node<Book>(new BooksByPrice(), this.hamlet, this.leaf, this.n3_4);

  // price tree for getRight
  // level 3
  ABST<Book> n3_3_right = new Node<Book>(new BooksByPrice(), this.animal, this.leaf, this.leaf);
  ABST<Book> n3_4_right = new Node<Book>(new BooksByPrice(), this.it, this.leaf, this.leaf);
  // level 2
  ABST<Book> n2_1_right = new Node<Book>(new BooksByPrice(), this.misery, this.leaf, this.leaf);
  ABST<Book> n2_2_right = new Node<Book>(new BooksByPrice(), this.hamlet, this.n3_3_right, this.n3_4_right);
  // level 1
  ABST<Book> n1_1_right = new Node<Book>(new BooksByPrice(), this.romeo, this.n2_1_right, this.n2_2_right);

  /*
   * TREES THAT USE TITLE
   * V V V V V V V V V V V
   */

  // make a tree by book title
  // level 3
  ABST<Book> leaf_title = new Leaf<Book>(new BooksByTitle());
  ABST<Book> n3_1_title = new Node<Book>(new BooksByTitle(), this.animal, this.leaf_title, this.leaf_title);
  ABST<Book> n3_2_title = new Node<Book>(new BooksByTitle(), this.hamlet, this.leaf_title, this.leaf_title);
  ABST<Book> n3_3_title = new Node<Book>(new BooksByTitle(), this.romeo, this.leaf_title, this.leaf_title);
  ABST<Book> n3_4_title = new Node<Book>(new BooksByTitle(), this.mockingbird, this.leaf_title, this.leaf_title);
  // level 2
  ABST<Book> n2_1_title = new Node<Book>(new BooksByTitle(), this.watchman, this.n3_1_title, this.n3_2_title);
  ABST<Book> n2_2_title = new Node<Book>(new BooksByTitle(), this.gatsby, this.n3_3_title, this.n3_4_title);
  // level 1
  ABST<Book> n1_1_title = new Node<Book>(new BooksByTitle(), this.misery, this.n2_1_title, this.n2_2_title);

  // title tree for testInsert
  // level 4
  ABST<Book> n4_4_title_insert = new Node<Book>(new BooksByTitle(), this.it, this.leaf_title, this.leaf_title);
  // level 3
  ABST<Book> n3_2_title_insert = new Node<Book>(new BooksByTitle(), this.hamlet, this.leaf_title, this.n4_4_title_insert);
  // level 2
  ABST<Book> n2_1_title_insert = new Node<Book>(new BooksByTitle(), this.watchman, this.n3_1_title, this.n3_2_title_insert);
  // level 1
  ABST<Book> n1_1_title_insert = new Node<Book>(new BooksByTitle(), this.misery, this.n2_1_title_insert, this.n2_2_title);

  // title tree for getRight
  // level 2
  ABST<Book> n2_1_title_getRight = new Node<Book>(new BooksByTitle(), this.watchman, this.leaf_title, this.n3_2_title);
  // level 1
  ABST<Book> n1_1_title_getRight = new Node<Book>(new BooksByTitle(), this.misery, this.n2_1_title_getRight, this.n2_2_title);

  /*
   * TREES THAT USE AUTHOR
   * V V V V V V V V V V V
   */

  // make tree by author title
  // level 4
  ABST<Book> leaf_author = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> n4_1_author = new Node<Book>(new BooksByAuthor(), this.button, this.leaf_author, this.leaf_author);
  // level 3
  ABST<Book> n3_1_author = new Node<Book>(new BooksByAuthor(), this.eightyfour, this.n4_1_author, this.leaf_author);
  ABST<Book> n3_4_author = new Node<Book>(new BooksByAuthor(), this.hamlet, this.leaf_author, this.leaf_author);
  // level 2
  ABST<Book> n2_1_author = new Node<Book>(new BooksByAuthor(), this.watchman, this.n3_1_author, this.leaf_author);
  ABST<Book> n2_2_author = new Node<Book>(new BooksByAuthor(), this.catcher, this.leaf_author, this.n3_4_author);
  // level 1
  ABST<Book> n1_1_author = new Node<Book>(new BooksByAuthor(), this.prejudice, this.n2_1_author, this.n2_2_author);

  // author tree for testInsert -> adds bartleby
  // level 4
  ABST<Book> n4_1_author_insert = new Node<Book>(new BooksByAuthor(), this.button, this.leaf_author, this.leaf_author);
  // level 3
  ABST<Book> n3_1_author_insert = new Node<Book>(new BooksByAuthor(), this.eightyfour, this.n4_1_author_insert, this.leaf_author);
  ABST<Book> n3_2_author_insert = new Node<Book>(new BooksByAuthor(), this.bartleby, this.leaf_author, this.leaf_author);
  ABST<Book> n3_4_author_insert = new Node<Book>(new BooksByAuthor(), this.hamlet, this.leaf_author, this.leaf_author);
  // level 2
  ABST<Book> n2_1_author_insert = new Node<Book>(new BooksByAuthor(), this.watchman, this.n3_1_author_insert, this.n3_2_author_insert);
  ABST<Book> n2_2_author_insert = new Node<Book>(new BooksByAuthor(), this.catcher, this.leaf_author, this.n3_4_author_insert);
  // level 1
  ABST<Book> n1_1_author_insert = new Node<Book>(new BooksByAuthor(), this.prejudice, this.n2_1_author_insert, this.n2_2_author_insert);


  // title tree for getRight -> omits benjamin button
  // level 3
  ABST<Book> n3_1_author_getRight = new Node<Book>(new BooksByAuthor(), this.eightyfour, this.leaf_author, this.leaf_author);
  ABST<Book> n3_4_author_getRight = new Node<Book>(new BooksByAuthor(), this.hamlet, this.leaf_author, this.leaf_author);
  // level 2
  ABST<Book> n2_1_author_getRight = new Node<Book>(new BooksByAuthor(), this.watchman, this.n3_1_author_getRight, this.leaf_author);
  ABST<Book> n2_2_author_getRight = new Node<Book>(new BooksByAuthor(), this.catcher, this.leaf_author, this.n3_4_author_getRight);
  // level 1
  ABST<Book> n1_1_author_getRight = new Node<Book>(new BooksByAuthor(), this.prejudice, this.n2_1_author_getRight, this.n2_2_author_getRight);

  // tests for insert method
  boolean testInsert(Tester t) {
    return t.checkExpect(this.n1_1.insert(this.gatsby), this.n1_1_insert)
        && t.checkExpect(this.n1_1_title.insert(this.it), this.n1_1_title_insert)
        && t.checkExpect(this.n1_1_author.insert(this.bartleby), this.n1_1_author_insert)
        && t.checkExpect(this.leaf.insert(this.bartleby), new Node<Book>(this.leaf.order, this.bartleby, this.leaf, this.leaf))
        && t.checkExpect(this.leaf_title.insert(this.bartleby), new Node<Book>(this.leaf_title.order, this.bartleby, this.leaf_title, this.leaf_title))
        && t.checkExpect(this.leaf_author.insert(this.bartleby), new Node<Book>(this.leaf_author.order, this.bartleby, this.leaf_author, this.leaf_author));
  }

  // tests for present method
  boolean testPresent(Tester t) {
    return t.checkExpect(this.n1_1.present(this.misery), true)
        && t.checkExpect(this.n1_1.present(this.watchman), true)
        && t.checkExpect(this.n1_1.present(this.prejudice), false)
        && t.checkExpect(this.n1_1.present(this.gatsby), false)
        && t.checkExpect(this.leaf.present(this.misery), false)
        && t.checkExpect(this.n1_1_title.present(this.gatsby), true)
        && t.checkExpect(this.n1_1_title.present(this.eightyfour), false)
        && t.checkExpect(this.n1_1_title.present(this.prejudice), false)
        && t.checkExpect(this.leaf_title.present(this.eightyfour), false)
        && t.checkExpect(this.n1_1_author.present(this.eightyfour), true)
        && t.checkExpect(this.n1_1_author.present(this.misery), false)
        && t.checkExpect(this.n1_1_author.present(this.it), false)
        && t.checkExpect(this.leaf_author.present(this.eightyfour), false);
  }

  // tests for getLeftmost
  boolean testGetLeftmost(Tester t) {
    return t.checkExpect(this.n1_1.getLeftmost(), this.watchman)
        && t.checkExpect(this.n2_2.getLeftmost(), this.animal)
        && t.checkExpect(this.n3_4.getLeftmost(), this.it)
        && t.checkExpect(this.n1_1_title.getLeftmost(), this.animal)
        && t.checkExpect(this.n2_2_title.getLeftmost(), this.romeo)
        && t.checkExpect(this.n3_4_title.getLeftmost(), this.mockingbird)
        && t.checkExpect(this.n1_1_author.getLeftmost(), this.button)
        && t.checkExpect(this.n2_2_author.getLeftmost(), this.catcher)
        && t.checkExpect(this.n3_1_author.getLeftmost(), this.button)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf, "getLeftmost")
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf_title, "getLeftmost")
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf_author, "getLeftmost");
  }

  // tests for leftHelper
  boolean testLeftHelper(Tester t) {
    return t.checkExpect(this.n1_1.leftHelper(this.romeo), this.watchman)
        && t.checkExpect(this.n2_2.leftHelper(this.hamlet), this.animal)
        && t.checkExpect(this.n3_4.leftHelper(this.it), this.it)
        && t.checkExpect(this.n1_1_title.leftHelper(this.misery), this.animal)
        && t.checkExpect(this.n2_2_title.leftHelper(this.gatsby), this.romeo)
        && t.checkExpect(this.n3_4_title.leftHelper(this.mockingbird), this.mockingbird)
        && t.checkExpect(this.n1_1_author.leftHelper(this.romeo), this.button)
        && t.checkExpect(this.n2_2_author.leftHelper(this.catcher), this.catcher)
        && t.checkExpect(this.n3_1_author.leftHelper(this.watchman), this.button)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf, "getLeftmost")
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf_title, "getLeftmost")
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf_author, "getLeftmost");
  }

  //tests for getRight
  boolean testGetRight(Tester t) {
    return t.checkExpect(this.n2_2.getRight(), this.n2_2_right_1)
        && t.checkExpect(this.n1_1.getRight(), this.n1_1_right)
        && t.checkExpect(this.n1_1_title.getRight(), this.n1_1_title_getRight)
        && t.checkExpect(this.n1_1_author.getRight(), this.n1_1_author_getRight)
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf, "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf_title, "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf_author, "getRight");
  }

  // tests for RightHelper
  boolean testRightHelper(Tester t) {
    return t.checkExpect(this.n2_2.rightHelper(this.animal), this.n2_2_right_1)
        && t.checkExpect(this.n1_1.rightHelper(this.watchman), this.n1_1_right)
        && t.checkExpect(this.n1_1_title.rightHelper(this.animal), this.n1_1_title_getRight)
        && t.checkExpect(this.n1_1_author.rightHelper(this.button), this.n1_1_author_getRight);
  }

  //tests for sameTree
  boolean testSameTree(Tester t) {
    return t.checkExpect(this.n2_2.sameTree(this.n2_2), true)
        && t.checkExpect(this.n2_2.sameTree(this.n1_1), false)
        && t.checkExpect(this.n2_2.sameTree(this.leaf), false)
        && t.checkExpect(this.n1_1_title.sameTree(this.n1_1_title), true)
        && t.checkExpect(this.n1_1_title.sameTree(this.n2_2_title), false)
        && t.checkExpect(this.n2_1_title.sameTree(this.n1_1_title), false)
        && t.checkExpect(this.n1_1_title.sameTree(this.leaf_title), false)
        && t.checkExpect(this.n1_1_author.sameTree(this.n1_1_author), true)
        && t.checkExpect(this.n1_1_author.sameTree(this.n2_2_author), false)
        && t.checkExpect(this.n2_1_author.sameTree(this.n1_1_author), false)
        && t.checkExpect(this.n1_1_author.sameTree(this.leaf_author), false);
  }

  //tests for sameNode
  boolean testSameNode(Tester t) {
    return t.checkExpect(this.n2_2.sameNode((Node<Book>)this.n2_2), true)
        && t.checkExpect(this.n2_2.sameNode((Node<Book>)this.n1_1), false)
        && t.checkExpect(this.n1_1_title.sameNode((Node<Book>)this.n1_1_title), true)
        && t.checkExpect(this.n1_1_title.sameNode((Node<Book>)this.n2_2_title), false)
        && t.checkExpect(this.n2_1_title.sameNode((Node<Book>)this.n1_1_title), false)
        && t.checkExpect(this.n1_1_author.sameNode((Node<Book>)this.n1_1_author), true)
        && t.checkExpect(this.n1_1_author.sameNode((Node<Book>)this.n2_2_author), false)
        && t.checkExpect(this.n2_1_author.sameNode((Node<Book>)this.n1_1_author), false);
  }

  //tests for sameLeaf
  boolean testSameLeaf(Tester t) {
    return t.checkExpect(this.leaf.sameLeaf((Leaf<Book>)this.leaf), true)
        && t.checkExpect(this.leaf_title.sameLeaf((Leaf<Book>)this.leaf_title), true)
        && t.checkExpect(this.leaf_author.sameLeaf((Leaf<Book>)this.leaf_author), true)
        && t.checkExpect(this.leaf.sameLeaf((Leaf<Book>)this.leaf_author), true)
        && t.checkExpect(this.leaf_title.sameLeaf((Leaf<Book>)this.leaf), true)
        && t.checkExpect(this.leaf_author.sameLeaf((Leaf<Book>)this.leaf_title), true);
  }

  //tests for sameData
  boolean testSameData(Tester t) {
    return t.checkExpect(this.n2_2.sameData(this.n2_2), true)
        && t.checkExpect(this.n2_2.sameData(this.n1_1), false)
        && t.checkExpect(this.n2_2.sameData(this.leaf), false)
        && t.checkExpect(this.leaf.sameData(this.n2_2), false)
        && t.checkExpect(this.n1_1_title.sameData(this.n1_1_title), true)
        && t.checkExpect(this.n1_1_title.sameData(this.n2_2_title), false)
        && t.checkExpect(this.n2_1_title.sameData(this.n1_1_title), false)
        && t.checkExpect(this.n1_1_title.sameData(this.leaf_title), false)
        && t.checkExpect(this.n1_1_author.sameData(this.n1_1_author), true)
        && t.checkExpect(this.n1_1_author.sameData(this.n2_2_author), false)
        && t.checkExpect(this.n2_1_author.sameData(this.n1_1_author), false)
        && t.checkExpect(this.n1_1_author.sameData(this.leaf_author), false);
  }

  //tests for inTree
  boolean testInTree(Tester t) {
    return t.checkExpect(this.n2_2.inTree(this.n2_2), true)
        && t.checkExpect(this.n2_2.inTree(this.n1_1), true)
        && t.checkExpect(this.n2_2.inTree(this.leaf), false)
        && t.checkExpect(this.n1_1_title.inTree(this.n1_1_title), true)
        && t.checkExpect(this.n1_1_title.inTree(this.n2_2_title), false)
        && t.checkExpect(this.n2_1_title.inTree(this.n1_1_title), true)
        && t.checkExpect(this.n1_1_title.inTree(this.leaf_title), false)
        && t.checkExpect(this.n1_1_author.inTree(this.n1_1_author), true)
        && t.checkExpect(this.n1_1_author.inTree(this.n2_2_author), false)
        && t.checkExpect(this.n2_1_author.inTree(this.n1_1_author), true)
        && t.checkExpect(this.n1_1_author.inTree(this.leaf_author), false);
  }

  //tests for buildList
  boolean testBuildList(Tester t) {
    return t.checkExpect(this.n2_2.buildList(),
        new ConsList<Book>(this.animal,
            new ConsList<Book>(this.hamlet,
                new ConsList<Book>(this.it,
                    new MtList<Book>()))))
        && t.checkExpect(this.n2_1_title.buildList(),
            new ConsList<Book>(this.animal,
                new ConsList<Book>(this.watchman,
                    new ConsList<Book>(this.hamlet,
                        new MtList<Book>()))))
        && t.checkExpect(this.n2_1_author.buildList(),
            new ConsList<Book>(this.button,
                new ConsList<Book>(this.eightyfour,
                    new ConsList<Book>(this.watchman,
                        new MtList<Book>()))))
        && t.checkExpect(this.leaf.buildList(), new MtList<Book>());
  }

  //tests for buildHelper
  boolean testBuildHelper(Tester t) {
    return t.checkExpect(this.n2_2.buildHelper(new MtList<Book>()),
        new ConsList<Book>(this.it,
            new ConsList<Book>(this.hamlet,
                new ConsList<Book>(this.animal,
                    new MtList<Book>()))))
        && t.checkExpect(this.n2_1_title.buildHelper(new MtList<Book>()),
            new ConsList<Book>(this.hamlet,
                new ConsList<Book>(this.watchman,
                    new ConsList<Book>(this.animal,
                        new MtList<Book>()))))
        && t.checkExpect(this.n2_1_author.buildHelper(new MtList<Book>()),
            new ConsList<Book>(this.watchman,
                new ConsList<Book>(this.eightyfour,
                    new ConsList<Book>(this.button,
                        new MtList<Book>()))))
        && t.checkExpect(this.leaf.buildHelper(new MtList<Book>()), new MtList<Book>());
  }
}