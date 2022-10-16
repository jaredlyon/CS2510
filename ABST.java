import tester.Tester;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import tester.Tester;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Comparator;

interface IList<T> {
  
}

class MtList<T> implements IList<T> {
  MtList() {};

}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
  
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
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

  // checks if this book is the same the given book
  public boolean sameBook(Book bk) {
    return this.title.equals(bk.title)
        && this.author.equals(bk.title)
        && this.price == bk.price;
  }
}

class BooksByTitle implements Comparator<Book> {

  public int compare(Book b1, Book b2) {
    return b1.title.compareTo(b2.title);
  }

}

class BooksByAuthor implements Comparator<Book> {

  public int compare(Book b1, Book b2) {
    return b1.author.compareTo(b2.author);
  }

}

class BooksByPrice implements Comparator<Book> {

  public int compare(Book b1, Book b2) {
    return b1.price - b2.price;
  }

}

interface IBST<T> {
  // inserts an item into the correct place
  ABST<T> insert(Comparator<T> comp, T data);
  // checks if an item in this tree has the given data
  boolean present(T data);
  // gets the leftmost item in this tree
  T getLeftmost();
  // checks if this item is the leftmost item in this tree
  T leftHelper(T data);
  // gets the right side of this tree
  ABST<T> getRight();
  // checks if this tree is the same as the given one
  boolean sameTree(ABST<T> tree);
  // checks if this node is the same as the given one
  boolean sameNode(ABST<T> node);
  // checks if this tree has the same data as the given one
  boolean sameData(ABST<T> tree);
  // lists the items in this tree
  IList<T> buildList();
  // helps buildList
  IList<T> buildHelper();

}

abstract class ABST<T> implements IBST<T> {
  Comparator<T> order;

  ABST(Comparator<T> order) {
    this.order = order;
  }

}

class Leaf<T> extends ABST<T> {

  Leaf(Comparator<T> order) {
    super(order);
  }

  // inserts an item into the correct place
  public ABST<T> insert(Comparator<T> comp, T data) {
    return this;
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

  // checks if this tree is the same as the given one
  public boolean sameTree(ABST<T> tree) {
    return true;
  }

  // checks if this node is the same as the given one
  public boolean sameNode(ABST<T> node) {
    return true;
  }

  // checks if this tree has the same data as the given one
  public boolean sameData(ABST<T> tree) {
    return true;
  }
  
  // searches this tree for the given data
  public boolean inTree(ABST<T> tree) {
    return true;
  }

  // lists the items in this tree
  public IList<T> buildList() {
    return new MtList<T>();
  }
  
  // helps buildList
  public IList<T> buildHelper() {
    return new ConsList<T>(this.left.buildList(), this.right.buildList());
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

  // inserts an item into the correct place
  public ABST<T> insert(T data) {
    if (this.order.compare(this.data, data) < 1) {
      return new Node<T>(this.order, this.data, this.left.insert(data), this.right);
    } else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(data));
    }
  }

  // checks if an item in this tree has the given data
  public boolean present(T data) {
    return (this.order.compare(this.data, data) == 0) 
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
    if (this.left.leftHelper()) {
      return new Leaf<T>(this.order);
    } else {
      return new Node<T>(this.order, this.data, this.left.getRight(), this.right);
    }
  }

  // checks if this tree is the same as the given one
  public boolean sameTree(ABST<T> tree) {
    return this.sameNode(tree);
  }

  // checks if this node is the same as the given one
  public boolean sameNode(ABST<T> node) {
    return this.data.sameBook(node.data)
        && this.left.sameNode(node.left)
        && this.right.sameNode(node.right);
  }

  // checks if this tree has the same data as the given one
  public boolean sameData(ABST<T> tree) {
    return this.inTree(tree)
        && this.left.sameData(tree)
        && this.right.sameData(tree);
  }
  
  // searches this tree for the given data
  public boolean inTree(ABST<T> tree) {
    return this.data.sameBook(tree)
        || this.inTree(tree)
  }

  // lists the items in this tree
  public IList<T> buildList() {
    return new ConsList<T>(this.data, this.buildHelper());
  }
  
  // helps buildList
  public IList<T> buildHelper() {
    return new ConsList<T>(this.left.buildList(), this.right.buildList());
  }
}


class ExamplesBooks {
  ExamplesBooks() {};

  // list of books
  Book gatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 20);
  Book button = new Book("The Curious Case of Benjamin Button", "F. Scott Fitzgerald", 25);
  Book catcher = new Book("Catcher in the Rye", "J.D. Salinger", 10);
  Book prejudice = new Book("Pride and Prejudice", "Jane Austen", 40);
  Book mockingbird = new Book("To Kill a Mockingbird", "Harper Lee", 45);
  Book watchman = new Book("Go Set a Watchman", "Harper Lee", 30);
  Book eightyfour = new Book("1984", "George Orwell", 20);
  Book animal = new Book("Animal Farm", "George Orwell", 55);
  Book it = new Book("It", "Stephen King", 70);
  Book misery = new Book("Misery", "Stephen King", 40);
  Book hamlet = new Book("Hamlet", "William Shakespeare", 60);
  Book romeo = new Book("Romeo and Juliet", "William Shakespeare", 50);

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


//  // tests for insert method
//  boolean testInsert(Tester t) {
//    return t.checkExpect(this.leaf.insert(new BooksByPrice(), this.catcher), this.nodeCatcher)
//        && t.checkExpect(this.n3_1.insert(new BooksByPrice(), this.catcher), 
//            new Node<Book>(new BooksByPrice(), this.watchman, this.nodeCatcher, this.leaf))
//        && t.checkExpect(this.n2_1.insert(new BooksByPrice(), this.mockingbird), 
//            new Node<Book>(new BooksByPrice(), this.misery, this.n3_1, this.nodeMockingbird));
//  }
//
//  // tests for present method
//  boolean testPresent(Tester t) {
//    return t.checkExpect(this.n1_1.present(this.misery), true)
//        && t.checkExpect(this.n1_1.present(this.watchman), true)
//        && t.checkExpect(this.n1_1.present(this.prejudice), false)
//        && t.checkExpect(this.n1_1.present(this.gatsby), false)
//        && t.checkExpect(this.leaf.present(this.misery), false);
//  }

  // tests for getLeftmost
  boolean testGetLeftmost(Tester t) {
    return t.checkExpect(this.n1_1.getLeftmost(), this.watchman)
        && t.checkExpect(this.n2_2.getLeftmost(), this.animal)
        && t.checkExpect(this.n3_4.getLeftmost(), this.it)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf, "getLeftmost");
  }

//  //tests for getRight
//  boolean testGetRight(Tester t) {
//    return t.checkExpect(this.n2_2.getRight(), this.it)
//        && t.checkException(this.leaf.getRight(), null, "No right of an empty tree", null);
//  }
//
//  //tests for sameTree
//  boolean testSameTree(Tester t) {
//    return t.checkExpect(this.n2_2.sameTree(this.n2_2), true)
//        && t.checkExpect(this.n2_2.sameTree(this.n1_1), false)
//        && t.checkExpect(this.n2_2.sameTree(this.leaf), false);
//  }
//
//  //tests for sameNode
//  boolean testSameNode(Tester t) {
//    return t.checkExpect(this.n2_2.sameNode(this.n2_2), true)
//        && t.checkExpect(this.n2_2.sameNode(this.n1_1), false)
//        && t.checkExpect(this.n2_2.sameNode(this.leaf), false);
//  }
//
//  //tests for sameData
//  boolean testSameData(Tester t) {
//    return t.checkExpect(this.n2_2.sameData(this.n2_2), true)
//        && t.checkExpect(this.n2_2.sameData(this.n1_1), false)
//        && t.checkExpect(this.n2_2.sameData(this.leaf), false);
//  }
//
//  //tests for buildList
//  boolean testBuildList(Tester t) {
//    return t.checkExpect(this.n2_2.buildList(),
//        new ConsList<Book>(this.animal,
//            new ConsList<Book>(this.hamlet,
//                new ConsList<Book>(this.it,
//                    new MtList<Book>()))))
//        && t.checkExpect(this.leaf.buildList(), new MtList<Book>());
//  }

}