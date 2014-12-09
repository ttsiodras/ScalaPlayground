package flatMap_SeqTailRecurse

import scala.collection.mutable.HashSet

object funcs {
  def walk(xx: Int, yy: Int) = {
    val visited = new HashSet[(Int, Int)]
    def addDigits(n:Int, soFar:Int=0):Int = {
      if (n<10) soFar+n else addDigits(n/10, soFar+n%10)
    }
    var count = 0
    def innerWalk(listOfPointsToVisit: Seq[(Int, Int)]): Int = {
      if (listOfPointsToVisit.isEmpty) count
      else {
        innerWalk(listOfPointsToVisit.flatMap {
          case tupleCoords@(x, y) => {
            if (visited.add(tupleCoords) && addDigits(x) + addDigits(y) < 26) {
              count += 1
              Seq((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1))
            }
            else
              Seq()
          }
        })
      }
    }
    innerWalk(Seq((xx, yy)))
  }
}
