package imperative_with_Q

import scala.collection.mutable
import scala.collection.mutable.HashSet
import scala.collection.mutable.ArrayBuffer

object funcs {

  def walk(xx: Int, yy: Int) = {
    val visited = new mutable.HashSet[(Int, Int)]()
    val toVisit = new mutable.Queue[(Int, Int)]()
    def addDigits(n:Int, soFar:Int=0):Int = {
      if (n<10) soFar+n else addDigits(n/10, soFar+n%10)
    }
    def add(x: Int, y: Int) {
      val tupleCoords = (x, y)

      if (visited.add(tupleCoords) && addDigits(x) + addDigits(y) < 26)
        toVisit += tupleCoords
    }
    add(xx, yy)
    var count = 0
    while (!toVisit.isEmpty) {
      count += 1
      val (x, y) = toVisit.dequeue()
      add(x + 1, y)
      add(x - 1, y)
      add(x, y + 1)
      add(x, y - 1)
    }
    count
  }

}
