import java.util.function.Predicate;

import tester.Tester;



abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;
  
  // determines the number of nodes in a deque
  int size() {
    return 0;
  }
  
  
  // EFFECT: inserts the given node into the sentinel
  void insert(ANode<T> node) {
    this.prev = node;
    node.next = this;
    
  }
  
  // EFFECT: connects one side of the node into the deque
  void connect(ANode<T> node) {
    this.next = node;
    node.prev = this;
  }
  
  // EFFECT: removes the current node and connects the next and previous nodes
  void remove() {
    if (this.next == this.prev) {
      throw new RuntimeException("Cannot remove from an empty list");
    }
    this.next.prev = this.prev;
    this.prev.next = this.next;
  }
  
  // finds node that passes the predicate
  ANode<T> findNode(Predicate<T> pred) {
    return this;
  }
  
  // EFFECT: removes the given node from the deque
  void removeNodeHelper(Deque<T> deque) {};
 
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
  
  // determines the number of nodes in a deque
  int size() {
    return 1 + this.next.size();
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
  // EFFECT: removes the given node from the deque
  void removeNodeHelper(Deque<T> deque) {
    deque.find(new SameData<T>(this.data)).remove();
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
    return this.header.next.size();
  }
  
  
  // EFFECT: adds a node to the head of the deque
  void addAtHead(T data) {
    Node<T> node = new Node<T>(data);
    this.header.next.insert(node);
    this.header.connect(node);
  }
  
  // EFFECT: adds a node to the tail of the deque
  void addAtTail(T data) {
    Node<T> node = new Node<T>(data);
    node.insert(this.header.prev);
    node.connect(this.header);
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
  
  // EFFECT: removes the given node from the deque
  void removeNode(ANode<T> node) {
    node.removeNodeHelper(this);                                // fix data type
    }                                                           // finds the given node, then calls remove on it
  
}


class SameData<T> implements Predicate<T> {

  T data;
  
  SameData(T data) {
    this.data = data;
  }

  // determines if two nodes are the same
  public boolean test(T t) {
    return t.equals(data);
  }
  
}

class LessThanFour implements Predicate<String> {
  
  // checks if the strings length is less than four
  public boolean test(String t) {
    return 4 > t.length();
  }
}

class FirstD implements Predicate<String> {
  
  // checks if the strings first letter is d
  public boolean test(String t) {
    return t.substring(0, 1).equals("d");
  }
}

class ExamplesDeque {
  
  Deque<String> deque1;
  Deque<String> deque2;
  
  Sentinel<String> sent1;
  Sentinel<String> sent2;
  
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
    
    
    this.sent1 = new Sentinel<String>();
    this.abc = new Node<String>("abc");
    this.bcd = new Node<String>("bcd");
    this.cde = new Node<String>("cde");
    this.def = new Node<String>("def");
    
    this.zab = new Node<String>("zab");
    this.efg = new Node<String>("efg");
    
    this.sent1.next = this.abc;
    this.sent1.prev = this.def;
    this.def.next = this.sent1;
    this.def.prev = this.cde;
    this.cde.next = this.def;
    this.cde.prev = this.bcd;
    this.bcd.next = this.cde;
    this.bcd.prev = this.abc;
    this.abc.next = this.bcd;
    this.abc.prev = this.sent1;
    
    this.deque1 = new Deque<String>(this.sent1);
    
    
    this.sent2 = new Sentinel<String>();
    this.dog = new Node<String>("dog");
    this.four = new Node<String>("four");
    this.apple = new Node<String>("apple");
    this.banana = new Node<String>("banana");
    
    this.hi = new Node<String>("hi");
    this.welcome = new Node<String>("welcome");
    
    this.sent2.next = this.dog;
    this.sent2.prev = this.banana;
    this.banana.next = this.sent2;
    this.banana.prev = this.apple;
    this.apple.next = this.banana;
    this.apple.prev = this.four;
    this.four.next = this.apple;
    this.four.prev = this.dog;
    this.dog.next = this.four;
    this.dog.prev = this.sent2;
    
    this.deque2 = new Deque<String>(this.sent2);
  }
  
  // tests for size method
  void testSize(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.size(), 4);
    t.checkExpect(this.deque2.size(), 4);
    
    this.deque1.removeFromHead();
    t.checkExpect(this.deque1.size(), 3);
  }
  
  // tests for addAtHead method
  void testAddAtHead(Tester t) {
    this.initData();
    t.checkExpect(this.sent1.next, this.abc);
    this.deque1.addAtHead("zab");
    t.checkExpect(this.sent1.next, this.abc.prev);
    
    t.checkExpect(this.sent2.next, this.dog);
    this.deque2.addAtHead("hi");
    t.checkExpect(this.sent2.next, this.dog.prev);
  }
  
  // tests for addAtTail method
  void testAddAtTail(Tester t) {
    this.initData();
    t.checkExpect(this.sent1.prev, this.def);
    this.deque1.addAtTail("efg");
    t.checkExpect(this.sent1.prev, this.def.next);
    
    t.checkExpect(this.sent2.prev, this.banana);
    this.deque2.addAtTail("welcome");
    t.checkExpect(this.sent2.prev, this.banana.next);
  }
  
  // tests for insert helper method
  void testInsert(Tester t) {
    this.initData();
    this.abc.insert(this.zab);
    t.checkExpect(this.zab.next, this.abc);
    t.checkExpect(this.abc.prev, this.zab);
    
    this.apple.insert(this.welcome);
    t.checkExpect(this.welcome.next, this.apple);
    t.checkExpect(this.apple.prev, this.welcome);
  }
  
  // tests for connect helper method
  void testConnect(Tester t) {
    this.initData();
    this.sent1.connect(this.zab);
    t.checkExpect(this.zab.prev, this.sent1);
    t.checkExpect(this.sent1.next, this.zab);
    
    this.abc.connect(this.dog);
    t.checkExpect(this.dog.prev, this.abc);
    t.checkExpect(this.abc.next, this.dog);
  }
  
  
  // tests for removeFromTail method
  void testRemoveFromHead(Tester t) {
    this.initData();
    t.checkExpect(this.sent1.next, this.abc);
    this.deque1.removeFromHead();
    t.checkExpect(this.sent1.next, this.bcd);
    
    t.checkExpect(this.sent2.next, this.dog);
    this.deque2.removeFromHead();
    t.checkExpect(this.sent2.next, this.four);
  }
  
  // tests for removeFromTail method
  void testRemoveFromTail(Tester t) {
    this.initData();
    t.checkExpect(this.sent1.prev, this.def);
    this.deque1.removeFromTail();
    t.checkExpect(this.sent1.prev, this.cde);
    
    t.checkExpect(this.sent2.prev, this.banana);
    this.deque2.removeFromTail();
    t.checkExpect(this.sent2.prev, this.apple);
  }
  
  // tests for the remove helper method
  void testRemove(Tester t) {
    this.initData();
    
    this.abc.remove();
    t.checkExpect(this.sent1.next, this.bcd);
    t.checkExpect(this.bcd.prev, this.sent1);
    this.bcd.remove();
    t.checkExpect(this.sent1.next, this.cde);
    t.checkExpect(this.cde.prev, this.sent1);
    this.def.remove();
    t.checkExpect(this.sent1.prev, this.cde);
    t.checkExpect(this.cde.next, this.sent1);
    t.checkException("Check for empty deque", new RuntimeException("Cannot remove from an empty list"), this.cde, "remove");
    
    this.dog.remove();
    t.checkExpect(this.sent2.next, this.four);
    t.checkExpect(this.four.prev, this.sent2);
    this.apple.remove();
    t.checkExpect(this.four.next, this.banana);
    t.checkExpect(this.banana.prev, this.four);
    
  }
   
  
  // tests for the find method
  void testFind(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.find(new FirstD()), this.def);
    t.checkExpect(this.deque2.find(new FirstD()), this.dog);
    t.checkExpect(this.deque2.find(new LessThanFour()), this.dog);
    t.checkExpect(this.deque1.find(new SameData<String>("dog")), this.deque1.header);
    t.checkExpect(this.deque2.find(new SameData<String>("bcd")), this.deque2.header);
  }
  
  // tests for the find helper method
  void testFindNode(Tester t) {
    this.initData();
    t.checkExpect(this.abc.findNode(new LessThanFour()), this.abc);
    t.checkExpect(this.banana.findNode(new LessThanFour()), this.sent2);
    t.checkExpect(this.dog.findNode(new LessThanFour()), this.dog);
    t.checkExpect(this.abc.findNode(new FirstD()), this.def);
    t.checkExpect(this.dog.findNode(new FirstD()), this.dog);
    t.checkExpect(this.four.findNode(new FirstD()), this.sent2); 
  }
  
  // tests for the removenode helper method
  void testRemoveNodeHelper(Tester t) {
    
  }
  
  // tests for removenode method
  void testRemoveNode(Tester t) {
    this.initData();
    this.deque1.removeNode(this.abc);
    t.checkExpect(this.deque1.header.next, this.bcd);
    
    this.deque1.removeNode(this.bcd);
    t.checkExpect(this.deque1.header.next, this.cde);
    
    this.initData();
    this.deque1.removeNode(this.dog);
    t.checkExpect(this.deque1, this.deque1);
    
    this.deque2.removeNode(this.dog);
    t.checkExpect(this.deque2.header.next, this.four);
    
    this.deque2.removeNode(this.apple);
    t.checkExpect(this.deque2.header.next.next, this.banana);
    
    this.initData();
    this.deque2.removeNode(this.abc);
    t.checkExpect(this.deque2, this.deque2);
    
    this.initData();
    t.checkExpect(this.deque1, this.deque1);
    this.deque1.removeNode(this.sent1);
    t.checkExpect(this.deque1, this.deque1);
    
    t.checkExpect(this.deque1, this.deque1);
    this.deque2.removeNode(this.sent2);
    t.checkExpect(this.deque1, this.deque1);
  }
}