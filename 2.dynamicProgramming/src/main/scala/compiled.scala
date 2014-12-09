
import scala.collection.mutable.HashMap

object DynProg {
  def timeit[A](repeat:Int=20, assertResult:Option[A] = None, label:String = "")(f: () => A) = {
    val maxLen = repeat.toString.size
    val minimumTime = List.range(1,repeat+1).map {
      idx => {
        if (idx == 1)
          print(s"Benchmarking $label, loop " + " "*maxLen + "/" + repeat)
        print("\b"*(2*maxLen+1) + ("%" + maxLen + "d").format(idx) + "/" + repeat)
        val startTime = System.currentTimeMillis
        val result = f()
        var totalTime = System.currentTimeMillis - startTime
        assertResult match {
          case Some(value) => {
            try { assert(value == result) }
            catch { case e:AssertionError =>
              println("\nExpected:" + value + ", got:" + result) ; throw e
            }
          }
          case None => ()
        }
        totalTime
      }
    }.min
    println("\b"*(2*maxLen+1) + s"\b\b\b\b\bBest time was $minimumTime ms.")
    minimumTime
  }
  def work() {
    val g_maxSize = 4480
    val fileSizes = List.range(1,100)

    val optimalResults = new HashMap[(Int,Int), Int]()
    val lastStep = new HashMap[(Int,Int), Int]()

    def FetchOrZero(dict:HashMap[(Int,Int),Int], key:(Int,Int)) = {
      dict.getOrElse(key, 0)
    }

    for (containerSize <- List.range(0, g_maxSize+1)) {
      for ((fileSize,idx) <- fileSizes.zipWithIndex) {
        // We will index the "optimalResults" via tuples:
        val cellCurrent = (containerSize, idx)              // The current cell
        val cellOnTheLeftOfCurrent = (containerSize, idx-1) // The cell on our left
        // Does the file on column "idx" fit inside containerSize?
        if (containerSize < fileSize) {
          // it doesn't fit in containerSize
          // so copy optimal value from the cell on its left
          optimalResults(cellCurrent) = FetchOrZero(optimalResults, cellOnTheLeftOfCurrent)
          // Copy last step from cell on the left...
          lastStep(cellCurrent) = FetchOrZero(lastStep,cellOnTheLeftOfCurrent)
        } else {
          // It fits!
          // Using file of column "idx", the remaining space is...
          val remainingSpace = containerSize - fileSize
          // and for that remaining space, the optimal result 
          // using the first idx-1 files has been found to be:
          val optimalResultOfRemainingSpace = FetchOrZero(optimalResults, (remainingSpace,(idx-1)))
          // so let's check: do we improve things by using "idx"?
          if (optimalResultOfRemainingSpace + fileSize > FetchOrZero(optimalResults,cellOnTheLeftOfCurrent)) {
            // we improved the best result, store it!
            optimalResults(cellCurrent) = optimalResultOfRemainingSpace + fileSize
            // Store last step - i.e. using file "idx"
            lastStep(cellCurrent) = fileSize
          } else {
            // no improvement by using "idx" - copy from left...
            optimalResults(cellCurrent) = FetchOrZero(optimalResults, cellOnTheLeftOfCurrent)
            // Copy last step from cell on the left...
            lastStep(cellCurrent) = FetchOrZero(lastStep, cellOnTheLeftOfCurrent)
          }
        }
      }
    }
   
    //println("Attainable: %d".format(FetchOrZero(optimalResults, (g_maxSize,fileSizes.size-1))))
   
    // Navigate backwards... starting from the optimal result:
    val rightMostIndex = fileSizes.size-1
    var total = FetchOrZero(optimalResults, (g_maxSize, rightMostIndex))
    while(total>0) {
      // The last step we used to get to "total" was...
      val lastFileSize = lastStep((total, rightMostIndex))
      //println("%d removing %d".format(total, lastFileSize))
      // And the one before the last step was... (loop)
      total = total - lastFileSize
    }
  }
  def main(args: Array[String]) {
    timeit()(() => work())
  }

}
