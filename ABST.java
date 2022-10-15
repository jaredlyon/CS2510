import tester.Tester;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

interface ILoString {
  
}

interface IList<T> {
  IList<T> filter(Predicate<T> pred);
  <U> IList<U> map(Function<T,U> converter);
  <U> U fold(BiFunction<T,U,U> converter,U initial);
  IList<T> zip(IList<T> t, Function<T,T> zipper)
  IList<T> zipHelper(IList<T> t, Function<T,T> zipper);
  // sorts the list made by buildList
  IList<T> sortList(Comparator<T> comp);
  // insertion for the sorter
  IList<T> insertList(Comparator<T> comp, T data);
}

class MtList<T> implements IList<T> {
  
  MtList() {}

  @Override
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new MtList<U>();
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return initial;
  }

  @Override
  public IList<T> zip(IList<T> t, Function<T, T> zipper) {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }
  
  public IList<T> zipHelper(IList<T> t, Function<T, T> zipper) {
    return t;
  }
  
  // sorts the list made by buildList
  public IList<T> sortList(Comparator<T> comp) {
    return new MtList<T>();
  }
  
  // insertion for the sorter
  public IList<T> insertList(Comparator<T> comp, T data) {
    return new MtList<T>();
  }
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
  
  ConsList(T first,IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first,this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new ConsList<U>(converter.apply(this.first),this.rest.map(converter));
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return converter.apply(this.first, this.rest.fold(converter,initial));
  }

  @Override
  public IList<T> zip(IList<T> t, Function<T, T> zipper) {
    // TODO Auto-generated method stub
    return t.zipHelper(this, zipper);
  }
  
  public IList<T> zipHelper(IList<T> t, Function<T, T> zipper) {
    return new ConsList<T>(this.first.apply(this.first.t), this.rest.apply(this.rest.t));
  }
}

class TMonth implements Predicate<String> {
  boolean apply(String s) {
    return s.substring(0,1).equals("t");
  }

  @Override
  public boolean test(String t) {
    // TODO Auto-generated method stub
    return false;
  }
  
  // sorts the list made by buildList
  public IList<T> sortList(Comparator<T> comp) {
    return this.rest.sortList(comp).insertList(comp, this.first);
  }
  
  // insertion for the sorter
  public IList<T> insertList(Comparator<T> comp, T data) {
    if (comp.compare(this.first, data) < 0) {
      return new ConsList<T>(this.first, this.rest.insertList(comp, data));
    } else {
      return new ConsList<T>(data, this.rest);
    }
  }
}


/*
 * ABST CODE
 *  V V V V V V 
 */


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
  
  // lists the items in this tree
  public IList<T> buildList() {
    return new MtList<T>();
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
  public ABST<T> insert(Comparator<T> comp, T data) {
    if (comp.compare(this.data, data) < 1) {
      new Node<T>(comp, this.data, this.left.insert(comp, data), this.right);
    } else {
      new Node<T>(comp, this.data, this.left, this.right.insert(comp, data));
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
    if (this.left.isLeaf) {
      return this.data;
    } else {
      return this.left.getLeftmost();
    }
  }
  
  // gets the right side of this tree
  public ABST<T> getRight() {
    if (this.left.isLeaf) {
      return new Leaf<T>(this.order);
    } else {
      return new Node<T>(this.order, this.data, this.left.getRight(), this.right);
    }
  }
  
  // checks if this tree is the same as the given one
  public boolean sametree(ABST<T> tree) {
    return this.sameNode(tree)
        && this.left.sameTree(tree.left)
        && this.right.sameTree(tree.right);
  }
  
  // checks if this node is the same as the given one
  public boolean sameNode(ABST<T> node) {
    return this.data.sameBook(node.data)
        && this.left.sameNode(node.left)
        && this.right.sameNode(node.right);
  }
  
  // checks if this tree has the same data as the given one
  public boolean sameData(ABST<T> tree) {
    return this.data.sameBook(tree.data)
        && this.left.sameData(tree.left)
        && this.right.sameData(tree.right);
  }
  
  // lists the items in this tree
  public IList<T> buildList() {
    return new MtList<T>();
  }
  
  
}


class ExamplesBooks {
 
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
  
  
  // tests for insert method
  boolean testInsert(Tester t) {
    return t.checkExpect(this.leaf.insert(new BooksByPrice(), this.catcher), this.nodeCatcher)
        && t.checkExpect(this.n3_1.insert(new BooksByPrice(), this.catcher), 
            new Node<Book>(new BooksByPrice(), this.watchman, this.nodeCatcher, this.leaf))
        && t.checkExpect(this.n2_1.insert(new BooksByPrice(), this.mockingbird), 
            new Node<Book>(new BooksByPrice(), this.misery, this.n3_1, this.nodeMockingbird));
  }
  
  // tests for present method
  boolean testPresent(Tester t) {
    return t.checkExpect(this.n1_1.present(this.misery), true)
        && t.checkExpect(this.n1_1.present(this.watchman), true)
        && t.checkExpect(this.n1_1.present(this.prejudice), false)
        && t.checkExpect(this.n1_1.present(this.gatsby), false)
        && t.checkExpect(this.leaf.present(this.misery), false);
  }
  
  // tests for getLeftmost
  boolean testGetLeftmost(Tester t) {
    return t.checkExpect(this.n1_1.getLeftmost(), this.watchman)
        && t.checkExpect(this.n2_2.getLeftmost(), this.animal)
        && t.checkExpect(this.n3_4.getLeftmost(), this.it)
        && t.checkException(this.leaf.getLeftmost(), null, "No leftmost item of an empty tree", null);
  }
  
  
}