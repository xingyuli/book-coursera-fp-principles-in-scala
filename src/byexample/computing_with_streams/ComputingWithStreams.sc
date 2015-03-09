package byexample.computing_with_streams

object ComputingWithStreams {
  
  def isPrime(n: Int): Boolean = {
    def iter(d: Int): Boolean = (d, n % d) match {
	    case (1, _) => true
	    case (_, 0) => false
	    case _ => iter(d-1)
	  }
	  n match {
	  	case 1 => true
	  	case _ => iter(n-1)
	  }
  }                                               //> isPrime: (n: Int)Boolean
  
  def sumPrimes(start: Int, end: Int): Int = {
    var i = start
    var acc = 0
    while (i < end) {
      if (isPrime(i)) acc += i
      i += 1
    }
    acc
  }                                               //> sumPrimes: (start: Int, end: Int)Int
  
  sumPrimes(1, 100)                               //> res0: Int = 1061
  
  
  // def range(start: Int, end: Int): List[Int] = List.range(start, end)
  
  // def range(start: Int, end: Int): Stream[Int] =
  //	if (start >= end) Stream.Empty
  //	else Stream.cons(start, range(start + 1, end))
  
  def range(start: Int, end: Int): Stream[Int] = Stream.range(start, end)
                                                  //> range: (start: Int, end: Int)Stream[Int]
  
  def moreFuntionalSumPrimes(start: Int, end: Int): Int =
    (range(start, end) filter isPrime).sum        //> moreFuntionalSumPrimes: (start: Int, end: Int)Int
  
  moreFuntionalSumPrimes(1, 100)                  //> res1: Int = 1061
  
}