
As part of my Scala learning, I ported some code I wrote back in 2011, in OCaml and F#.
It's about a puzzle - which in fact caused some issues with F# (here's my [original 
question on StackOverflow](http://stackoverflow.com/questions/7538584/f-vs-ocaml-stack-overflow) :

> There is an ant which can walk around on a planar grid. The ant can move one
> space at a time left, right, up or down. That is, from cell (x, y) the ant
> can go to cells (x+1, y), (x-1, y), (x, y+1), and (x, y-1). Points where the
> sum of the digits of the x and y coordinates are greater than 25 are
> inaccessible to the ant. For example, the point (59,79) is inaccessible
> because 5 + 9 + 7 + 9 = 30, which is greater than 25. The question is: How
> many points can the ant access if it starts at (1000, 1000), including (1000,
> 1000) itself?

The results - with the code using identical concepts in the three languages:

    Benchmarking OCaml...
    
    Statistics :
      Average value: 0.13900
      Std deviation: 0.00539
      Sample stddev: 0.00568
             Median: 0.14000
                Min: 0.13000
                Max: 0.15000
       Overall: 0.139 +/- 4.1%
    make[1]: Leaving directory `/home/ttsiod/GitHub/ScalaPlayground/usingPackagesForBenchmarking/contrib/OCaml'
    make -C "contrib/F#/" bench
    make[1]: Entering directory `/home/ttsiod/GitHub/ScalaPlayground/usingPackagesForBenchmarking/contrib/F#'
    
    Benchmarking F#...
    
    Statistics :
      Average value: 309.67505
      Std deviation: 37.64793
      Sample stddev: 39.68441
             Median: 298.81170
                Min: 266.60990
                Max: 410.06820
       Overall: 309.67505 +/- 12.8%
    make[1]: Leaving directory `/home/ttsiod/GitHub/ScalaPlayground/usingPackagesForBenchmarking/contrib/F#'
    make benchScala
    make[1]: Entering directory `/home/ttsiod/GitHub/ScalaPlayground/usingPackagesForBenchmarking'
    
    Benchmarking Scala...
    
    target/pack/bin/scala-benchmark
    Picked up _JAVA_OPTIONS: -Xmx1g
    Benchmarking ==============> antFunctional, Best time was 137 ms.

Amazingly, Scala is just as fast as OCaml, leaving F# more than 2x behind.
Nice work, HotSpot :-)

I then added 4 other implementations sent to me by kind people on [StackOverflow](http://stackoverflow.com/questions/27291969/mapping-my-code-from-ocaml-f-to-scala-some-questions). Here's their score:

    Benchmarking =================> imperative, Best time was 111 ms.
    Benchmarking ==========> imperative_with_Q, Best time was 114 ms.
    Benchmarking ========> functional_with_Seq, Best time was 148 ms.
    Benchmarking =====> flatMap_SeqTailRecurse, Best time was 204 ms.

