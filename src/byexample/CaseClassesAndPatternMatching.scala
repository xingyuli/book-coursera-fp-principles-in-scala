package byexample

object CaseClassesAndPatternMatching extends App {

  /*
   * Bad!
   * 
   * Having to modify existing code when system grows is always problematic.
   * It introduces versioning and maintenance problems.
   */
  /*
  abstract class Expr {
    def isNumber: Boolean
    def isSum: Boolean
    def numValue: Int
    def leftOp: Expr
    def rightOp: Expr
  }
  
  class Number(n: Int) extends Expr {
    def isNumber: Boolean = true
    def isSum: Boolean = false
    def numValue: Int = n
    def leftOp: Expr = sys.error("Number.leftOp")
    def rightOp: Expr = sys.error("Number.rightOp")
  }
  
  class Sum(e1: Expr, e2: Expr) extends Expr {
    def isNumber: Boolean = false
    def isSum: Boolean = true
    def numValue: Int = sys.error("Sum.numValue")
    def leftOp: Expr = e1
    def rightOp: Expr = e2
  }
  
  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else sys.error("unrecognized expression kind")
  }
  */
  
  /*
   * more object-oriented decomposition approach
   * 
   * much better now, but...
   * 
   * We might want to add new operations on expressions. For instance, we might
   * want to add an operation that pretty-prints an expression tree to standard
   * output.
   * 
   * However, if we had opted for an object-oriented decomposition of
   * expressions, we would need to add a new print procedure to each class.
   * 
   * Hence, classical object-oriented decomposition requires modification of
   * all existing classes when a system is extented with new operations.
   */
  /*
  abstract class Expr {
    def eval: Int
    def print
  }
  
  class Number(n: Int) extends Expr {
    def eval: Int = n
    def print { Console.print(n) }
  }
  
  class Sum(e1: Expr, e2: Expr) extends Expr {
    def eval: Int = e1.eval + e2.eval
    def print {
      Console.print("(")
      e1.print
      Console.print("+")
      e2.print
      Console.print(")")
    }
  }
  */

  abstract class Expr {
    def eval: Int = this match {
      case Number(n) => n
      case Sum(l, r) => l.eval + r.eval
    }
  }
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  
  println(Sum(Number(1), Sum(Number(3), Number(7))).eval)
  
  // Exercise 7.2.2
  abstract class IntTree 
  case object EmptyTree extends IntTree
  case class Node(elem: Int, left: IntTree, right: IntTree) extends IntTree
  
  def contains(t: IntTree, v: Int): Boolean = t match {
    case EmptyTree => false
    case Node(e, l, r) => {
      if (v < e) contains(l, v)
      else if (v > e) contains(r, v)
      else true
    }
  }
  
  def insert(t: IntTree, v: Int): IntTree = t match {
    case EmptyTree => Node(v, EmptyTree, EmptyTree)
    case Node(e, l, r) => {
      if (v < e) Node(e, insert(l, v), r)
      else if (v > e) Node(e, l, insert(r, v))
      else t
    }
  }
  
}