package byexample.generic_type_and_methods

object GenericTypesAndMethods {
  
  /*
  abstract class IntStack {
    def push(x: Int): IntStack = new IntNonEmptyStack(x, this)
    def isEmpty: Boolean
    def top: Int
    def pop: IntStack
  }
  
  class IntEmptyStack extends IntStack {
    def isEmpty: Boolean = true
    def top: Int = sys.error("EmptyStack.top")
    def pop: IntStack = sys.error("EmptyStack.pop")
  }
  
  class IntNonEmptyStack(elem: Int, rest: IntStack) extends IntStack {
    def isEmpty: Boolean = false
    def top: Int = elem
    def pop: IntStack = rest
  }
  */

  abstract class Stack[+A] {
    // lower bounds
    def push[B >: A](x: B): Stack[B] = new NonEmptyStack(x, this)
    def isEmpty: Boolean
    def top: A
    def pop: Stack[A]
  }
  
  /*
  class EmptyStack[A] extends Stack[A] {
    def isEmpty: Boolean = true
    def top: A = sys.error("EmptyStack.top")
    def pop: Stack[A] = sys.error("EmptyStack.pop")
  }
  */
  
  // Nothing is a subtype of all other types.
  // Hence, for co-variant stacks, Stack[Nothing] is a subtype of Stack[T], for
  // any other type T.
  object EmptyStack extends Stack[Nothing] {
    def isEmpty = true
    def top = sys.error("EmptyStack.top")
    def pop = sys.error("EmptyStack.pop")
  }
  
  class NonEmptyStack[+A](elem: A, rest: Stack[A]) extends Stack[A] {
    def isEmpty = false
    def top = elem
    def pop = rest
  }
  
  val x = EmptyStack
  val y = x push 1 push 2
  println(y.pop.top)
  
  def isPrefix[A](p: Stack[A], s: Stack[A]): Boolean = {
    if (p.isEmpty) true
    else p.top == s.top && isPrefix(p.pop, s.pop)
  }
  val s1 = EmptyStack.push("abc")
  val s2 = EmptyStack.push("abc").push(s1.top)
  println(isPrefix(s1, s2))
 
 
  // Section: Type Parameter Bounds
 
  // upper bounds
  trait Set[A <: Ordered[A]] {
    def incl(x: A): Set[A]
    def contains(x: A): Boolean
  }
  
  class EmptySet[A <: Ordered[A]] extends Set[A] {
    def incl(x: A): Set[A] = new NonEmptySet[A](x, new EmptySet[A], new EmptySet[A])
    def contains(x: A): Boolean = false
  }
  
  class NonEmptySet[A <: Ordered[A]](elem: A, left: Set[A], right: Set[A]) extends Set[A] {
    def incl(x: A): Set[A] =
      if (x < elem) left incl x
      else if (x > elem) right incl x
      else this
    def contains(x: A): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
  }
  
  case class Num(value: Double) extends Ordered[Num] {
    override def compare(that: Num): Int =
      if (this.value < that.value) -1
      else if (this.value > that.value) 1
      else 0
  }
 
  val s = new EmptySet[Num] incl Num(1.0) incl Num(2.0)
  s contains Num(1.5)
  
}