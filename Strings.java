// CS 2510, Assignment 3

import tester.Tester;

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
  
  // combines lists alternatively, leaving any extras at the end
  ILoString interleave(ILoString other);
  
  // combines this sorted listed with another sorted list to create a new sorted list
  ILoString merge(ILoString other);
  
  // compares a string to a list for merge
  boolean mergeChecker(String str);
  
  // reverses this list for reverseConcat
  String reverseConcat();
  
  // insert method for the reverseConcat
  String reverseConcatInsert(String str);
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
  
  // combines lists alternatively, leaving any extras at the end
  public ILoString interleave(ILoString other) {
    return other;
  }
  
  // combines this sorted listed with another sorted list to create a new sorted list
  public ILoString merge(ILoString other) {
    return other;
  }
  
  // compares a string to a list for merge
  public boolean mergeChecker(String str) {
    return true;
  }

  // reverses this list for reverseConcat
  public String reverseConcat() {
    return "";
  }
  
  // insert method for the reverseConcat
  public String reverseConcatInsert(String str) {
    return "" + str;
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

  // combines lists alternatively, leaving any extras at the end
  public ILoString interleave(ILoString other) {
    return new ConsLoString(this.first, other.interleave(this.rest));
  }
  
  // combines this sorted listed with another sorted list to create a new sorted list
  public ILoString merge(ILoString other) {
    if (other.mergeChecker(this.first)) {
      return new ConsLoString(this.first, this.rest.merge(other));
    } else {
      return other.merge(this);
    }
  }
  
  // compares a string to a list for merge
  public boolean mergeChecker(String str) {
    return str.toLowerCase().compareTo(this.first.toLowerCase()) <= 0
        && this.rest.mergeChecker(str);
  }
  
  // reverses this list for reverseConcat
  public String reverseConcat() {
    return this.rest.reverseConcat().reverseConcatInsert(this.first);
  }
  
  // insert method for the reverseConcat
  public String reverseConcatInsert(String str) {
    return this.first + this.rest.reverseConcatInsert(str);
  }
  
}

// to represent examples for lists of strings
class ExamplesStrings {
  ExamplesStrings() {}
  
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
  
  ILoString maryJohnnyMerge = new ConsLoString ("a ",
      new ConsLoString("a ",
          new ConsLoString("car.",
              new ConsLoString("had ",
                  new ConsLoString("Johnny ", 
                      new ConsLoString("lamb.",
                          new ConsLoString("little ",
                              new ConsLoString("Mary ",
                                  new ConsLoString("owned ",
                                      new ConsLoString("pretty ", new MtLoString()))))))))));
  
  ILoString maryJohnnyInterleave = new ConsLoString ("a ",
      new ConsLoString("a ",
          new ConsLoString("had ",
              new ConsLoString("car.",
                  new ConsLoString("lamb.", 
                      new ConsLoString("Johnny ",
                          new ConsLoString("little ",
                              new ConsLoString("owned ",
                                  new ConsLoString("Mary ",
                                      new ConsLoString("pretty ", new MtLoString()))))))))));

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
  
  ILoString list4 = new ConsLoString("apple",
      new ConsLoString("banana",
          new ConsLoString("orange", new MtLoString())));
  
  ILoString list5 = new ConsLoString("axe",
      new ConsLoString("beyblade",
          new ConsLoString("ostensible", new MtLoString())));
  
  ILoString list45InterleaveMerge = new ConsLoString("apple",
      new ConsLoString("axe",
          new ConsLoString("banana",
              new ConsLoString("beyblade",
                  new ConsLoString("orange",
                      new ConsLoString("ostensible", new MtLoString()))))));
  
  ILoString maryJohnny = new ConsLoString ("Mary ",
      new ConsLoString("Johnny ",
          new ConsLoString("had ",
              new ConsLoString("owned ",
                  new ConsLoString("a ", 
                      new ConsLoString("a ",
                          new ConsLoString("little ",
                              new ConsLoString("pretty ",
                                  new ConsLoString("lamb.",
                                      new ConsLoString("car.", new MtLoString()))))))))));
  
  ILoString maryMary = new ConsLoString("Mary ",
      new ConsLoString("Mary ",
          new ConsLoString("had ",
              new ConsLoString("had ", 
                  new ConsLoString("a ",
                      new ConsLoString("a ",
                          new ConsLoString("little ",
                              new ConsLoString("little ",
                                  new ConsLoString("lamb.",
                                      new ConsLoString("lamb.", new MtLoString()))))))))));
  
  ILoString maryList4 =  new ConsLoString("Mary ",
      new ConsLoString("apple",
          new ConsLoString("had ",
              new ConsLoString("banana", 
                  new ConsLoString("a ",
                      new ConsLoString("orange",
                          new ConsLoString("little ",
                              new ConsLoString("lamb.", new MtLoString()))))))));
                                          
  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return 
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }

  // test the method findAndReplace
  boolean testFindAndReplace(Tester t) {
    return 
        t.checkExpect(this.list1.findAndReplace("bottle", "water"), list2)
        && t.checkExpect(this.list1.findAndReplace("gahhh", "aaaaahhh"), list1)
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
  
  // test the method interleave
  boolean testInterleave(Tester t) {
    return
        t.checkExpect(this.mary.interleave(this.johnny), this.maryJohnny)
        && t.checkExpect(this.marySort.interleave(this.johnnySort), this.maryJohnnyInterleave) // different output from merge
        && t.checkExpect(this.list4.interleave(this.list5), this.list45InterleaveMerge) // same output as merge
        && t.checkExpect(this.mary.interleave(this.mary), this.maryMary)
        && t.checkExpect(this.mary.interleave(this.mt), this.mary)
        && t.checkExpect(this.mt.interleave(this.mary), this.mary)
        && t.checkExpect(this.mary.interleave(this.list4), this.maryList4);
  }
  
  // test the method merge
  boolean testMerge(Tester t) {
    return
        t.checkExpect(this.marySort.merge(this.johnnySort), this.maryJohnnyMerge) // different output from interleave
        && t.checkExpect(this.list4.merge(this.list5), this.list45InterleaveMerge) // same output as interleave
        && t.checkExpect(this.mt.merge(this.marySort), this.marySort);
  }
  
  // test the method reverseConcat
  boolean testReverseConcat(Tester t) {
    return
        t.checkExpect(this.mary.reverseConcat(), "lamb.little a had Mary ")
        && t.checkExpect(this.mt.reverseConcat(), "");
  }

}