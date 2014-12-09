package common

object utils {
  def addDigits(n:Int, soFar:Int=0):Int =
    if (n<10) soFar+n else addDigits(n/10, soFar+n%10)

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
}
