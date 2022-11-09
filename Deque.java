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
  
  // finds node that passes the predicate
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
    if (pred.test(this.data)) {
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
    return this.header.next.findNode(pred);                     // field of a field, but this is allowed
  }
  
  // removes the given node from the deque
  void removeNode(Node<T> node) {
    this.find(new SameNode<T>()).remove();                      // fix data type
  }                                                           // finds the given node, then calls remove on it
}

class SameNode<T> implements Predicate<T> {

  // determines if two nodes are the same
  public boolean test(T t) {
    return t.equals(this);
  }
  
}

class LessThanThree implements Predicate<String> {
  
  // checks if the strings length is less than three
  public boolean test(String t) {
    return 3 > t.length();
  }
}

class FirstD implements Predicate<String> {
  
  // checks if the strings first letter is d
  public boolean test(String t) {
    return t.substring(0, 1).equals("d");
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
    this.def.next = this.sentalpha;
    this.def.prev = this.cde;
    this.cde.next = this.def;
    this.cde.prev = this.bcd;
    this.bcd.next = this.cde;
    this.bcd.prev = this.abc;
    this.abc.next = this.bcd;
    this.abc.prev = this.sentalpha;
    
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
    this.banana.next = this.sentlength;
    this.banana.prev = this.apple;
    this.apple.next = this.banana;
    this.apple.prev = this.four;
    this.four.next = this.apple;
    this.four.prev = this.dog;
    this.dog.next = this.four;
    this.dog.prev = this.sentlength;
    
    this.dequelength = new Deque<String>(this.sentlength);
  }
  
  // tests for addAtHead method
  void testAddAtHead(Tester t) {
    this.initData();
    t.checkExpect(this.sentalpha.next, this.abc);
    this.dequealpha.addAtHead("zab");
    this.zab.next = this.abc;
    this.zab.prev = this.sentalpha;
    t.checkExpect(this.sentalpha.next, this.zab);
    
    t.checkExpect(this.sentlength.next, this.dog);
    this.dequelength.addAtHead("hi");
    this.hi.next = this.dog;
    this.hi.prev = this.sentlength;
    t.checkExpect(this.sentlength.next, this.hi);
  }
  
  // tests for addAtTail method
  void testAddAtTail(Tester t) {
    this.initData();
    t.checkExpect(this.sentalpha.prev, this.def);
    this.dequealpha.addAtTail("efg");
    this.efg.next = this.sentalpha;
    this.efg.prev = this.def;
    t.checkExpect(this.sentalpha.prev, this.efg);
    
    t.checkExpect(this.sentlength.prev, this.banana);
    this.dequelength.addAtTail("welcome");
    this.welcome.next = this.sentlength;
    this.welcome.prev = this.banana;
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