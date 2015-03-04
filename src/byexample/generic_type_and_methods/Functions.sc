package byexample.generic_type_and_methods

object Functions {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val f: (AnyRef => Int) = x => x.hashCode()      //> f  : AnyRef => Int = <function1>
  val g: (String => Int) = f                      //> g  : String => Int = <function1>
  
  val plus1: (Int => Int) = x => x + 1            //> plus1  : Int => Int = <function1>
  plus1(2)                                        //> res0: Int = 3
  
}