package byexample.for_comprehensions

object ForComprehensions {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def isPrime(n: Int): Boolean = (true /: List.range(2, n-1)) { (z, i) => z && (n%i != 0) }
                                                  //> isPrime: (n: Int)Boolean
  
  val n = 10                                      //> n  : Int = 10
  for { i <- List.range(1, n)
  		  j <- List.range(1, i)
  	    if (isPrime(i+j)) } yield (i, j)      //> res0: List[(Int, Int)] = List((2,1), (3,2), (4,1), (4,3), (5,2), (6,1), (6,5
                                                  //| ), (7,4), (7,6), (8,3), (8,5), (9,2), (9,4), (9,8))

  case class Book(title: String, authors: List[String])
  
  val books: List[Book] = List(
		Book("Structure and Interpretation of Computer Programs",
			List("Abelson, Harold", "Sussman, Gerald J.")),
		Book("Principles of Compiler Design",
			List("Aho, Alfred", "Ullman, Jeffrey")),
		Book("Programming in Modula2",
			List("Wirth, Niklaus")),
		Book("Introduction to Functional Programming",
			List("Bird, Richard")),
		Book("The Java Language Specification",
			List("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad")))
                                                  //> books  : List[byexample.for_comprehensions.ForComprehensions.Book] = List(Bo
                                                  //| ok(Structure and Interpretation of Computer Programs,List(Abelson, Harold, S
                                                  //| ussman, Gerald J.)), Book(Principles of Compiler Design,List(Aho, Alfred, Ul
                                                  //| lman, Jeffrey)), Book(Programming in Modula2,List(Wirth, Niklaus)), Book(Int
                                                  //| roduction to Functional Programming,List(Bird, Richard)), Book(The Java Lang
                                                  //| uage Specification,List(Gosling, James, Joy, Bill, Steele, Guy, Bracha, Gila
                                                  //| d)))
  
  for (b <- books; a <- b.authors if a startsWith "Ullman") yield b.title
                                                  //> res1: List[String] = List(Principles of Compiler Design)
  
}