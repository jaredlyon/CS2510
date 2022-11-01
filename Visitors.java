import java.util.function.BiFunction;
import java.util.function.Function;

import tester.Tester;

interface IArithVisitor<R> {
  
  R accept(IArith a);
  
}

class EvalVisitor implements IArithVisitor<Double> {

  public Double accept(Const c) {
    return c.num;
  }
  
  public Double accept(UnaryFormula u) {
    return null;
    
  }
  
  public Double accept(BinaryFormula b) {
    return null;
  }

  public Double accept(IArith a) {
    return a.applyToIArith(null);
  }
  
}



interface IArith{
 
  double applyToIArith(IArithVisitor<Double> func);

  
}

class Const {
  double num;
  
  Const(double num) {
    this.num = num;
  }
  
  public double applyToIArith(IArithVisitor<Double> func) {
    return func.accept(this);
  }
}

class UnaryFormula implements IArith {
  Function<Double, Double> func;
  String name;
  IArith child;
  
  UnaryFormula(Function<Double, Double> func, String name, IArith child) {
    this.func = func;
    this.name = name;
    this.child = child;
  }
  
  public double applyToIArith(IArithVisitor<Double> func) {
    return func.accept(this);
  }
}

class BinaryFormula implements IArith {
  BiFunction<Double, Double, Double> func;
  String name;
  IArith left;
  IArith right;
  
  BinaryFormula(BiFunction<Double, Double, Double> func, String name, IArith left, IArith right) {
    this.func = func;
    this.name = name;
    this.left = left;
    this.right = right;
  }
  
  public double applyToIArith(IArithVisitor<Double> func) {
    return func.accept(this);
  }
}

