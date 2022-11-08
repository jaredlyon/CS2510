import java.util.function.Predicate;

import tester.Tester;



abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;
  
  
  // inserts the given node into the sentinel
  void insert(ANode<T> node) {
    this.prev.connect(node);
    this.prev = node;
    node.next = this;
    
  }
  
  // connects one side of the node into the deque
  void connect(ANode<T> node) {
    this.next = node;
    node.prev = this;
  }
  
  // removes the current node and connects the next and previous nodes
  void remove() {
    this.next.prev = this.prev;
    this.prev.next = this.next;
  }
  
  // finds the node that passed the predicate
  ANode<T> findNode(Predicate<T> pred) {
    return this;
  }
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
  
  
  // finds node that passes the predicate
  ANode<T> findNode(Predicate<T> pred) {
    if (pred.test(this)) {
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
  
  
  // EFFECT: adds a node to the head of the deque
  void addAtHead(T data) {
    Node<T> node = new Node<T>(data);
    this.header.next.insert(node);
  }
  
  // EFFECT: adds a node to the tail of the deque
  void addAtTail(T data) {
    Node<T> node = new Node<T>(data);
    this.header.prev.insert(node);
  }
  
  // EFFECT: removes a node from the head of the deque
  void removeFromHead() {
    this.header.next.remove();
  }
  
  // EFFECT: removes a node from the tail of the deque.
  void removeFromTail() {
    this.header.prev.remove();
  }
  
  // finds the node that passed the given predicate
  ANode<T> find(Predicate<T> pred) {
    return this.header.next.findNode(pred);                     // field of a field
  }
  
  // removes the given node from the deque
  Deque<T> removeNode(Node<T> node) {
    return null;
  }
}

class ExamplesDeque {
  
  Deque<String> dequealpha;
  Deque<String> dequelength;
  
  Sentinel<String> sentalpha;
  Sentinel<String> sentlength;
  
  ANode<String> zab;
  ANode<String> abc;
  ANode<String> bcd;
  ANode<String> cde;
  ANode<String> def;
  ANode<String> efg;
  
  ANode<String> hi;
  ANode<String> dog;
  ANode<String> four;
  ANode<String> apple;
  ANode<String> banana;
  ANode<String> welcome;

  
  void initData() {
    
    
    this.sentalpha = new Sentinel<String>();
    this.abc = new Node<String>("abc");
    this.bcd = new Node<String>("bcd");
    this.cde = new Node<String>("cde");
    this.def = new Node<String>("def");
    
    this.zab = new Node<String>("zab");
    this.efg = new Node<String>("efg");
    
    this.sentalpha.next = this.abc;
    this.sentalpha.prev = this.def;
    this.def.prev = this.cde;
    this.cde.next = this.def;
    this.cde.prev = this.bcd;
    this.bcd.next = this.cde;
    this.bcd.prev = this.abc;
    this.abc.next = this.bcd;
    
    this.dequealpha = new Deque<String>(this.sentalpha);
    
    
    this.sentlength = new Sentinel<String>();
    this.dog = new Node<String>("dog");
    this.four = new Node<String>("four");
    this.apple = new Node<String>("apple");
    this.banana = new Node<String>("banana");
    
    this.hi = new Node<String>("hi");
    this.welcome = new Node<String>("welcome");
    
    this.sentlength.next = this.dog;
    this.sentlength.prev = this.banana;
    this.banana.prev = this.apple;
    this.apple.next = this.banana;
    this.apple.prev = this.four;
    this.four.next = this.apple;
    this.four.prev = this.dog;
    this.dog.next = this.four;
    
    this.dequelength = new Deque<String>(this.sentlength);
  }
  
  // tests for addAtHead method
  void testAddAtHead(Tester t) {                                    // EXPECTED NODE NEEDS TO UPDATE
    this.initData();
    t.checkExpect(this.sentalpha.next, this.abc);
    this.dequealpha.addAtHead("zab");
    t.checkExpect(this.sentalpha.next, this.zab);
    
    t.checkExpect(this.sentlength.next, this.dog);
    this.dequelength.addAtHead("hi");
    t.checkExpect(this.sentlength.next, this.hi);
  }
  
  // tests for addAtTail method
  void testAddAtTail(Tester t) {
    this.initData();
    t.checkExpect(this.sentalpha.prev, this.def);
    this.dequealpha.addAtTail("efg");
    t.checkExpect(this.sentalpha.prev, this.efg);
    
    t.checkExpect(this.sentlength.prev, this.banana);
    this.dequelength.addAtTail("welcome");
    t.checkExpect(this.sentlength.prev, this.welcome);
  }
  
  // tests for removeFromTail method
  void testRemoveFromHead(Tester t) {
    this.initData();
    t.checkExpect(this.sentalpha.next, this.abc);
    this.dequealpha.removeFromHead();
    t.checkExpect(this.sentalpha.next, this.bcd);
    
    t.checkExpect(this.sentlength.next, this.dog);
    this.dequelength.removeFromHead();
    t.checkExpect(this.sentlength.next, this.four);
  }
  
  // tests for removeFromTail method
  void testRemoveFromTail(Tester t) {
    this.initData();
    t.checkExpect(this.sentalpha.prev, this.def);
    this.dequealpha.removeFromTail();
    t.checkExpect(this.sentalpha.prev, this.cde);
    
    t.checkExpect(this.sentlength.prev, this.banana);
    this.dequelength.removeFromTail();
    t.checkExpect(this.sentlength.prev, this.apple);
  }
  
}