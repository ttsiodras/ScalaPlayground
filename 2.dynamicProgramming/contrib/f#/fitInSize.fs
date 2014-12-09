
open System.IO
open System.Collections.Generic

let work () =
    let g_maxSize = 4480
    let fileSizes = [ 1 .. 99 ]

    let optimalResults = new Dictionary<_,_>(HashIdentity.Structural)
    let lastStep = new Dictionary<_,_>()

    let FetchOrZero (dict:Dictionary<_,_>) k = 
        match (dict.TryGetValue k) with
        | true, v -> v
        | _       -> 0

    for containerSize in 0 .. g_maxSize do
        for (idx,fileSize) in List.mapi (fun i x -> (i,x)) fileSizes do
            // We will index the "optimalResults" via tuples:
            let cellCurrent = (containerSize, idx)              // The current cell
            let cellOnTheLeftOfCurrent = (containerSize, idx-1) // The cell on our left
            // Does the file on column "idx" fit inside containerSize?
            if containerSize<fileSize then
                // it doesn't fit in containerSize
                // so copy optimal value from the cell on its left
                optimalResults.[cellCurrent] <- FetchOrZero optimalResults cellOnTheLeftOfCurrent
                // Copy last step from cell on the left...
                lastStep.[cellCurrent] <- FetchOrZero lastStep cellOnTheLeftOfCurrent
            else
                // It fits!
                // Using file of column "idx", the remaining space is...
                let remainingSpace = containerSize - fileSize
                // and for that remaining space, the optimal result 
                // using the first idx-1 files has been found to be:
                let optimalResultOfRemainingSpace = FetchOrZero optimalResults (remainingSpace,(idx-1))
                // so let's check: do we improve things by using "idx"?
                if optimalResultOfRemainingSpace + fileSize > FetchOrZero optimalResults cellOnTheLeftOfCurrent then
                    // we improved the best result, store it!
                    optimalResults.[cellCurrent] <- optimalResultOfRemainingSpace + fileSize
                    // Store last step - i.e. using file "idx"
                    lastStep.[cellCurrent] <- fileSize
                else
                    // no improvement by using "idx" - copy from left...
                    optimalResults.[cellCurrent] <- FetchOrZero optimalResults cellOnTheLeftOfCurrent
                    // Copy last step from cell on the left...
                    lastStep.[cellCurrent] <- FetchOrZero lastStep cellOnTheLeftOfCurrent
   

    // printfn "Attainable: %d" (FetchOrZero optimalResults (g_maxSize,fileSizes.Length-1))
   
    // Navigate backwards... starting from the optimal result:
    let rightMostIndex = fileSizes.Length-1
    let mutable total = FetchOrZero optimalResults (g_maxSize, rightMostIndex)
    while total>0 do
       // The last step we used to get to "total" was...
       let lastFileSize = lastStep.[(total, rightMostIndex)]
       // printfn "%d removing %d" total lastFileSize
       // And the one before the last step was... (loop)
       total <- total - lastFileSize
 
[<EntryPoint>]
let main (args : string[]) =
    [1 .. 10] |> List.iter(
        fun _ -> 
            let stopWatch = System.Diagnostics.Stopwatch.StartNew()
            work ()
            stopWatch.Stop()
            printfn "%f" stopWatch.Elapsed.TotalMilliseconds
    )
    0
