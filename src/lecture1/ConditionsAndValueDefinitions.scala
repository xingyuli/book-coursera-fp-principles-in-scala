package lecture1

object ConditionsAndValueDefinitions {

  def and(x: Boolean, y: => Boolean) = if (x) y else false
  
  def or(x: Boolean, y: => Boolean) = if (!x) y else true
  
}