#!/usr/bin/env scalaRunner

import scala.util.Random
import scala.collection.mutable.TreeSet

def timeit[A](f: => A) = {
  val s = System.currentTimeMillis
  val result = f
  println("Execution took " + (System.currentTimeMillis - s) + " ms.")
  result
}

val data = Random.shuffle(List.range(0,1000000))

def sortList1(l:List[Int]) = l.sorted

def sortList2(l:List[Int]) = {
  val t = TreeSet[Int]()
  t ++= l
  t.toList
}

List.range(1,10).foreach {
  _ => timeit {
    val s = sortList1(data)
  }
}
var s = sortList1(data)
println(s.size + " " + s(0) + "-" + s(s.size-1))


List.range(1,10).foreach {
  _ => timeit {
    val s = sortList2(data)
  }
}
s = sortList2(data)
println(s.size + " " + s(0) + "-" + s(s.size-1))
