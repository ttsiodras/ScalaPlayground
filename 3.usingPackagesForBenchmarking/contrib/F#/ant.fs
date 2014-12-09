open System.Collections.Generic
 
let rec walk xx yy =
    let visited = new Dictionary<_,_>(HashIdentity.Structural)
 
    let addDigits number =
        let rec sumInner n soFar =
            match n with
            | x when x<10  -> soFar+x
            | x -> sumInner (n/10) (soFar + n % 10) in
        sumInner number 0 in
    let rec innerWalk (totalSoFar,listOfPointsToVisit) =
        match listOfPointsToVisit with
        | [] -> totalSoFar
        | _ ->
            innerWalk (
                listOfPointsToVisit
                (* remove points that we've already seen *)
                |> List.filter (fun (x,y) ->
                    match visited.TryGetValue((x,y)) with
                    | true,_ -> false (* remove *)
                    | _      -> visited.[(x,y)] <- 1 ; true)
                (* increase totalSoFar and add neighbours to list *)
                |> List.fold
                    (fun (sum,newlist) (x,y) ->
                        match (addDigits x)+(addDigits y) with
                        | n when n<26 ->
                            (sum+1,(x+1,y)::(x-1,y)::(x,y+1)::(x,y-1)::newlist)
                        | n -> (sum,newlist))
                    (totalSoFar,[]))
    innerWalk (0,[(xx,yy)])
 
let _ =
    [1..10] |> List.iter (
        fun _ ->
            let stopWatch = System.Diagnostics.Stopwatch.StartNew()
            Printf.printf "Points: %d\n" (walk 1000 1000)
            stopWatch.Stop()
            printfn "%f" stopWatch.Elapsed.TotalMilliseconds
        )
