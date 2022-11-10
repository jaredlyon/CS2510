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
  void removeNodeHelper(Deque<T> deque) {
    // this does not do anything because it is inherited by sentinel
    // and we do not want anything to happen when the method is given a sentinel
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
    if (next == null || prev == null) {
      throw new IllegalArgumentException("Given node is null");
    }
    this.next = next;
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

class Sentinel<T> extends ANode<T> {
  
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
    this.header = new Sentinel<T>();
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
  
  // gets the removed node from the head
  // EFFECT: removes a node from the head of the deque
  ANode<T> removeFromHead() {
    ANode<T> removed = this.header.next;
    this.header.next.remove();
    return removed;
  }
  
  // gets the removed node from the tail
  // EFFECT: removes a node from the tail of the deque.
  ANode<T> removeFromTail() {
    ANode<T> removed = this.header.prev;
    this.header.prev.remove();
    return removed;
  }
  
  // finds the node that passed the given predicate
  ANode<T> find(Predicate<T> pred) {
    return this.header.next.findNode(pred);
  }
  
  // EFFECT: removes the given node from the deque
  void removeNode(ANode<T> node) {
    node.removeNodeHelper(this);                 
  }                                                          
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
  Deque<String> deque3;
  
  Sentinel<String> sent2;
  Sentinel<String> sent3;
  
  Node<String> abc;
  Node<String> bcd;
  Node<String> cde;
  Node<String> def;
  Node<String> efg;
  Node<String> zab;
  
  Node<String> dog;
  Node<String> four;
  Node<String> apple;
  Node<String> banana;
  Node<String> hi;
  Node<String> welcome;

  
  void initData() {
    
    this.deque1 = new Deque<String>();
    
    this.sent2 = new Sentinel<String>();
    this.abc = new Node<String>("abc", this.sent2, this.sent2);
    this.bcd = new Node<String>("bcd", this.sent2, this.abc);
    this.cde = new Node<String>("cde", this.sent2, this.bcd);
    this.def = new Node<String>("def", this.sent2, this.cde);
    this.deque2 = new Deque<String>(this.sent2);
    
    this.zab = new Node<String>("zab");
    this.efg = new Node<String>("efg");
    
    
    this.sent3 = new Sentinel<String>();
    this.dog = new Node<String>("dog", this.sent3, this.sent3);
    this.four = new Node<String>("four", this.sent3, this.dog);
    this.apple = new Node<String>("apple", this.sent3, this.four);
    this.banana = new Node<String>("banana", this.sent3, this.apple);
    this.deque3 = new Deque<String>(this.sent3);
    
    this.hi = new Node<String>("hi");
    this.welcome = new Node<String>("welcome");
    

  }
  

  
  // tests for FirstD predicate
  void testFirstD(Tester t) {
    t.checkExpect((new FirstD()).test("dog"), true);
    t.checkExpect((new FirstD()).test("adf"), false);
    t.checkExpect((new FirstD()).test("def"), true);
  }
  
  // tests for LessThanFour Predicate
  void testLessThanFour(Tester t) {
    t.checkExpect((new LessThanFour()).test("abc"), true);
    t.checkExpect((new LessThanFour()).test("four"), false);
    t.checkExpect((new LessThanFour()).test("hi"), true);
    t.checkExpect((new LessThanFour()).test("apple"), false);
  }
  
  // tests for SameData predicate
  void testSameData(Tester t) {
    t.checkExpect((new SameData<String>("dog")).test("dog"), true);
    t.checkExpect((new SameData<String>("apple")).test("dog"), false);
    t.checkExpect((new SameData<String>("abc")).test("abc"), true);
  }
  
  // test for node constructor
  void testNode(Tester t) {
    this.initData();
    
    t.checkConstructorException("Invalid argument", 
        new IllegalArgumentException("Given node is null"), "Node", "aaa", null, this.abc);
    t.checkConstructorException("Invalid argument", 
        new IllegalArgumentException("Given node is null"), "Node", "aaa", this.abc, null);
    t.checkConstructorException("Invalid argument", 
        new IllegalArgumentException("Given node is null"), "Node", "aaa", null, null);
  }
  
  // tests for size method
  void testSize(Tester t) {
    this.initData();
    t.checkExpect(this.deque2.size(), 4);
    t.checkExpect(this.deque3.size(), 4);
    
    this.deque2.removeFromHead();
    t.checkExpect(this.deque2.size(), 3);
  }
  
  // tests for addAtHead method
  void testAddAtHead(Tester t) {
    this.initData();
    t.checkExpect(this.sent2.next, this.abc);
    this.deque2.addAtHead("zab");
    t.checkExpect(this.sent2.next, this.abc.prev);
    
    t.checkExpect(this.sent3.next, this.dog);
    this.deque3.addAtHead("hi");
    t.checkExpect(this.sent3.next, this.dog.prev);
  }
  
  // tests for addAtTail method
  void testAddAtTail(Tester t) {
    this.initData();
    t.checkExpect(this.sent2.prev, this.def);
    this.deque2.addAtTail("efg");
    t.checkExpect(this.sent2.prev, this.def.next);
    
    t.checkExpect(this.sent3.prev, this.banana);
    this.deque3.addAtTail("welcome");
    t.checkExpect(this.sent3.prev, this.banana.next);
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
    this.sent2.connect(this.zab);
    t.checkExpect(this.zab.prev, this.sent2);
    t.checkExpect(this.sent2.next, this.zab);
    
    this.abc.connect(this.dog);
    t.checkExpect(this.dog.prev, this.abc);
    t.checkExpect(this.abc.next, this.dog);
  }
  
  
  // tests for removeFromTail method
  void testRemoveFromHead(Tester t) {
    this.initData();
    t.checkExpect(this.sent2.next, this.abc);
    t.checkExpect(this.deque2.removeFromHead(), this.abc);
    t.checkExpect(this.sent2.next, this.bcd);
    
    t.checkExpect(this.sent3.next, this.dog);
    t.checkExpect(this.deque3.removeFromHead(), this.dog);
    t.checkExpect(this.sent3.next, this.four);
    
    // t.checkException("Check for empty deque", 
    //    new RuntimeException("Cannot remove from an empty list"), this.deque1.header, "remove");
  }
  
  // tests for removeFromTail method
  void testRemoveFromTail(Tester t) {
    this.initData();
    t.checkExpect(this.sent2.prev, this.def);
    t.checkExpect(this.deque2.removeFromTail(), this.def);
    t.checkExpect(this.sent2.prev, this.cde);
    
    t.checkExpect(this.sent3.prev, this.banana);
    t.checkExpect(this.deque3.removeFromTail(), this.banana);
    t.checkExpect(this.sent3.prev, this.apple);
    
    // t.checkException("Check for empty deque", 
    //    new RuntimeException("Cannot remove from an empty list"), this.deque1.header, "remove");
  }
  
  // tests for the remove helper method
  void testRemove(Tester t) {
    this.initData();
    
    this.abc.remove();
    t.checkExpect(this.sent2.next, this.bcd);
    t.checkExpect(this.bcd.prev, this.sent2);
    this.bcd.remove();
    t.checkExpect(this.sent2.next, this.cde);
    t.checkExpect(this.cde.prev, this.sent2);
    this.def.remove();
    t.checkExpect(this.sent2.prev, this.cde);
    t.checkExpect(this.cde.next, this.sent2);
    t.checkException("Check for empty deque", 
        new RuntimeException("Cannot remove from an empty list"), this.cde, "remove");
    
    this.dog.remove();
    t.checkExpect(this.sent3.next, this.four);
    t.checkExpect(this.four.prev, this.sent3);
    this.apple.remove();
    t.checkExpect(this.four.next, this.banana);
    t.checkExpect(this.banana.prev, this.four);
    
  }
   
  
  // tests for the find method
  void testFind(Tester t) {
    this.initData();
    t.checkExpect(this.deque2.find(new FirstD()), this.def);
    t.checkExpect(this.deque3.find(new FirstD()), this.dog);
    t.checkExpect(this.deque3.find(new LessThanFour()), this.dog);
    t.checkExpect(this.deque2.find(new SameData<String>("dog")), this.deque2.header);
    t.checkExpect(this.deque3.find(new SameData<String>("bcd")), this.deque3.header);
  }
  
  // tests for the find helper method
  void testFindNode(Tester t) {
    this.initData();
    t.checkExpect(this.abc.findNode(new LessThanFour()), this.abc);
    t.checkExpect(this.banana.findNode(new LessThanFour()), this.sent3);
    t.checkExpect(this.dog.findNode(new LessThanFour()), this.dog);
    t.checkExpect(this.abc.findNode(new FirstD()), this.def);
    t.checkExpect(this.dog.findNode(new FirstD()), this.dog);
    t.checkExpect(this.four.findNode(new FirstD()), this.sent3); 
  }
  
  // tests for the removenode helper method
  void testRemoveNodeHelper(Tester t) {
    this.initData();
    this.initData();
    
    t.checkExpect(this.deque2.header.next, this.abc);
    this.abc.removeNodeHelper(this.deque2);
    t.checkExpect(this.deque2.header.next, this.bcd);
    
    this.bcd.removeNodeHelper(this.deque2);
    t.checkExpect(this.deque2.header.next, this.cde);
    
    this.initData();
    this.dog.removeNodeHelper(this.deque2);
    t.checkExpect(this.deque2, this.deque2);
    
    t.checkExpect(this.deque3.header.next, this.dog);
    this.dog.removeNodeHelper(this.deque3);
    t.checkExpect(this.deque3.header.next, this.four);

    this.apple.removeNodeHelper(this.deque3);
    t.checkExpect(this.deque3.header.next.next, this.banana);
    
    this.initData();
    this.abc.removeNodeHelper(this.deque3);
    t.checkExpect(this.deque3, this.deque3);
    
    this.initData();
    t.checkExpect(this.deque2, this.deque2);
    this.sent2.removeNodeHelper(this.deque2);
    t.checkExpect(this.deque2, this.deque2);
    
    t.checkExpect(this.deque2, this.deque2);
    this.sent3.removeNodeHelper(this.deque3);
    t.checkExpect(this.deque2, this.deque2);
  }
  
  // tests for removenode method
  void testRemoveNode(Tester t) {
    this.initData();
    
    t.checkExpect(this.deque2.header.next, this.abc);
    this.deque2.removeNode(this.abc);
    t.checkExpect(this.deque2.header.next, this.bcd);
    
    this.deque2.removeNode(this.bcd);
    t.checkExpect(this.deque2.header.next, this.cde);
    
    this.initData();
    this.deque2.removeNode(this.dog);
    t.checkExpect(this.deque2, this.deque2);
    
    t.checkExpect(this.deque3.header.next, this.dog);
    this.deque3.removeNode(this.dog);
    t.checkExpect(this.deque3.header.next, this.four);

    this.deque3.removeNode(this.apple);
    t.checkExpect(this.deque3.header.next.next, this.banana);
    
    this.initData();
    this.deque3.removeNode(this.abc);
    t.checkExpect(this.deque3, this.deque3);
    
    this.initData();
    t.checkExpect(this.deque2, this.deque2);
    this.deque2.removeNode(this.sent2);
    t.checkExpect(this.deque2, this.deque2);
    
    t.checkExpect(this.deque2, this.deque2);
    this.deque3.removeNode(this.sent3);
    t.checkExpect(this.deque2, this.deque2);
  }
}