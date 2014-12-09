package imperative

import scala.collection.mutable.HashSet
import scala.collection.mutable.ArrayBuffer
import common.utils.addDigits

object funcs {

  def walk(xx: Int, yy: Int) = {
    val visited = new HashSet[(Int, Int)]()
    val toVisit = new ArrayBuffer[(Int, Int)]()
    def addDigits(n:Int, soFar:Int=0):Int = {
      if (n<10) soFar+n else addDigits(n/10, soFar+n%10)
    }
    def add(x: Int, y: Int) = {
      val tupleCoords = (x, y)

      if (visited.add(tupleCoords) && addDigits(x) + addDigits(y) < 26) {
        toVisit += tupleCoords
      }
    }
    add(xx, yy)
    var i = 0
    while (i < toVisit.size) {
      val (x, y) = toVisit(i)
      add(x + 1, y)
      add(x - 1, y)
      add(x, y + 1)
      add(x, y - 1)
      i += 1
    }
    toVisit.size
  }

}
