package byexample.lists

object Lists extends App {
  
  val fruit: List[String] = List("apples", "orange", "pears")
  val nums: List[Int] = List(1, 2, 3, 4)
  val diag3: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  
  def msort[A](less: (A, A) => Boolean)(xs: List[A]): List[A] = {
    def merge(xs1: List[A], xs2: List[A]): List[A] = {
      if (xs1.isEmpty) xs2
      else if (xs2.isEmpty) xs1
      else if (less(xs1.head, xs2.head)) xs1.head :: merge(xs1.tail, xs2)
      else xs2.head :: merge(xs1, xs2.tail)
    }
    val n = xs.length / 2
    if (n == 0) xs
    else merge(msort(less)(xs take n), msort(less)(xs drop n))
  }
  
  println(msort((x: Int, y: Int) => x < y)(1 :: 4 :: 2 :: 5 :: -1 :: Nil))
  
  def map[A, B](xs: List[A])(f: A => B): List[B] = xs match {
    case Nil => Nil
    case h :: t => f(h) :: map(t)(f)
  }
  
  def foreach[A](xs: List[A])(f: A => Unit) {
    xs match {
      case Nil => ()
      case h :: t => f(h); foreach(t)(f)
    }
  }
  
  def column[A](xs: List[List[A]], index: Int): List[A] =
    map(xs)(row => row(index))
  
  def filter[A](xs: List[A])(p: A => Boolean): List[A] = xs match {
    case Nil => Nil
    case h :: t => if (p(h)) h :: filter(t)(p) else filter(t)(p)
  }
    
  println(map(List(1, 2, 3))(_*2))
  println(column(List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9)), 2))
  foreach(List("Hello", "World"))(println)
  
}