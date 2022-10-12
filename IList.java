import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import tester.Tester;


interface ILoString {
  
}

interface IList<T> {
  IList<T> filter(Predicate<T> pred);
  <U> IList<U> map(Function<T,U> converter);
  <U> U fold(BiFunction<T,U,U> converter,U initial);
  IList<T> zip(IList<T> t, Function<T,T> zipper)
  IList<T> zipHelper(IList<T> t, Function<T,T> zipper);  
}

class MtList<T> implements IList<T> {
  
  MtList() {}

  @Override
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new MtList<U>();
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return initial;
  }

  @Override
  public IList<T> zip(IList<T> t, Function<T, T> zipper) {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }
  
  public IList<T> zipHelper(IList<T> t, Function<T, T> zipper) {
    return t;
  }
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
  
  ConsList(T first,IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first,this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new ConsList<U>(converter.apply(this.first),this.rest.map(converter));
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return converter.apply(this.first, this.rest.fold(converter,initial));
  }

  @Override
  public IList<T> zip(IList<T> t, Function<T, T> zipper) {
    // TODO Auto-generated method stub
    return t.zipHelper(this, zipper);
  }
  
  public IList<T> zipHelper(IList<T> t, Function<T, T> zipper) {
    return new ConsList<T>(this.first.apply(this.first.t), this.rest.apply(this.rest.t));
  }
}

class TMonth implements Predicate<String> {
  boolean apply(String s) {
    return s.substring(0,1).equals("t");
  }

  @Override
  public boolean test(String t) {
    // TODO Auto-generated method stub
    return false;
  }
}

class ErMonth implements BiFunction<String,Integer,Integer> {

  @Override
  public Integer apply(String t, Integer u) {
    if (t.substring(t.length() - 2, t.length()).equals("er")) {
      return u + 1;
    }
      else { 
        return u;
      }
  } 
}

class AbbrevMonth implements Function<String,String> {

  public String apply(String t) {
    return t.substring(0,3);
  }
}

class Zipped implements Function<Double, Double> {
  
  public Double apply(Double d) {
    return this * d;
  }
}

class ExamplesLists{
  ExamplesLists() {}
  
  IList<String> months = new ConsList<String>("january", 
                            new ConsList<String> ("february", 
                                new ConsList<String>("march",
                                    new ConsList<String>("april", 
                                        new ConsList<String>("may",
                                            new ConsList<String>("june",
                                                new ConsList<String>("july",
                                                    new ConsList<String>("august",
                                                        new ConsList<String>("september",
                                                            new ConsList<String>("october",
                                                                new ConsList<String>("november",
                                                                    new ConsList<String>("december",
                                                                        new MtList<String>()))))))))))));
  
  IList<String> AbbrevMonths = new ConsList<String>("jan", 
      new ConsList<String> ("feb", 
          new ConsList<String>("mar",
              new ConsList<String>("apr", 
                  new ConsList<String>("may",
                      new ConsList<String>("jun",
                          new ConsList<String>("jul",
                              new ConsList<String>("aug",
                                  new ConsList<String>("sep",
                                      new ConsList<String>("oct",
                                          new ConsList<String>("nov",
                                              new ConsList<String>("dec",
                                                  new MtList<String>()))))))))))));
  
  IList<String> erMonths = new ConsList<String>("september",
                              new ConsList<String>("october",
                                new ConsList<String>("november",
                                    new ConsList<String>("december",
                                        new MtList<String>()))));
  
  IList<String> tMonths = new MtList<String>();
  
  boolean testTMonth(Tester t) {
    return t.checkExpect(this.months.filter(new TMonth()), this.tMonths);
  }
  
  boolean testErMonth(Tester t) {
    return t.checkExpect(this.months.fold(new ErMonth(), 0), 4);
  }
  
  boolean testAbbrevMonth(Tester t) {
    return t.checkExpect(this.months.map(new AbbrevMonth()), this.AbbrevMonths);
  }
  
  
}