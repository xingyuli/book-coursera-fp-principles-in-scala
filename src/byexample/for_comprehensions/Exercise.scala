package byexample.for_comprehensions

object Exercise extends App {
  
  // Exercise 10.3.1
  def flatten[A](xss: List[List[A]]): List[A] = for (xs <- xss; x <- xs) yield x

  println(flatten(List(
      List(1, 4, 7),
      List(2, 5, 8),
      List(3, 6, 9))))
  
  
  // Exercise 10.3.2
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
  
  println(books.flatMap(b =>
    b.authors
      .filter(_ startsWith "Bird")
      .map(a => b.title)))

  println(books.filter(b => (b.title indexOf "Program") >= 0).map(_.title))
  
}