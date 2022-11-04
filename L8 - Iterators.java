import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

class ListOfLists<T> implements Iterable<T> {
  ArrayList<ArrayList<T>> list;

  ListOfLists(ArrayList<ArrayList<T>> list) {
    this.list = list;
  }
  
  ListOfLists() {
    this.list = new ArrayList<ArrayList<T>>();
  }

  void addNewList() {
    ArrayList<T> newList = new ArrayList<T>();
    list.add(newList);
  }

  void add(int index, T object) {
    if (index > (list.size() - 1)) {
      throw new IndexOutOfBoundsException("Invalid index!");
    } else {
      ArrayList<T> target = list.get(index);
      target.add(object);
    }
  }

  public ArrayList<T> get(int index) {
    if (index > (list.size() - 1)) {
      throw new IndexOutOfBoundsException("Invalid index!");
    } else {
      return this.list.get(index);
    }
  }

  public int size() {
    return this.list.size();
  }

  //produces an Iterator for this list
  public Iterator<T> iterator() {
    return new LoLIterator<T>(this);
  }
}

class LoLIterator<T> implements Iterator<T> {
  ListOfLists<T> items;
  int outerIndex;
  int innerIndex;
  
  LoLIterator(ListOfLists<T> items) {
    this.items = items;
    this.outerIndex = 0;
    this.innerIndex = 0;
  }
  
  //are there any more items to produce?
  public boolean hasNext() {
    return this.outerIndex < this.items.size()
        && this.innerIndex < this.items.get(outerIndex).size();
  }
  
  //gets the next item
  //EFFECT: the currentIndex is incremented by 1
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!");
    }
    
    T temp = this.items.get(this.outerIndex).get(innerIndex);
    
    if ((this.items.get(this.outerIndex).size() - 1) > innerIndex) {
      innerIndex++;
    } else {
      innerIndex = 0;
      outerIndex++;
    }
    
    return temp;
  }
}

class ExamplesLoL {
  void testListOfLists(Tester t) {
    ListOfLists<Integer> lol = new ListOfLists<Integer>();
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();
 
    //add elements 1,2,3 in first list
    lol.add(0,1);
    lol.add(0,2);
    lol.add(0,3);
 
    //add elements 4,5,6 in second list
    lol.add(1,4);
    lol.add(1,5);
    lol.add(1,6);
 
    //add elements 7,8,9 in third list
    lol.add(2,7);
    lol.add(2,8);
    lol.add(2,9);
 
    //iterator should return elements in order 1,2,3,4,5,6,7,8,9
    int number = 1;
    for (Integer num:lol) {
        t.checkExpect(num,number);
        number = number + 1;
    }
}

}