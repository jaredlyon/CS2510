import java.util.function.BiFunction;
import java.util.function.Function;

import tester.Tester;

// represents an visitor interface
interface IArithVisitor<R> extends Function<IArith, R> {
  R visitConst(Const c);
  R visitUnary(UnaryFormula f);
  R visitBinary(BinaryFormula f);
}

// evaluates an arithmetic tree
class EvalVisitor implements IArithVisitor<Double> {

  @Override
  public Double visitConst(Const c) {
    return c.num;
  }

  @Override
  public Double visitUnary(UnaryFormula f) {
    return f.func.apply(f.child.accept(this));
  }

  @Override
  public Double visitBinary(BinaryFormula f) {
    return f.func.apply(f.left.accept(this), f.right.accept(this));
  }

  @Override
  public Double apply(IArith t) {
    return t.accept(this);
  } 
}

// prints an arithmetic tree
class PrintVisitor implements IArithVisitor<String> {

  @Override
  public String visitConst(Const c) {
    return Double.toString(c.num);
  }

  @Override
  public String visitUnary(UnaryFormula f) {
    return f.name + f.child.accept(this);
  }

  @Override
  public String visitBinary(BinaryFormula f) {
    return f.name + f.left.accept(this) + f.right.accept(this);
  }

  @Override
  public String apply(IArith t) {
    return t.accept(this);
  } 
}

// doubles the consts in an arithmetic tree
class DoublerVisitor implements IArithVisitor<IArith> {

  @Override
  public IArith visitConst(Const c) {
    return new Const(c.num * 2);
  }

  @Override
  public IArith visitUnary(UnaryFormula f) {
    return new UnaryFormula(f.func, f.name, f.child.accept(this));
  }

  @Override
  public IArith visitBinary(BinaryFormula f) {
    return new BinaryFormula(f.func, f.name, f.left.accept(this), f.right.accept(this));
  }

  @Override
  public IArith apply(IArith t) {
    return t.accept(this);
  } 
}

// checks if this arithmetic tree has negative numbers
class NoNegativeResults implements IArithVisitor<Boolean> {

  @Override
  public Boolean visitConst(Const c) {
    return c.num < 0;
  }

  @Override
  public Boolean visitUnary(UnaryFormula f) {
    return f.child.accept(this);
  }

  @Override
  public Boolean visitBinary(BinaryFormula f) {
    return f.left.accept(this) || f.right.accept(this);
  }

  @Override
  public Boolean apply(IArith t) {
    return t.accept(this);
  } 
}

// represents an interface of arithmetic operations
interface IArith {
  <R> R accept(IArithVisitor<R> visitor);
}

// represents a number
class Const implements IArith{
  double num;

  Const(double num) {
    this.num = num;
  }

  public <R> R accept(IArithVisitor<R> visitor) { return visitor.visitConst(this); }
}

// represents a unary formula
class UnaryFormula implements IArith {
  Function<Double, Double> func;
  String name;
  IArith child;

  UnaryFormula(Function<Double, Double> func, String name, IArith child) {
    this.func = func;
    this.name = name;
    this.child = child;
  }

  public <R> R accept(IArithVisitor<R> visitor) { return visitor.visitUnary(this); }
}

// represents a binary formula
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

  public <R> R accept(IArithVisitor<R> visitor) { return visitor.visitBinary(this); }
}

// represents a sqr unary operation
class Sqr implements Function<Double, Double> {

  // squares the constant
  public Double apply(Double t) {
    return t * t;
  }
}

// represents a neg unary operation
class Neg implements Function<Double, Double> {

  // negates the constant
  public Double apply(Double t) {
    return t * -1;
  }
}

//represents a add binary operation
class Plus implements BiFunction<Double, Double, Double> {

  // adds two constants
  public Double apply(Double t, Double u) {
    return t + u;
  }
}

//represents a add binary operation
class Minus implements BiFunction<Double, Double, Double> {

  // adds two constants
  public Double apply(Double t, Double u) {
    return t - u;
  }
}

//represents a add binary operation
class Mul implements BiFunction<Double, Double, Double> {

  // adds two constants
  public Double apply(Double t, Double u) {
    return t * u;
  }
}

//represents a add binary operation
class Div implements BiFunction<Double, Double, Double> {

  // adds two constants
  public Double apply(Double t, Double u) {
    return t / u;
  }
}

class ExamplesVisitors {
  // examples of constants
  Const zero = new Const(0.0);
  
  Const one = new Const(1.0);
  Const two = new Const(2.0);
  Const three = new Const(3.0);
  Const four = new Const(4.0);
  Const five = new Const(5.0);
  Const six = new Const(6.0);
  Const seven = new Const(7.0);
  Const eight = new Const(8.0);
  Const nine = new Const(9.0);

  Const oneNeg = new Const(-1.0);
  Const twoNeg = new Const(-2.0);
  Const threeNeg = new Const(-3.0);
  Const fourNeg = new Const(-4.0);
  Const fiveNeg = new Const(-5.0);
  Const sixNeg = new Const(-6.0);
  Const sevenNeg = new Const(-7.0);
  Const eightNeg = new Const(-8.0);
  Const nineNeg = new Const(-9.0);

  // test functions
  void testSqr(Tester t) {
    t.checkExpect(new Sqr().apply(2.0), 4.0);
    t.checkExpect(new Sqr().apply(4.0), 16.0);
    t.checkExpect(new Sqr().apply(6.0), 36.0);
  }

  void testNeg(Tester t) {
    t.checkExpect(new Neg().apply(2.0), -2.0);
    t.checkExpect(new Neg().apply(4.0), -4.0);
    t.checkExpect(new Neg().apply(6.0), -6.0);
  }

  void testPlus(Tester t) {
    t.checkExpect(new Plus().apply(1.0, 2.0), 3.0);
    t.checkExpect(new Plus().apply(3.0, 4.0), 7.0);
    t.checkExpect(new Plus().apply(8.0, 1.0), 9.0);
  }

  void testMinus(Tester t) {
    t.checkExpect(new Minus().apply(7.0, 2.0), 5.0);
    t.checkExpect(new Minus().apply(7.0, 4.0), 3.0);
    t.checkExpect(new Minus().apply(2.0, 4.0), -2.0);
  }

  void testMul(Tester t) {
    t.checkExpect(new Mul().apply(1.0, 2.0), 2.0);
    t.checkExpect(new Mul().apply(-1.0, 2.0), -2.0);
    t.checkExpect(new Mul().apply(-1.0, -2.0), 2.0);
  }

  void testDiv(Tester t) {
    t.checkExpect(new Div().apply(4.0, 2.0), 2.0);
    t.checkExpect(new Div().apply(-4.0, 2.0), -2.0);
    t.checkExpect(new Div().apply(-4.0, -2.0), 2.0);
  }
  
  IArith neg = new UnaryFormula(new Neg(), "neg", this.one);
  IArith plus = new BinaryFormula(new Plus(), "plus", this.two, this.three);
  IArith div = new BinaryFormula(new Div(), "div", this.neg, this.plus);
}