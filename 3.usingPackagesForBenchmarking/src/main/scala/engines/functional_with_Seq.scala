package functional_with_Seq

import scala.collection.mutable.HashMap
import common.utils.addDigits

object funcs {

  def walk(xx:Int, yy:Int) = {
    val visited = new HashMap[(Int,Int), Int]
    def innerWalk(totalSoFar:Int, listOfPointsToVisit:Seq[(Int,Int)]):Int = {
      if (listOfPointsToVisit.isEmpty) totalSoFar
      else {
        val newStep = listOfPointsToVisit.
          // remove points that we've already seen
          filter(visited.put(_, 1).isEmpty).
          // increase totalSoFar and add neighbours to list
          foldLeft( (totalSoFar,Seq[(Int,Int)]()) )({
            case ((sum,newlist), (x,y)) => {
              if (addDigits(x)+addDigits(y) < 26)
                  (sum+1,(x+1,y)+:(x-1,y)+:(x,y+1)+:(x,y-1)+:newlist)
              else
                  (sum,newlist)
            }
          });
        innerWalk(newStep._1, newStep._2)
      }
    }
    innerWalk(0, Seq((xx,yy)))
  }

}
