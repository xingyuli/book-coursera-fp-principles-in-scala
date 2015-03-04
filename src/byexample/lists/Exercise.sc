package byexample.lists

import scala.annotation.tailrec

object Exercise {

  // Exercise 9.1.1
  def insert(x: Int, orderedXs: List[Int]): List[Int] = orderedXs match {
    case List() => List(x)
    case h :: t => if (x > h) h :: insert(x, t) else x :: orderedXs
  }                                               //> insert: (x: Int, orderedXs: List[Int])List[Int]
  
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case h :: t => insert(h, isort(t))
  }                                               //> isort: (xs: List[Int])List[Int]
  
  isort(1 :: 4 :: 2 :: 5 :: -1 :: Nil)            //> res0: List[Int] = List(-1, 1, 2, 4, 5)
  
  // Exercise 9.2.1
  def length[A](xs: List[A]): Int = {
    @tailrec def innerLength(acc: Int, rest: List[A]): Int = rest match {
      case Nil => acc
      case h :: t => innerLength(acc+1, t)
    }
    innerLength(0, xs)
  }                                               //> length: [A](xs: List[A])Int
 
  // Exercise 9.4.1
  def squareList1(xs: List[Int]): List[Int] = xs match {
    case List() => Nil
    case h :: t => (h * h) :: squareList1(t)
  }                                               //> squareList1: (xs: List[Int])List[Int]
  
  def squareList2(xs: List[Int]): List[Int] = xs map { x => x * x }
                                                  //> squareList2: (xs: List[Int])List[Int]
  
  squareList1(1 :: 2 :: 3 :: Nil)                 //> res1: List[Int] = List(1, 4, 9)
  squareList2(1 :: 2 :: 3 :: Nil)                 //> res2: List[Int] = List(1, 4, 9)
  
}