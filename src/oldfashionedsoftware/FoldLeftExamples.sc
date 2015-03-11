package oldfashionedsoftware

import scala.collection.mutable.ArrayBuffer

// Examples taken from http://oldfashionedsoftware.com/2009/07/30/lots-and-lots-of-foldleft-examples/
object FoldLeftExamples {
  
  def sum(xs: List[Int]) = (0 /: xs)(_ + _)       //> sum: (xs: List[Int])Int

  def product(xs: List[Int]) = (1 /: xs)(_ * _)   //> product: (xs: List[Int])Int

  def count(xs: List[Any]) = (0 /: xs)((acc, _) => acc + 1)
                                                  //> count: (xs: List[Any])Int
  
  def average(xs: List[Double]) = (0.0 /: xs)(_ + _) / (0 /: xs)((acc, _) => acc + 1)
                                                  //> average: (xs: List[Double])Double
  average(List(1.2, 2.3, 3.4, 4.5))               //> res0: Double = 2.85


  // forget empty list
  def last[A](xs: List[A]) = (xs.head /: xs)((_, x) => x)
                                                  //> last: [A](xs: List[A])A
  
  def penultimate[A](xs: List[A]) = ((xs.head, xs.tail.head) /: xs)((r, c) => (r._2, c))._1
                                                  //> penultimate: [A](xs: List[A])A
  
  def contains[A](xs: List[A], item: A) = (false /: xs)((r, x) => r || x==item)
                                                  //> contains: [A](xs: List[A], item: A)Boolean
  contains(List(1, 3, 5), 3)                      //> res1: Boolean = true
  contains(List(1, 3, 5), 4)                      //> res2: Boolean = false

  
  def mimicToString[A](xs: List[A]) = xs match {
    case Nil => "List()"
    case h :: t => (("List(" + h) /: t)(_ + ", " + _) + ")"
  }                                               //> mimicToString: [A](xs: List[A])String
  mimicToString(List.range(1, 5))                 //> res3: String = List(1, 2, 3, 4)

  
  def reverse[A](xs: List[A]) = (List[A]() /: xs)((r, c) => c :: r)
                                                  //> reverse: [A](xs: List[A])List[A]
  reverse(List.range(1, 5))                       //> res4: List[Int] = List(4, 3, 2, 1)

  
  def unique[A](xs: List[A]) = (List[A]() /: xs)((r, c) => if (r contains c) r else c :: r).reverse
                                                  //> unique: [A](xs: List[A])List[A]
	unique(List(1, 3, 5, 7, 3, 1))            //> res5: List[Int] = List(1, 3, 5, 7)

  
  def toSet[A](xs: List[A]) = ((Set[A]() /: xs)((r, c) => r + c))
                                                  //> toSet: [A](xs: List[A])scala.collection.immutable.Set[A]
  toSet(List(1, 3, 5, 7, 3, 1))                   //> res6: scala.collection.immutable.Set[Int] = Set(1, 3, 5, 7)


  def double[A](xs: List[A]) = (List[A]() /: xs)((r, c) => c :: c :: r).reverse
                                                  //> double: [A](xs: List[A])List[A]
  double(List(1, 2, 3))                           //> res7: List[Int] = List(1, 1, 2, 2, 3, 3)
  
  
  // my solution
  // def insertionSort[A <% Ordered[A]](xs: List[A]) = (List[A]() /: xs) { (r, c) =>
  //   val pair = r span (_ > c)
  //   pair._1 ::: (c :: pair._2)
  // }.reverse
  
  // better solution provided by the blog
  def insertionSort[A <% Ordered[A]](xs: List[A]) = (List[A]() /: xs) { (r, c) =>
  	val (prefix, suffix) = r span (c < _)
  	prefix ::: (c :: suffix)
  }                                               //> insertionSort: [A](xs: List[A])(implicit evidence$1: A => Ordered[A])List[A
                                                  //| ]
  insertionSort(List("My", "name", "is", "God", "Bless", "You", "how", "about", "you"))
                                                  //> res8: List[String] = List(you, name, is, how, about, You, My, God, Bless)

  def pivot[A <% Ordered[A]](xs: List[A]) = ((List[A](), xs.head, List[A]()) /: xs.tail) { (r, c) =>
    // my solution
    // if (c < r._2) (c :: r._1, xs.head, r._3)
    // else (r._1, xs.head, c :: r._3)
    
    // better solution provided by the blog
    val (r1, pivot, r2) = r
    if (c < pivot) (c :: r1, pivot, r2) else (r1, pivot, c :: r2)
  }                                               //> pivot: [A](xs: List[A])(implicit evidence$2: A => Ordered[A])(List[A], A, L
                                                  //| ist[A])
  
  def encode[A](xs: List[A]): List[(A, Int)] =
    (List[(A, Int)]() /: xs) { (r, c) =>
      // my solution
      // if (r.isEmpty) (c, 1) :: r
      // else {
      //  if (c == r.head._1) (c, r.head._2+1) :: r.tail
      //  else (c, 1) :: r
      // }
      
      // better solution provided by the blog
      r match {
        case (value, count) :: t if value == c => (c, count+1) :: t
        case _ => (c, 1) :: r
      }
    }.reverse                                     //> encode: [A](xs: List[A])List[(A, Int)]
  encode(List(1, 2, 2, 2, 2, 2, 3, 2, 2))         //> res9: List[(Int, Int)] = List((1,1), (2,5), (3,1), (2,2))
  
  
  def decode[A](xs: List[(A, Int)]): List[A] =
    (List[A]() /: xs) { (r, c) =>
      List.range(0, c._2).map(_ => c._1) ::: r
    }.reverse                                     //> decode: [A](xs: List[(A, Int)])List[A]
  decode(List((1,1), (2,5), (3,1), (2,2)))        //> res10: List[Int] = List(1, 2, 2, 2, 2, 2, 3, 2, 2)


  def group[A](xs: List[A], size: Int): List[List[A]] =
    (List[List[A]]() /: xs) { (r, c) =>
      r match {
        case h :: t if h.size != size => (c :: h) :: t
        case _ => List(c) :: r
      }
    }.map(_.reverse).reverse                      //> group: [A](xs: List[A], size: Int)List[List[A]]
  group(List(1, 2, 3, 4, 5, 6, 7), 3)             //> res11: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7))

}