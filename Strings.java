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

  // combines this sorted listed with another sorted list to create a new sorted
  // list
  ILoString merge(ILoString other);

  // compares a string to a list for merge
  boolean mergeChecker(String str);

  // reverses this list for reverseConcat
  String reverseConcat();

  // checks if a list of strings is a series of doubles
  boolean isDoubledList();

  // helper for isDoubledList
  boolean isDoubledListHelper(String str);

  // checks if this list is the same both forwards and backwards
  boolean isPalindromeList();

  // reverses a list
  ILoString reverse();

  // helper for reversing a list
  ILoString reverseHelper(ILoString acc);
}

// to represent an empty list of Strings
class MtLoString implements ILoString {
  MtLoString() {
  }

  /*
   * fields:
   *  none
   * methods:
   *  this.combine() ... String
   *  this.findAndReplace(String, String) ... ILoString
   *  this.anyDupes() ... boolean
   *  this.dupeHelper(String) ... boolean
   *  this.sort() ... ILoString
   *  this.insert(String) ... ILoString
   *  this.isSorted() ... boolean
   *  this.sortAcc(String) ... boolean
   *  this.interleave(ILoString) ... ILoString
   *  this.merge(ILoString) ... ILoString
   *  this.mergeChecker(String) ... boolean
   *  this.reverseConcat() ... String
   *  this.isDoubledList() ... boolean
   *  this.isDoubledListHelper(String) ... boolean
   *  this.isPalindromeList() ... boolean
   *  this.reverse() ... ILoString
   *  this.reverseHelper(ILoString) ... ILoString
   * methods for fields:
   *  none
   */

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
    /* fields of other:
     *  other.first ... String
     *  other.rest ... ILoString
     * methods:
     *  other.combine() ... String
     *  other.findAndReplace(String, String) ... ILoString
     *  other.anyDupes() ... boolean
     *  other.dupeHelper(String) ... boolean
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.sortAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeChecker(String) ... boolean
     *  other.reverseConcat() ... String
     *  other.isDoubledList() ... boolean
     *  other.isDoubledListHelper(String) ... boolean
     *  other.isPalindromeList() ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelper(ILoString) ... ILoString
     */

    return other;
  }

  // combines this sorted listed with another sorted list to create a new sorted list
  public ILoString merge(ILoString other) {
    /* fields of other:
     *  other.first ... String
     *  other.rest ... ILoString
     * methods:
     *  other.combine() ... String
     *  other.findAndReplace(String, String) ... ILoString
     *  other.anyDupes() ... boolean
     *  other.dupeHelper(String) ... boolean
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.sortAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeChecker(String) ... boolean
     *  other.reverseConcat() ... String
     *  other.isDoubledList() ... boolean
     *  other.isDoubledListHelper(String) ... boolean
     *  other.isPalindromeList() ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelper(ILoString) ... ILoString
     */

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

  // checks if this list is made of doubles
  public boolean isDoubledList() {
    return true;
  }

  // helper for isDoubledList
  public boolean isDoubledListHelper(String str) {
    return false;
  }

  // checks if this list is a palindrome
  public boolean isPalindromeList() {
    return true;
  }

  // reverses a list
  public ILoString reverse() {
    return this;
  }

  // helper for reverse method
  public ILoString reverseHelper(ILoString acc) {
    /* fields of other:
     *  acc.first ... String
     *  acc.rest ... ILoString
     * methods:
     *  acc.combine() ... String
     *  acc.findAndReplace(String, String) ... ILoString
     *  acc.anyDupes() ... boolean
     *  acc.dupeHelper(String) ... boolean
     *  acc.sort() ... ILoString
     *  acc.insert(String) ... ILoString
     *  acc.isSorted() ... boolean
     *  acc.sortAcc(String) ... boolean
     *  acc.interleave(ILoString) ... ILoString
     *  acc.merge(ILoString) ... ILoString
     *  acc.mergeChecker(String) ... boolean
     *  acc.reverseConcat() ... String
     *  acc.isDoubledList() ... boolean
     *  acc.isDoubledListHelper(String) ... boolean
     *  acc.isPalindromeList() ... boolean
     *  acc.reverse() ... ILoString
     *  acc.reverseHelper(ILoString) ... ILoString
     */

    return acc;
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
   * fields:
   *  this.first ... String
   *  this.rest ... ILoString
   * methods:
   *  this.combine() ... String
   *  this.findAndReplace(String, String) ... ILoString
   *  this.anyDupes() ... boolean
   *  this.dupeHelper(String) ... boolean
   *  this.sort() ... ILoString
   *  this.insert(String) ... ILoString
   *  this.isSorted() ... boolean
   *  this.sortAcc(String) ... boolean
   *  this.interleave(ILoString) ... ILoString
   *  this.merge(ILoString) ... ILoString
   *  this.mergeChecker(String) ... boolean
   *  this.reverseConcat() ... String
   *  this.isDoubledList() ... boolean
   *  this.isDoubledListHelper(String) ... boolean
   *  this.isPalindromeList() ... boolean
   *  this.reverse() ... ILoString
   *  this.reverseHelper(ILoString) ... ILoString
   * methods for fields:
   *  this.first.concat(String) ... String
   *  this.first.compareTo(String) ... int
   *  this.rest.combine() ... String
   *  this.rest.combine() ... String
   *  this.rest.findAndReplace(String, String) ... ILoString
   *  this.rest.anyDupes() ... boolean
   *  this.rest.dupeHelper(String) ... boolean
   *  this.rest.sort() ... ILoString
   *  this.rest.insert(String) ... ILoString
   *  this.rest.isSorted() ... boolean
   *  this.rest.sortAcc(String) ... boolean
   *  this.rest.interleave(ILoString) ... ILoString
   *  this.rest.merge(ILoString) ... ILoString
   *  this.rest.mergeChecker(String) ... boolean
   *  this.rest.reverseConcat() ... String
   *  this.rest.isDoubledList() ... boolean
   *  this.rest.isDoubledListHelper(String) ... boolean
   *  this.rest.isPalindromeList() ... boolean
   *  this.rest.reverse() ... ILoString
   *  this.rest.reverseHelper(ILoString) ... ILoString
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
    return this.first.toLowerCase().compareTo(str.toLowerCase()) >= 0
        && this.rest.sortAcc(this.first);
  }

  // combines lists alternatively, leaving any extras at the end
  public ILoString interleave(ILoString other) {
    /* fields of other:
     *  other.first ... String
     *  other.rest ... ILoString
     * methods:
     *  other.combine() ... String
     *  other.findAndReplace(String, String) ... ILoString
     *  other.anyDupes() ... boolean
     *  other.dupeHelper(String) ... boolean
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.sortAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeChecker(String) ... boolean
     *  other.reverseConcat() ... String
     *  other.isDoubledList() ... boolean
     *  other.isDoubledListHelper(String) ... boolean
     *  other.isPalindromeList() ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelper(ILoString) ... ILoString
     */

    return new ConsLoString(this.first, other.interleave(this.rest));
  }

  // combines this sorted listed with another sorted list to create a new sorted list
  public ILoString merge(ILoString other) {
    /* fields of other:
     *  other.first ... String
     *  other.rest ... ILoString
     * methods:
     *  other.combine() ... String
     *  other.findAndReplace(String, String) ... ILoString
     *  other.anyDupes() ... boolean
     *  other.dupeHelper(String) ... boolean
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.sortAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeChecker(String) ... boolean
     *  other.reverseConcat() ... String
     *  other.isDoubledList() ... boolean
     *  other.isDoubledListHelper(String) ... boolean
     *  other.isPalindromeList() ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelper(ILoString) ... ILoString
     */

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
    return this.reverse().combine();
  }

  // checks if this list is composed of pairs
  public boolean isDoubledList() {
    return this.rest.isDoubledListHelper(this.first);
  }

  // helper for isDoubledList
  public boolean isDoubledListHelper(String str) {
    if (this.first.equalsIgnoreCase(str.toLowerCase())) {
      return this.rest.isDoubledList();
    } else {
      return false;
    }
  }

  // checks if this list is a palindrome
  public boolean isPalindromeList() {
    return this.combine().equalsIgnoreCase(this.reverse().combine());
  }

  // reverses a list
  public ILoString reverse() {
    return this.rest.reverseHelper(new ConsLoString(this.first, new MtLoString()));
  }

  // helper for reverse
  public ILoString reverseHelper(ILoString acc) {
    /* fields of other:
     *  acc.first ... String
     *  acc.rest ... ILoString
     * methods:
     *  acc.combine() ... String
     *  acc.findAndReplace(String, String) ... ILoString
     *  acc.anyDupes() ... boolean
     *  acc.dupeHelper(String) ... boolean
     *  acc.sort() ... ILoString
     *  acc.insert(String) ... ILoString
     *  acc.isSorted() ... boolean
     *  acc.sortAcc(String) ... boolean
     *  acc.interleave(ILoString) ... ILoString
     *  acc.merge(ILoString) ... ILoString
     *  acc.mergeChecker(String) ... boolean
     *  acc.reverseConcat() ... String
     *  acc.isDoubledList() ... boolean
     *  acc.isDoubledListHelper(String) ... boolean
     *  acc.isPalindromeList() ... boolean
     *  acc.reverse() ... ILoString
     *  acc.reverseHelper(ILoString) ... ILoString
     */

    return this.rest.reverseHelper(new ConsLoString(this.first, acc));
  }
}

// to represent examples for lists of strings
class ExamplesStrings {
  ExamplesStrings() {
  }

  ILoString mt = new MtLoString();

  ILoString mary = new ConsLoString("Mary ", new ConsLoString("had ", new ConsLoString("a ",
      new ConsLoString("little ", new ConsLoString("lamb.", new MtLoString())))));

  ILoString maryDoubled = new ConsLoString("Mary ",
      new ConsLoString("Mary ",
          new ConsLoString("had ", new ConsLoString("had ", new ConsLoString("a ",
              new ConsLoString("a ", new ConsLoString("little ", new ConsLoString("little ",
                  new ConsLoString("lamb.", new ConsLoString("lamb.", new MtLoString()))))))))));

  ILoString marySort = new ConsLoString("a ", new ConsLoString("had ", new ConsLoString("lamb.",
      new ConsLoString("little ", new ConsLoString("Mary ", new MtLoString())))));

  ILoString johnny = new ConsLoString("Johnny ", new ConsLoString("owned ", new ConsLoString("a ",
      new ConsLoString("pretty ", new ConsLoString("car.", new MtLoString())))));

  ILoString johnnySort = new ConsLoString("a ", new ConsLoString("car.", new ConsLoString("Johnny ",
      new ConsLoString("owned ", new ConsLoString("pretty ", new MtLoString())))));

  ILoString maryJohnnyMerge = new ConsLoString("a ",
      new ConsLoString("a ",
          new ConsLoString("car.", new ConsLoString("had ", new ConsLoString("Johnny ",
              new ConsLoString("lamb.", new ConsLoString("little ", new ConsLoString("Mary ",
                  new ConsLoString("owned ", new ConsLoString("pretty ", new MtLoString()))))))))));

  ILoString maryJohnnyInterleave = new ConsLoString("a ",
      new ConsLoString("a ",
          new ConsLoString("had ", new ConsLoString("car.", new ConsLoString("lamb.",
              new ConsLoString("Johnny ", new ConsLoString("little ", new ConsLoString("owned ",
                  new ConsLoString("Mary ", new ConsLoString("pretty ", new MtLoString()))))))))));

  ILoString list1 = new ConsLoString("bottle", new ConsLoString("bottle", new ConsLoString("water",
      new ConsLoString("water", new ConsLoString("chair", new MtLoString())))));
  
  ILoString list1reverse = new ConsLoString("chair", new ConsLoString("water", new ConsLoString("water",
      new ConsLoString("bottle", new ConsLoString("bottle", new MtLoString())))));

  ILoString list2 = new ConsLoString("water", new ConsLoString("water", new ConsLoString("water",
      new ConsLoString("water", new ConsLoString("chair", new MtLoString())))));
  
  ILoString list2reverse = new ConsLoString("chair", new ConsLoString("water", new ConsLoString("water",
      new ConsLoString("water", new ConsLoString("water", new MtLoString())))));

  ILoString list3 = new ConsLoString("bottle", new ConsLoString("water", new ConsLoString("water",
      new ConsLoString("water", new ConsLoString("chair", new MtLoString())))));

  ILoString list4 = new ConsLoString("apple",
      new ConsLoString("banana", new ConsLoString("orange", new MtLoString())));

  ILoString list5 = new ConsLoString("axe",
      new ConsLoString("beyblade", new ConsLoString("ostensible", new MtLoString())));

  ILoString list45InterleaveMerge = new ConsLoString("apple",
      new ConsLoString("axe", new ConsLoString("banana", new ConsLoString("beyblade",
          new ConsLoString("orange", new ConsLoString("ostensible", new MtLoString()))))));

  ILoString maryJohnny = new ConsLoString("Mary ",
      new ConsLoString("Johnny ",
          new ConsLoString("had ",
              new ConsLoString("owned ", new ConsLoString("a ",
                  new ConsLoString("a ", new ConsLoString("little ", new ConsLoString("pretty ",
                      new ConsLoString("lamb.", new ConsLoString("car.", new MtLoString()))))))))));

  ILoString maryMary = new ConsLoString("Mary ",
      new ConsLoString("Mary ",
          new ConsLoString("had ", new ConsLoString("had ", new ConsLoString("a ",
              new ConsLoString("a ", new ConsLoString("little ", new ConsLoString("little ",
                  new ConsLoString("lamb.", new ConsLoString("lamb.", new MtLoString()))))))))));

  ILoString maryList4 = new ConsLoString("Mary ",
      new ConsLoString("apple",
          new ConsLoString("had ",
              new ConsLoString("banana", new ConsLoString("a ", new ConsLoString("orange",
                  new ConsLoString("little ", new ConsLoString("lamb.", new MtLoString()))))))));

  ILoString palListFalse = new ConsLoString("Mary ",
      new ConsLoString("apple",
          new ConsLoString("had ",
              new ConsLoString("banana", new ConsLoString("a ", new ConsLoString("orange",
                  new ConsLoString("little ", new ConsLoString("lamb.", new MtLoString()))))))));

  ILoString palListTrue = new ConsLoString("apple",
      new ConsLoString("banana",
          new ConsLoString("orange",
              new ConsLoString("tomato", new ConsLoString("tomato", new ConsLoString("orange",
                  new ConsLoString("banana", new ConsLoString("apple", new MtLoString()))))))));

  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }

  // test the method findAndReplace
  boolean testFindAndReplace(Tester t) {
    return t.checkExpect(this.list1.findAndReplace("bottle", "water"), list2)
        && t.checkExpect(this.list1.findAndReplace("gahhh", "aaaaahhh"), list1)
        && t.checkExpect(this.mt.findAndReplace("b", "a"), mt);
  }

  // test the method anyDupes
  boolean testAnyDupes(Tester t) {
    return t.checkExpect(this.list1.anyDupes(), true) && t.checkExpect(this.mary.anyDupes(), false)
        && t.checkExpect(this.list3.anyDupes(), true) && t.checkExpect(this.mt.anyDupes(), false);
  }

  // test the dupeHelper method
 // boolean testDupeHelper(Tester t) {}
  
  
  
  
  
  // test the method sort
  boolean testSort(Tester t) {
    return t.checkExpect(this.mary.sort(), marySort)
        && t.checkExpect(this.johnny.sort(), johnnySort) && t.checkExpect(this.mt.sort(), mt);
  }

  // test the method isSorted
  boolean testIsSorted(Tester t) {
    return t.checkExpect(this.mary.isSorted(), false)
        && t.checkExpect(this.marySort.isSorted(), true)
        && t.checkExpect(this.johnny.isSorted(), false)
        && t.checkExpect(this.johnnySort.isSorted(), true)
        && t.checkExpect(this.mt.isSorted(), true);
  }
  
  // test the sortAcc method
  //boolean testSortAcc(Tester t) {}
  
  
  
  
  

  // test the method interleave
  boolean testInterleave(Tester t) {
    return t.checkExpect(this.mary.interleave(this.johnny), this.maryJohnny)
        // different output from merge
        && t.checkExpect(this.marySort.interleave(this.johnnySort), this.maryJohnnyInterleave)
        // same output as merge
        && t.checkExpect(this.list4.interleave(this.list5), this.list45InterleaveMerge)
        && t.checkExpect(this.mary.interleave(this.mary), this.maryMary)
        && t.checkExpect(this.mary.interleave(this.mt), this.mary)
        && t.checkExpect(this.mt.interleave(this.mary), this.mary)
        && t.checkExpect(this.mary.interleave(this.list4), this.maryList4);
  }

  // test the method merge
  boolean testMerge(Tester t) {
    // different output from interleave
    return t.checkExpect(this.marySort.merge(this.johnnySort), this.maryJohnnyMerge)
        // same output as interleave
        && t.checkExpect(this.list4.merge(this.list5), this.list45InterleaveMerge)
        && t.checkExpect(this.mt.merge(this.marySort), this.marySort);
  }
  
  // test the mergeChecker helper method
 // boolean testMergeCheck(Tester t) {}
  
  
  
  
  

  // test the method reverseConcat
  boolean testReverseConcat(Tester t) {
    return t.checkExpect(this.mary.reverseConcat(), "lamb.little a had Mary ")
        && t.checkExpect(this.johnny.reverseConcat(), "car.pretty a owned Johnny ")
        && t.checkExpect(this.list1.reverseConcat(), "chairwaterwaterbottlebottle")
        && t.checkExpect(this.mt.reverseConcat(), "");
  }

  // test the method isDoubledList
  boolean testIsDoubledList(Tester t) {
    return t.checkExpect(this.mary.isDoubledList(), false)
        && t.checkExpect(this.maryDoubled.isDoubledList(), true)
        && t.checkExpect(this.mt.isDoubledList(), true);
  }
  
  // test the isDoubledListHelper
  //boolean testIsDoubledListHelper(Tester t) {}
  
  
  
  
  // test the reverse method
  boolean testReverse(Tester t) {
    return t.checkExpect(this.list1.reverse(), this.list1reverse)
        && t.checkExpect(this.mt.reverse(), this.mt)
        && t.checkExpect(this.list2.reverse(), this.list2reverse);
  }
  
  // test the reverseHelper method
  boolean testReverseHelper(Tester t) {}
  
  
  
  
  // test the method isPalindromeList
  boolean testIsPalindromeList(Tester t) {
    return t.checkExpect(this.palListFalse.isPalindromeList(), false)
        && t.checkExpect(this.palListTrue.isPalindromeList(), true)
        && t.checkExpect(this.mt.isPalindromeList(), true);
  }

}