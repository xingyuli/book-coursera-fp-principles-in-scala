package byexample.mutable_state

object MutableState {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  class BankAccount {
    private var balance = 0
    def deposit(amount: Int) {
      if (amount > 0) balance += amount
    }
    def withdraw(amount: Int): Int = {
      if (0 < amount && amount <= balance) {
        balance -= amount
        balance
      } else sys.error("insufficient funds")
    }
  }
  
  val account = new BankAccount                   //> account  : byexample.mutable_state.MutableState.BankAccount = byexample.muta
                                                  //| ble_state.MutableState$$anonfun$main$1$BankAccount$1@3d15d862
  account deposit 50
  account withdraw 20                             //> res0: Int = 30
  account withdraw 20                             //> res1: Int = 10
  account withdraw 15                             //> java.lang.RuntimeException: insufficient funds
                                                  //| 	at scala.sys.package$.error(package.scala:27)
                                                  //| 	at byexample.mutable_state.MutableState$$anonfun$main$1$BankAccount$1.wi
                                                  //| thdraw(byexample.mutable_state.MutableState.scala:15)
                                                  //| 	at byexample.mutable_state.MutableState$$anonfun$main$1.apply$mcV$sp(bye
                                                  //| xample.mutable_state.MutableState.scala:23)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at byexample.mutable_state.MutableState$.main(byexample.mutable_state.Mu
                                                  //| tableState.scala:3)
                                                  //| 	at byexample.mutable_state.MutableState.main(byexample.mutable_state.Mut
                                                  //| ableState.scala)
  
}