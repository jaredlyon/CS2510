import tester.Tester;
import java.util.Comparator;

class Book {
  String title, author;
  int price;
  
  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
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
}


class ExamplesBooks {
  
  Book gatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 20);
  Book button = new Book("The Curious Case of Benjamin Button", "F. Scott Fitzgerald", 25);
  Book catcher = new Book("Catcher in the Rye", "J.D. Salinger", 10);
  Book prejudice = new Book("Pride and Prejudice", "Jane Austen", 40);
  Book mockingbird = new Book("To Kill a Mockingbird", "Harper Lee", 45);
  Book watchman = new Book("Go Set a Watchman", "Harper Lee", 10);
  Book eightyfour = new Book("1984", "George Orwell", 20);
  Book animal = new Book("Animal Farm", "George Orwell", 15);
  Book it = new Book("It", "Stephen King", 60);
  Book misery = new Book("Misery", "Stephen King", 45);
  Book hamlet = new Book("Hamlet", "William Shakespeare", 35);
  Book romeo = new Book("Romeo and Juliet", "William Shakespeare", 40);
  
}