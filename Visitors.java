import java.util.function.BiFunction;
import java.util.function.Function;
import tester.Tester;

// represents an visitor interface
interface IArithVisitor<R> extends Function<IArith, R> {
  // visits a const
  R visitConst(Const c);

  // visits a unary formula
  R visitUnary(UnaryFormula f);

  // visits a binary formula
  R visitBinary(BinaryFormula f);
}

// evaluates an arithmetic tree
class EvalVisitor implements IArithVisitor<Double> {

  // returns the value of a const
  public Double visitConst(Const c) {
    return c.num;
  }

  // applies a unary function
  public Double visitUnary(UnaryFormula f) {
    return f.func.apply(f.child.accept(this));
  }

  // applies a binary function
  public Double visitBinary(BinaryFormula f) {
    return f.func.apply(f.left.accept(this), f.right.accept(this));
  }

  // applies this
  public Double apply(IArith t) {
    return t.accept(this);
  } 
}

// prints an arithmetic tree
class PrintVisitor implements IArithVisitor<String> {

  // converts a const to a string
  public String visitConst(Const c) {
    return Double.toString(c.num);
  }

  // converts a unary formula to a string
  public String visitUnary(UnaryFormula f) {
    return "(" + f.name + " " + f.child.accept(this) + ")";
  }

  // converts a binary formula to a string
  public String visitBinary(BinaryFormula f) {
    return "(" + f.name + " " + f.left.accept(this) + " " + f.right.accept(this) + ")";
  }

  // applies this
  public String apply(IArith t) {
    return t.accept(this);
  } 
}

// doubles the consts in an arithmetic tree
class DoublerVisitor implements IArithVisitor<IArith> {

  // doubles a const
  public IArith visitConst(Const c) {
    return new Const(c.num * 2);
  }

  // doubles all consts in a unary formula
  public IArith visitUnary(UnaryFormula f) {
    return new UnaryFormula(f.func, f.name, f.child.accept(this));
  }

  // doubles all consts in a binary formula
  public IArith visitBinary(BinaryFormula f) {
    return new BinaryFormula(f.func, f.name, f.left.accept(this), f.right.accept(this));
  }

  // applies this
  public IArith apply(IArith t) {
    return t.accept(this);
  } 
}

// checks if this arithmetic tree has negative numbers
// returns true if there are no negative results
class NoNegativeResults implements IArithVisitor<Boolean> {

  // checks if a const is negative
  public Boolean visitConst(Const c) {
    return c.num >= 0;
  }

  // checks for negatives in a unary formula
  public Boolean visitUnary(UnaryFormula f) {
    return new EvalVisitor().visitUnary(f) >= 0
        && new EvalVisitor().apply(f.child) >= 0;
  }

  // checks for negatives in a binary formula
  public Boolean visitBinary(BinaryFormula f) {
    return new EvalVisitor().visitBinary(f) >= 0
        && new EvalVisitor().apply(f.left) >= 0
        && new EvalVisitor().apply(f.right) >= 0;
  }

  // accepts this
  public Boolean apply(IArith t) {
    return t.accept(this);
  } 
}

// represents an interface of arithmetic operations
interface IArith {
  <R> R accept(IArithVisitor<R> visitor);
}

// represents a number
class Const implements IArith {
  double num;

  Const(double num) {
    this.num = num;
  }

  // accepts a visitor and applies it to this const
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitConst(this);
  }
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

  // accepts a visitor and applies it to this unary formula
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitUnary(this);
  }
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

  // accepts a visitor and applies it to this binary formula
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitBinary(this);
  }
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

  // test sqr
  void testSqr(Tester t) {
    t.checkExpect(new Sqr().apply(2.0), 4.0);
    t.checkExpect(new Sqr().apply(4.0), 16.0);
    t.checkExpect(new Sqr().apply(6.0), 36.0);
  }

  // test neg
  void testNeg(Tester t) {
    t.checkExpect(new Neg().apply(2.0), -2.0);
    t.checkExpect(new Neg().apply(4.0), -4.0);
    t.checkExpect(new Neg().apply(6.0), -6.0);
  }

  // test plus
  void testPlus(Tester t) {
    t.checkExpect(new Plus().apply(1.0, 2.0), 3.0);
    t.checkExpect(new Plus().apply(3.0, -4.0), -1.0);
    t.checkExpect(new Plus().apply(-8.0, -1.0), -9.0);
  }

  // test minus
  void testMinus(Tester t) {
    t.checkExpect(new Minus().apply(7.0, 2.0), 5.0);
    t.checkExpect(new Minus().apply(7.0, -4.0), 11.0);
    t.checkExpect(new Minus().apply(-2.0, -4.0), 2.0);
  }

  // test mul
  void testMul(Tester t) {
    t.checkExpect(new Mul().apply(1.0, 2.0), 2.0);
    t.checkExpect(new Mul().apply(-1.0, 2.0), -2.0);
    t.checkExpect(new Mul().apply(-1.0, -2.0), 2.0);
  }

  // test div
  void testDiv(Tester t) {
    t.checkExpect(new Div().apply(4.0, 2.0), 2.0);
    t.checkExpect(new Div().apply(-4.0, 2.0), -2.0);
    t.checkExpect(new Div().apply(-4.0, -2.0), 2.0);
  }

  UnaryFormula neg1 = new UnaryFormula(new Neg(), "neg", this.one);
  UnaryFormula sqr1 = new UnaryFormula(new Sqr(), "sqr", this.two);
  BinaryFormula plus1 = new BinaryFormula(new Plus(), "plus", this.two, this.three);
  BinaryFormula div1 = new BinaryFormula(new Div(), "div", this.neg1, this.plus1);
  BinaryFormula mul1 = new BinaryFormula(new Mul(), "mul", this.oneNeg, this.two);
  BinaryFormula mul2 = new BinaryFormula(new Mul(), "mul", this.one, this.two);

  // test visitConst
  void testVisitConst(Tester t) {
    t.checkExpect(new EvalVisitor().visitConst(this.two), 2.0);
    t.checkExpect(new PrintVisitor().visitConst(this.two), "2.0");
    t.checkExpect(new DoublerVisitor().visitConst(this.two), this.four);
    t.checkExpect(new NoNegativeResults().visitConst(this.two), true);
    t.checkExpect(new NoNegativeResults().visitConst(this.twoNeg), false);
  }

  // test visitUnary
  void testVisitUnary(Tester t) {
    t.checkExpect(new EvalVisitor().visitUnary(this.neg1), -1.0);
    t.checkExpect(new PrintVisitor().visitUnary(this.neg1), "(neg 1.0)");
    t.checkExpect(new DoublerVisitor().visitUnary(this.neg1),
        new UnaryFormula(new Neg(), "neg", this.two));
    t.checkExpect(new NoNegativeResults().visitUnary(this.neg1), false);
    t.checkExpect(new NoNegativeResults().visitUnary(this.sqr1), true);
  }

  // test visitBinary
  void testVisitBinary(Tester t) {
    t.checkExpect(new EvalVisitor().visitBinary(this.plus1), 5.0);
    t.checkExpect(new PrintVisitor().visitBinary(this.plus1), "(plus 2.0 3.0)");
    t.checkExpect(new PrintVisitor().visitBinary(this.div1), "(div (neg 1.0) (plus 2.0 3.0))");
    t.checkExpect(new DoublerVisitor().visitBinary(this.plus1),
        new BinaryFormula(new Plus(), "plus", this.four, this.six));
    t.checkExpect(new NoNegativeResults().visitBinary(this.mul1), false);
    t.checkExpect(new NoNegativeResults().visitBinary(this.mul2), true);
    t.checkExpect(new NoNegativeResults().visitBinary(this.div1), false);
  }

  // test apply
  void testApply(Tester t) {
    t.checkExpect(new EvalVisitor().apply(this.two), 2.0);
    t.checkExpect(new PrintVisitor().apply(this.two), "2.0");
    t.checkExpect(new DoublerVisitor().apply(this.two), this.four);
    t.checkExpect(new NoNegativeResults().apply(this.two), true);
    t.checkExpect(new NoNegativeResults().apply(this.twoNeg), false);
  }

  // test accept
  void testAccept(Tester t) {
    // for const
    t.checkExpect(this.one.accept(new EvalVisitor()), 1.0);
    t.checkExpect(this.one.accept(new PrintVisitor()), "1.0");
    t.checkExpect(this.one.accept(new DoublerVisitor()), new Const(2.0));
    t.checkExpect(this.one.accept(new NoNegativeResults()), true);
    t.checkExpect(this.oneNeg.accept(new NoNegativeResults()), false);

    // for unary
    t.checkExpect(this.neg1.accept(new EvalVisitor()), -1.0);
    t.checkExpect(this.neg1.accept(new PrintVisitor()), "(neg 1.0)");
    t.checkExpect(this.neg1.accept(new DoublerVisitor()),
        new UnaryFormula(new Neg(), "neg", new Const(2.0)));
    t.checkExpect(this.neg1.accept(new NoNegativeResults()), false);
    t.checkExpect(this.sqr1.accept(new NoNegativeResults()), true);

    // for binary
    t.checkExpect(this.mul1.accept(new EvalVisitor()), -2.0);
    t.checkExpect(this.mul1.accept(new PrintVisitor()), "(mul -1.0 2.0)");
    t.checkExpect(this.mul1.accept(new DoublerVisitor()),
        new BinaryFormula(new Mul(), "mul", new Const(-2.0), new Const(4.0)));
    t.checkExpect(this.mul1.accept(new NoNegativeResults()), false);
    t.checkExpect(this.mul2.accept(new NoNegativeResults()), true);
  }
}