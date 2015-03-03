package byexample

object ClassesAndObjects extends App {
  
  trait IntSet {
  	def incl(x: Int): IntSet
    def excl(x: Int): IntSet
    def union(s: IntSet): IntSet
    def intersection(s: IntSet): IntSet
  	def contains(x: Int): Boolean
  }
  
  object EmptySet extends IntSet {
    def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)
    def excl(x: Int): IntSet = this
    def union(s: IntSet): IntSet = s
    def intersection(s: IntSet): IntSet = this
    def contains(x: Int): Boolean = false
  }
  
  class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    
    def incl(x: Int): IntSet =
      if (x < elem) new NonEmptySet(elem, left incl x, right)
      else if (x > elem) new NonEmptySet(elem, left, right incl x)
      else this
    
    def excl(x: Int): IntSet =
      if (!contains(x)) this
      else {
        if (x < elem) left excl x union right
        else if (x > elem) left union (right excl x)
        else this
      }
    
    def union(s: IntSet): IntSet = s incl elem union left union right
    
    def intersection(s: IntSet): IntSet = {
      var result: IntSet = EmptySet
      if (s.contains(elem)) result = result incl elem
      result union (s intersection left) union (s intersection right)
    }

    def contains(x: Int): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true

  }
  
}