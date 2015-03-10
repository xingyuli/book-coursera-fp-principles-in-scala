package byexample.lazy_values

object LazyValues {
  
  object Db {
    val table = Map(1 -> (1, "Haruki Murakami", -1),
                    2 -> (2, "Milan Kundera", 1),
                    3 -> (3, "Jeffrey Eugenides", 1),
                    4 -> (4, "Mario Vargas Llosa", 1),
                    5 -> (5, "Julian Barnes", 2))
    
    def team(id: Int) = {
      for (rec <- table.values.toList; if rec._3 == id)
        yield recToEmployee(rec)
    }
	  
    def get(id: Int) = recToEmployee(table(id))
	  
	  private def recToEmployee(rec: (Int, String, Int)) = {
	    println("[db] fetching " + rec._1)
	    Employee(rec._1, rec._2, rec._3)
	  }
	  
  }
  
  case class Employee(id: Int, name: String, managerId: Int) {
    lazy val manager: Employee = Db.get(managerId)
    lazy val team: List[Employee] = Db.team(id)
  }
  
  val em = Db.get(2)                              //> [db] fetching 2
                                                  //| em  : byexample.lazy_values.LazyValues.Employee = Employee(2,Milan Kundera,1
                                                  //| )
  em.manager                                      //> [db] fetching 1
                                                  //| res0: byexample.lazy_values.LazyValues.Employee = Employee(1,Haruki Murakami
                                                  //| ,-1)
  em.team                                         //> [db] fetching 5
                                                  //| res1: List[byexample.lazy_values.LazyValues.Employee] = List(Employee(5,Juli
                                                  //| an Barnes,2))
  
}