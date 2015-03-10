package byexample.iterators

object Iterators {

  import Iterator._
  
  val limit = 10                                  //> limit  : Int = 10
  
  Iterator(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)
    .zip(from(0))
    .filter { case (x, i) => x > limit }
    .map { case (x, i) => i }                     //> res0: Iterator[Int] = non-empty iterator
  
}