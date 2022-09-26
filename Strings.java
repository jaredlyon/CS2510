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
  boolean dupeHelper(String ref, ILoString list);
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
    return this;
  }

  // checks if there are repeated strings in this list
  public boolean anyDupes() {
    return false;
  }

  // helper for anyDupes
  public boolean dupeHelper(String ref, ILoString list) {
    return false;
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
    return this.dupeHelper(this.first, this.rest);
  }

  // helper for anyDupes
  public boolean dupeHelper(String ref, ILoString list) {
    return this.first.equals(ref)
        || list.anyDupes();
  }
}

// to represent examples for lists of strings
class ExamplesStrings {

  ILoString mary = new ConsLoString("Mary ",
      new ConsLoString("had ",
          new ConsLoString("a ",
              new ConsLoString("little ",
                  new ConsLoString("lamb.", new MtLoString())))));

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

  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return 
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }

  // test the method findAndReplace
  boolean testFindAndReplace(Tester t) {
    return 
        t.checkExpect(this.list1.findAndReplace("bottle", "water"), list2);
  }
  
  // test the method anyDupes
  boolean testAnyDupes(Tester t) {
    return 
        t.checkExpect(this.list1.anyDupes(), true)
        && t.checkExpect(this.mary.anyDupes(), false);
  }

}