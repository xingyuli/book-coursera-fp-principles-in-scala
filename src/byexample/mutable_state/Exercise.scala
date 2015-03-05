package byexample.mutable_state

import scala.annotation.tailrec

object Exercise extends App {

  // Exercise 11.2.1
  def repeatLoop(command: => Unit)(condition: => Boolean) {
    command
    if (condition) {
      repeatLoop(command)(condition)     
    }
  }

  class Loop(command: => Unit) {
    @tailrec final def until(condition: => Boolean) {
      command
      if (!condition) {
        until(condition)
      }
    }
  }
  
  def repeatLoop2(command: => Unit) = new Loop(command)
  
  var i = 0
  repeatLoop {
    println("i: " + i)
    i += 1
  } (i < 10)
  
  var j = 0
  repeatLoop2 {
    println("j: " + j)
    j += 2
  } until (j == 10)
  
}