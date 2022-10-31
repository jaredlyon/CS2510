import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import tester.Tester;

class ArrayUtils {
  <T> ArrayList<T> filter(ArrayList<T> arr, Predicate<T> pred) {

    ArrayList<T> result = new ArrayList<T>();

    for (int i = 0; i < arr.size(); i++) {
      if (pred.test(arr.get(i))) {
        result.add(arr.get(i));
      }
    }

    return result;
  }

  <T> ArrayList<T> filterNot(ArrayList<T> arr, Predicate<T> pred) {

    ArrayList<T> result = new ArrayList<T>();

    for (int i = 0; i < arr.size(); i++) {
      if (!(pred.test(arr.get(i)))) {
        result.add(arr.get(i));
      }
    }

    return result;
  }

  <T> ArrayList<T> customFilter(ArrayList<T> arr, Predicate<T> pred, boolean keepPassing) {

    ArrayList<T> result = new ArrayList<T>();

    for (int i = 0; i < arr.size(); i++) {
      if (!keepPassing) {
        if (!(pred.test(arr.get(i)))) {
          result.add(arr.get(i));
        }
      } else if (keepPassing) {
        if (pred.test(arr.get(i))) {
          result.add(arr.get(i));
        }
      }
    }

    return result;
  }

  <T> ArrayList<T> removeFailing(ArrayList<T> arr, Predicate<T> pred) {

    for (int i = 0; i < arr.size(); i++) {
      if (!(pred.test(arr.get(i)))) {
        arr.remove(i);
        i--;
      }
    }

    return arr;
  }

  <T> ArrayList<T> removePassing(ArrayList<T> arr, Predicate<T> pred) {

    for (int i = 0; i < arr.size(); i++) {
      if (pred.test(arr.get(i))) {
        arr.remove(i);
        i--;
      }
    }

    return arr;
  }

  <T> ArrayList<T> customRemove(ArrayList<T> arr, Predicate<T> pred, boolean keepPassing) {

    for (int i = 0; i < arr.size(); i++) {
      if (!keepPassing) {
        if (pred.test(arr.get(i))) {
          arr.remove(i);
          i--;
        }
      } else if (keepPassing) {
        if (!(pred.test(arr.get(i)))) {
          arr.remove(i);
          i--;
        }
      }
    }

    return arr;
  }

  <T> ArrayList<T> interweave(ArrayList<T> arr1, ArrayList<T> arr2) {
    /**
    ArrayList<T> result = new ArrayList<T>();
    ArrayList<T> extra = new ArrayList<T>();
    int size1 = arr1.size();
    int size2 = arr2.size();
    int smaller = Math.min(size1, size2);
    int bigger = Math.max(size1, size2);
    boolean runExtra = true;

    if (size1 > size2) {
      extra = arr1;
    } else if (size2 > size1) {
      extra = arr2;
    } else {
      runExtra = false;
    }

    for (int i = 0; i < smaller; i++) {
      result.add(arr1.get(i));
      result.add(arr2.get(i));
    }

    if (runExtra) {
      for (int i = smaller; i < bigger; i++) {
        result.add(extra.get(i));
      }
    }

    return result;
    */
    
    return interweave(arr1, arr2, 1, 1);
  }

  <T> ArrayList<T> interweave(ArrayList<T> arr1, ArrayList<T> arr2, int getFrom1, int getFrom2) {
    if (arr1.isEmpty() && arr2.isEmpty()) {
      return new ArrayList<T>();
    } else if (arr1.isEmpty()) {
      return arr2;
    } else if (arr2.isEmpty()) {
      return arr1;
    } else {
      ArrayList<T> temp = new ArrayList<T>();
      
      for (int i = 0; i < getFrom1; i++) {
        temp.add(arr1.remove(0));
      }
      
      ArrayList<T> result = interweave(arr2, arr1, getFrom2, getFrom1);
      result.addAll(0, temp);
      return result;
    }
  }
}

class Greater implements Predicate<Integer> {
  int target;

  Greater(int target) {
    this.target = target;
  }

  public boolean test(Integer t) {
    return t > target;
  }
}

class Longer implements Predicate<String> {
  int target;

  Longer(int target) {
    this.target = target;
  }

  public boolean test(String s) {
    return s.length() > target;
  }
}

class ExamplesArray {
  ExamplesArray() {

  }

  ArrayUtils au = new ArrayUtils();
  ArrayList<Integer> ints1 = new ArrayList<Integer>();
  ArrayList<Integer> ints2 = new ArrayList<Integer>();
  ArrayList<String> strings1 = new ArrayList<String>();
  ArrayList<String> strings2 = new ArrayList<String>();

  void initData() {
    this.ints1.clear();
    this.ints2.clear();
    this.strings1.clear();
    this.strings2.clear();

    this.ints1.add(1);
    this.ints1.add(2);
    this.ints1.add(3);

    this.ints2.add(6);
    this.ints2.add(5);
    this.ints2.add(4);
    this.ints2.add(3);
    this.ints2.add(2);
    this.ints2.add(1);

    this.strings1.add("hello");
    this.strings1.add("goodbye");
    this.strings1.add("fundies 2");

    this.strings2.add("a");
    this.strings2.add("bb");
    this.strings2.add("ccc");
  }

  ArrayList<Integer> results1 = new ArrayList<Integer>();
  ArrayList<Integer> results2 = new ArrayList<Integer>();
  ArrayList<String> results3 = new ArrayList<String>();
  ArrayList<String> results4 = new ArrayList<String>();
  ArrayList<Integer> results5 = new ArrayList<Integer>();
  ArrayList<String> results6 = new ArrayList<String>();
  ArrayList<Integer> results7 = new ArrayList<Integer>();

  void initResults() {
    this.results1.clear();
    this.results2.clear();
    this.results3.clear();
    this.results4.clear();
    this.results5.clear();
    this.results6.clear();
    this.results7.clear();

    this.results1.add(2);
    this.results1.add(3);

    this.results2.add(1);

    this.results3.add("bb");
    this.results3.add("ccc");

    this.results4.add("a");

    this.results5.add(1);
    this.results5.add(6);
    this.results5.add(2);
    this.results5.add(5);
    this.results5.add(3);
    this.results5.add(4);
    this.results5.add(3);
    this.results5.add(2);
    this.results5.add(1);

    this.results6.add("hello");
    this.results6.add("a");
    this.results6.add("goodbye");
    this.results6.add("bb");
    this.results6.add("fundies 2");
    this.results6.add("ccc");
    
    this.results7.add(1);
    this.results7.add(6);
    this.results7.add(2);
    this.results7.add(5);
    this.results7.add(3);
    this.results7.add(4);
    this.results7.add(3);
    this.results7.add(2);
    this.results7.add(1);
  }

  // test filter
  void testFilter(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.filter(this.ints1, new Greater(1)), this.results1);
    t.checkExpect(au.filter(this.strings2, new Longer(1)), this.results3);
  }

  //test notFilter
  void testNotFilter(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.filterNot(this.ints1, new Greater(1)), this.results2);
    t.checkExpect(au.filterNot(this.strings2, new Longer(1)), this.results4);
  }

  //test notFilter
  void testCustomFilter(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.customFilter(this.ints1, new Greater(1), true), this.results1);
    t.checkExpect(au.customFilter(this.strings2, new Longer(1), true), this.results3);

    this.initData();
    this.initResults();

    t.checkExpect(au.customFilter(this.ints1, new Greater(1), false), this.results2);
    t.checkExpect(au.customFilter(this.strings2, new Longer(1), false), this.results4);
  }

  //test removeFailing
  void testRemoveFailing(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.removeFailing(this.ints1, new Greater(1)), this.results1);
    t.checkExpect(au.removeFailing(this.strings2, new Longer(1)), this.results3);
  } 

  // test removePassing
  void testRemovePassing(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.removePassing(this.ints1, new Greater(1)), this.results2);
    t.checkExpect(au.removePassing(this.strings2, new Longer(1)), this.results4);
  }

  // test notFilter
  void testCustomRemove(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.customRemove(this.ints1, new Greater(1), true), this.results1);
    t.checkExpect(au.customRemove(this.strings2, new Longer(1), true), this.results3);

    this.initData();
    this.initResults();

    t.checkExpect(au.customRemove(this.ints1, new Greater(1), false), this.results2);
    t.checkExpect(au.customRemove(this.strings2, new Longer(1), false), this.results4);
  }

  // test interweave
  void testInterweave(Tester t) {
    this.initData();
    this.initResults();

    t.checkExpect(au.interweave(this.ints1, this.ints2), this.results5);
    t.checkExpect(au.interweave(this.strings1, this.strings2), this.results6);
    t.checkExpect(au.interweave(this.strings1,this.strings2, 1, 1), this.results6);
    t.checkExpect(au.interweave(this.ints1,this.ints2, 2, 3), this.results7);
  }
}