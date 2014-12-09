let visited = Hashtbl.create 200000
 
let rec walk xx yy =
    let addDigits number =
        let rec sumInner n soFar =
            match n with
            | x when x<10  -> soFar+x
            | x -> sumInner (n/10) (soFar + n mod 10) in
        sumInner number 0 in
    let rec innerWalk (totalSoFar,listOfPointsToVisit) =
        match listOfPointsToVisit with
        | [] -> totalSoFar
        | _ ->
            innerWalk (
                listOfPointsToVisit
                (* remove points that we've already seen *)
                |> List.filter (fun (x,y) ->
                    match Hashtbl.mem visited (x,y) with
                    | true -> false (* remove *)
                    | _    -> (Hashtbl.add visited (x,y) 1 ; true))
                (* increase totalSoFar and add neighbours to list *)
                |> List.fold_left
                    (fun (sum,newlist) (x,y) ->
                        match (addDigits x)+(addDigits y) with
                        | n when n<26 ->
                            (sum+1,(x+1,y)::(x-1,y)::(x,y+1)::(x,y-1)::newlist)
                        | n -> (sum,newlist))
                    (totalSoFar,[])) in
    innerWalk (0,[(xx,yy)])
 
let _ =
    Printf.printf "Points: %d\n" (walk 1000 1000)
