
**TL;DR** A puzzle solver in OCaml,F# and Scala. Just type `make`.

Basically, as part of learning Scala (in my freetime), I ported some code I wrote back in 2011 (from OCaml and F#
to Scala). The code is about a puzzle, and when I initially wrote it, F# couldn't copy - here's my [original 
question on StackOverflow](http://stackoverflow.com/questions/7538584/f-vs-ocaml-stack-overflow) . Later on,
I figured out how to do it *properly*, using tail recursion. So now, 3 years later, let's see how Scala fares 
with it...  

The puzzle is this:

> There is an ant which can walk around on a planar grid. The ant can move one
> space at a time left, right, up or down. That is, from cell (x, y) the ant
> can go to cells (x+1, y), (x-1, y), (x, y+1), and (x, y-1). Points where the
> sum of the digits of the x and y coordinates are greater than 25 are
> inaccessible to the ant. For example, the point (59,79) is inaccessible
> because 5 + 9 + 7 + 9 = 30, which is greater than 25. The question is: How
> many points can the ant access if it starts at (1000, 1000), including (1000,
> 1000) itself?

The results of all my implementations - with the code using identical concepts in all three languages:

    bash$ make
    ...

    Benchmarking OCaml...
    
    Statistics :
      Average value: 0.13900
      Std deviation: 0.00539
      Sample stddev: 0.00568
             Median: 0.14000
                Min: 0.13000
                Max: 0.15000
       Overall: 0.139 +/- 4.1%

    Benchmarking F#...
    
    Statistics :
      Average value: 309.67505
      Std deviation: 37.64793
      Sample stddev: 39.68441
             Median: 298.81170
                Min: 266.60990
                Max: 410.06820
       Overall: 309.67505 +/- 12.8%

    Benchmarking Scala...
    
    Benchmarking ==============> antFunctional, Best time was 137 ms.

Looking at the min times (when benchmarking CPU bound algorithms, that's what you 
should do), it is clear that amazingly, Scala is just as fast as OCaml - leaving F# more than 2x behind.

Nice work, JVM HotSpot.

I then added 4 other implementations sent to me by kind people on [StackOverflow](http://stackoverflow.com/questions/27291969/mapping-my-code-from-ocaml-f-to-scala-some-questions). Here's their score:

    Benchmarking =================> imperative, Best time was 111 ms.
    Benchmarking ==========> imperative_with_Q, Best time was 114 ms.
    Benchmarking ========> functional_with_Seq, Best time was 148 ms.
    Benchmarking =====> flatMap_SeqTailRecurse, Best time was 204 ms.

Scala is already very appealing - it has the nice, strong and static type system of the ML-world,
but it doesn't live in an island like Haskell and OCaml - it has the huge ecosystem of the JVM
(hello, enterprise overlords). F# claims the same, but from my benchmarks it seems to be slower.
