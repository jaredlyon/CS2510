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
}


class Sentinel<T> extends ANode<T>{
  
  Sentinel() {
    this.next = this;
    this.prev = this;
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
  
  // adds a node to the head of the deque
  Deque<T> addAtHead() {
    return null;
  }
  
  // adds a node to the tail of the deque
  Deque<T> addAtTail() {
    return null;
  }
  
  // removes a node from the head of the deque
  Deque<T> removeFromHead() {
    return null;
  }
  
  // removes a node from the tail of the deque.
  Deque<T> removeFromTail() {
    return null;
  }
  
  // finds the node that passed the given predicate
  Node<T> find(Predicate<T> pred) {
    return null;
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