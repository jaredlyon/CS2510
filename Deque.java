import java.util.function.Predicate;

import tester.Tester;

abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;
  
}


class Node<T> extends ANode<T> {
  T data;

  
  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }
  
  Node(T data, ANode<T> next, ANode<T> prev) {
    if(this.next == null) {
      throw new IllegalArgumentException("Next node is null");
    }
    this.next = next;
    if(this.prev == null) {
      throw new IllegalArgumentException("Prev node is null");
    }
    this.prev = prev;
    this.data = data;
    this.next.prev = this;
    this.prev.next = this;
    
  }
  
  
  // connects the next and given nodes through next and previous
  void connectNext(ANode<T> node) {
    this.next = node;
    node.prev = this;
  }
  
  // connects the previous and given nodes through next and previous
  void connectPrev(ANode<T> node) {
    this.prev = node;
    node.next = this;
  }
  
  
  // finds node that passes the predicate
  Node<T> findNode(Predicate<T> pred) {
    if (pred.apply(this)) {
      return this;
    }
    else {
      return this.next.findNode(pred);
    }
  }
  
}


class Sentinel<T> extends ANode<T>{
  
  Sentinel() {
    this.next = this;
    this.prev = this;
  }
  
  // inserts the given node into the sentinel
  void insertNext(ANode<T> node) {
    this.next.connectNext(node);
    this.next = node;
  }
  
  // inserts the given node into the sentinel
  void insertPrev(ANode<T> node) {
    this.prev.connectPrev(node);
    this.prev = node;
  }
  
  // removes node from the head of the deque
  void removeHead() {
    this.next = this.next.next;               // this is really ugly code, it needs a helper to declutter
    this.next.next.prev = this;  
  }
  
  // removes node from the tail of the deque
  void removeTail() {
    this.prev = this.prev.prev;              // this is really ugly code, it needs a helper to declutter
    this.prev.prev.next = this;
  }
  
  // finds the node that passed the predicate
  Node<T> findNode(Predicate<T> pred) {                         // if the findNode method ends back up at sentinel, give exception
    return new RuntimeException("No such node exists");
  }

}


class Deque<T> {
  Sentinel<T> header;
  
  Deque(Sentinel<T> header) {
    this.header = header;
  }
  
  Deque() {
    new Sentinel<T>();
  }
  
  
  // returns number of nodes in a deque
  int size() {
    return 0;
  }
  
  // CHANGE THE NEXT AND PREV OF EACH TO SKIP OR ADD NEW NODE
  

  
  
  // adds a node to the head of the deque
  void addAtHead(T data) {
    Node<T> node = new Node<T>(data);
    this.header.insertNext(node);
  }
  
  // adds a node to the tail of the deque
  void addAtTail(T data) {
    Node<T> node = new Node<T>(data);
    this.header.insertPrev(node);
  }
  
  // removes a node from the head of the deque
  void removeFromHead() {
    this.header.removeHead();
  }
  
  // removes a node from the tail of the deque.
  void removeFromTail() {
    this.header.removeTail();
  }
  
  // finds the node that passed the given predicate
  Node<T> find(Predicate<T> pred) {
    return this.header.next.findNode(pred);                     // field of a field
  }
  
  // removes the given node from the deque
  Deque<T> removeNode(Node<T> node) {
    return null;
  }
}

class ExamplesDeque {
  
  ANode<String> sent;
  ANode<String> abc;
  ANode<String> bcd;
  ANode<String> cde;
  ANode<String> def;
  
  void initData() {
    this.sent = new Sentinel<String>();
    this.abc = new Node<String>("abc");
    this.bcd = new Node<String>("bcd");
    this.cde = new Node<String>("cde");
    this.def = new Node<String>("def");
    
    this.sent.next = this.abc;
    this.sent.prev = this.def;
    this.def.prev = this.cde;
    this.cde.prev = this.bcd;
    this.bcd.prev = this.abc;
  }
}