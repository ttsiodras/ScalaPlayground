object ScalaBenchmark {

  def main(args: Array[String]) {
    val benchmarks = List(
      ("imperative",             () => imperative.funcs.walk(1000, 1000)             ) ,
      ("imperative_with_Q",      () => imperative_with_Q.funcs.walk(1000, 1000)      ) ,
      ("antFunctional",          () => functional.funcs.walk(1000,1000)              ) ,
      ("functional_with_Seq",    () => functional_with_Seq.funcs.walk(1000, 1000)    ) ,
      ("flatMap_SeqTailRecurse", () => flatMap_SeqTailRecurse.funcs.walk(1000, 1000) ) )

    val maxLabelLength = benchmarks.map(_._1.size).max + 5
    benchmarks.foreach({ case (label, codeFunc) => {
      val paddedLabel = "="*(maxLabelLength - label.size) + "> " + label
      common.utils.timeit(repeat = 20, assertResult = Some(148848), label=paddedLabel)(codeFunc)
    }})
  }
}
