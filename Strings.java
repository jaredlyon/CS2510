// CS 2510, Assignment 3

import tester.*;

// to represent a list of Strings
interface ILoString {
  // combine all Strings in this list into one
  String combine();

  // replace all instances of the first string in this list with the second
  ILoString findAndReplace(String find, String replace);

  // checks if there are repeated strings in this list
  boolean anyDupes();

  // helper for anyDupes
  boolean dupeHelper(String ref);

  // alphabetically sorts the given list
  ILoString sort();

  // keeps track of the new sorted list for the sort method
  ILoString insert(String str);
  
  // checks if this list is sorted
  boolean isSorted();
  
  // accumulates data for isSorted
  boolean sortAcc(String str);
}

// to represent an empty list of Strings
class MtLoString implements ILoString {
  MtLoString() {}

  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  // replace all instances of the first string in this list with the second
  public ILoString findAndReplace(String find, String replace) {
    return new MtLoString();
  }

  // checks if there are repeated strings in this list
  public boolean anyDupes() {
    return false;
  }

  // helper for anyDupes
  public boolean dupeHelper(String ref) {
    return false;
  }

  // sorts this list
  public ILoString sort() {
    return this;
  }

  // keeps track of the new list for the sort method
  public ILoString insert(String str) {
    return new ConsLoString(str, this);
  }
  
  // checks if this list is sorted
  public boolean isSorted() {
    return true;
  }
  
  // accumulates data for isSorted
  public boolean sortAcc(String str) {
    return true;
  }
}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;  
  }

  /*
     TEMPLATE
     FIELDS:
     ... this.first ...         -- String
     ... this.rest ...          -- ILoString

     METHODS
     ... this.combine() ...     -- String

     METHODS FOR FIELDS
     ... this.first.concat(String) ...        -- String
     ... this.first.compareTo(String) ...     -- int
     ... this.rest.combine() ...              -- String

   */

  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }  

  // replace all instances of the first string in this list with the second
  public ILoString findAndReplace(String find, String replace) {
    if (this.first.equals(find)) {
      return new ConsLoString(replace, this.rest.findAndReplace(find, replace));
    } else {
      return new ConsLoString(this.first, this.rest.findAndReplace(find, replace));
    }
  }

  // to represent a nonempty list of Strings
  public boolean anyDupes() {
    return this.rest.dupeHelper(this.first) || this.rest.anyDupes();
  }

  // helper for anyDupes
  public boolean dupeHelper(String ref) {
    return ref.equals(this.first) || this.rest.dupeHelper(ref);
  }

  // alphabetically sorts this list
  public ILoString sort() {
    return this.rest.sort().insert(this.first);
  }

  // keeps track of the new list for sort
  public ILoString insert(String str) {
    if (this.first.toLowerCase().compareTo(str.toLowerCase()) < 0) {
      return new ConsLoString(this.first, this.rest.insert(str));
    } else {
      return new ConsLoString(str, this);
    }
  }
  
  // checks if this list is sorted
  public boolean isSorted() {
    return this.rest.sortAcc(this.first);
  }
  
  // accumulates data for isSorted
  public boolean sortAcc(String str) {
    return this.first.toLowerCase().compareTo(str.toLowerCase()) > 0
        && this.rest.sortAcc(this.first);
  }
}

// to represent examples for lists of strings
class ExamplesStrings {
  
  ILoString mt = new MtLoString();

  ILoString mary = new ConsLoString("Mary ",
      new ConsLoString("had ",
          new ConsLoString("a ",
              new ConsLoString("little ",
                  new ConsLoString("lamb.", new MtLoString())))));
  
  ILoString marySort = new ConsLoString("a ",
      new ConsLoString("had ",
          new ConsLoString("lamb.",
              new ConsLoString("little ",
                  new ConsLoString("Mary ", new MtLoString())))));
  
  ILoString johnny = new ConsLoString("Johnny ",
      new ConsLoString("owned ",
          new ConsLoString("a ",
              new ConsLoString("pretty ",
                  new ConsLoString("car.", new MtLoString())))));
  
  ILoString johnnySort = new ConsLoString("a ",
      new ConsLoString("car.",
          new ConsLoString("Johnny ",
              new ConsLoString("owned ",
                  new ConsLoString("pretty ", new MtLoString())))));

  ILoString list1 = new ConsLoString("bottle",
      new ConsLoString("bottle",
          new ConsLoString("water",
              new ConsLoString("water",
                  new ConsLoString("chair", new MtLoString())))));

  ILoString list2 = new ConsLoString("water",
      new ConsLoString("water",
          new ConsLoString("water",
              new ConsLoString("water",
                  new ConsLoString("chair", new MtLoString())))));

  ILoString list3 = new ConsLoString("bottle",
      new ConsLoString("water",
          new ConsLoString("water",
              new ConsLoString("water",
                  new ConsLoString("chair", new MtLoString())))));

  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return 
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }

  // test the method findAndReplace
  boolean testFindAndReplace(Tester t) {
    return 
        t.checkExpect(this.list1.findAndReplace("bottle", "water"), list2)
        && t.checkExpect(this.mt.findAndReplace("b", "a"), mt);
  }

  // test the method anyDupes
  boolean testAnyDupes(Tester t) {
    return 
        t.checkExpect(this.list1.anyDupes(), true)
        && t.checkExpect(this.mary.anyDupes(), false)
        && t.checkExpect(this.list3.anyDupes(), true)
        && t.checkExpect(this.mt.anyDupes(), false);
  }
  
  // test the method sort
  boolean testSort(Tester t) {
    return 
        t.checkExpect(this.mary.sort(), marySort)
        && t.checkExpect(this.johnny.sort(), johnnySort)
        && t.checkExpect(this.mt.sort(), mt);
  }
  
  // test the method isSorted
  boolean testIsSorted(Tester t) {
    return 
        t.checkExpect(this.mary.isSorted(), false)
        && t.checkExpect(this.marySort.isSorted(), true)
        && t.checkExpect(this.johnny.isSorted(), false)
        && t.checkExpect(this.johnnySort.isSorted(), true)
        && t.checkExpect(this.mt.isSorted(), true);
  }

}