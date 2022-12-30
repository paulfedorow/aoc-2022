(ns aoc.day24
  (:require [clojure.string :as str])
  (:import (clojure.lang PersistentQueue)))

(def input "#.########################################################################################################################\n#<^vvv<v^v>^vvv.v<vv<>^^<^><.vvv^>v<^<<<<^>^vv^>^<^>>v>v^v^>^v>vv>^.^..v<^<^^^>>>><<>v<^^^v<>>><>.vv^vv>v.vv<<^<.v...^v.<#\n#<^^><><^^^<^>>v^^>v<^vvvv><vv<v.<vv^v<>v^.v^>v^v<>^<>^<vv.v^.^<<<^^^..<.><<v<vv^<v^^>>.><v<vv.>v>><<v.v^<^<v^^^^vv^v>^v>#\n#<^.>>v<<vv^^<<<<<.<v<>^>v^v<v<<<^<v^<<vvvv>v<^v<^v<>v.<>v^^^.^vv.<vv>v<^^v^>>.><^.><<<>v^>><^.<><>v^vv>>^v>>.><vvv^<<>v<#\n#>v^v<>^<.^.^>><>>..<vv<>^>>vv<.<.<^v<^<>>><<^v.v^<v<vv><v.<v.>v^v^<.v>^v^<^.v>.v.>^<v^>vv><<v.>^>v><<^vv<v^>v>v>.>v>>><.#\n#><<<^<v.><>>^>v<^><^<^v.^v<vv<<^^vvv><^^>>>>v^.^^v^<v^^.vv^v^<.v<v^^v<^..>>v<^v^.v^^^^.>v^<vv^<<>><<>vv><^<<^^><..vv.v^.#\n#<^>^.^v.><^>.v<v<>^<^<<<<<^^><vv.>^v><^<.>>.><^<v>>>^^^vvv<>v^^^<.^v^<^v<v>^.^.<>v^.v>v<v^<^^vv.^<><>v^>>.^>>><>><>v..^<#\n#>.^>^>^.v<vv.<>.>>v<>><^vv>>^<<v^.><^^>^^^.<><><.<v<<^.^<><<^>v<v<v<v>^^<^<v<<...<<>v<v<>.^v>>^^^^<<<><v<^>.^v^.>v<v>v.>#\n#<.>.^.<>^^<^v<<^^^^v..<><>^vvv<^>..v<>^vvvv<<<>v^<v.v^<<>v.<^v^>^>>vv<.<<.<^^^v^v<vv<.>^<^.v.<<<v.v^v>v^^<vv^^^>v>v^>><>#\n#<v>v>^<vvv^v<^>^v>.<<^v^^>v>^v>^.<<v<vv<<v^<v<^vvv.<>.<>^vv.>^vvv<v<><v^.<^v<..><<><^v..v>^>v<>>.<<>^<^v>v.^><<^v<<.v^>>#\n#<<<^<<^v^vv><><<>vv<>...<^<<<vv^^^...<><<<^vv^<vv<^<v>^<^^^^>v^>>.<^<^<>>v^><><v^v>>><v>^^<^.>><><^<v<^.>>v^>^v^v<v^^^<<#\n#<>.><^v.vv><.v<v>^.>^><<^<><vv>^v<^>v<^.v^.v<>^.>^>vv>^^>>v<^><^v.>v^>^vvv^^<v<>..v^^<>..v^>v^^^<<>vv^vv>v><<v<.>><<vvv>#\n#><><.^^<<<<.<<><v^<v>^>^>vv..v>^v<<v^<^^.<<>^>>.<>>^<<v.^>^^vv>^^v.^<<.>>><v^>.>>^.>>^<^v>^<>v<v.v<.<^.v>^<>v^v>.^.><^v>#\n#><>>>>^<>>>v^v^v^vv.<^^.^.^>^v>>v<<^..^>><<.^<v><>v>>><<>vv.<>v.^<^^<v^vv^>v<>v><^^vv>vvv.v><v.<<<<>>>vv>v.>v>>^^v>.^<><#\n#<<><><^>^v<>v^.^<.^<v^<v.>.>v^v.<^><<vv<vvv^<>^>.v>>v^><><<<v<.^v>><vv<^v<>^v>v.^<^.><.^<.v^v<^^><>^.^>v>.>^<..<<^>^vv..#\n#><v>v.v<>>v<<><>>^<>vv^<<>^vvv^<>^>>v><^<v.<^<<<^><<<<<^.^v><><<><><v.vv^^v.<<<<>.<<>v^.<^.v<v>>v^<..^><v<<^^<^>^^v^>.<>#\n#.<<>>>v.<>^<<^v.^<>v<><vv>.^v.^>.<v.>v<.^vvv>v^<<v<><^<v^.>vv^.<<^v^v>>vvv>.>>^>^<<<v.>>.vv>>>^v^vv^v<v<<^>>><.<>>^>v^<<#\n#<<>><><>>^v>^>.<v^v..v.v^v^>.><^.><^<>^.>>v.^>vv^v><^..<>^vv<>.<<.<>v^^..>^<>v><<^>^v><<v>..>vvv<.>>v<v<vv<^^v>^v<v^v<v.#\n#>vvv.><<<^<>>><<<v><>>>>^v<<.<><vvv.<<^^v.v<>^<>^v.^^>.<v>v>v^v^^<^.v^>^><v>^>>^<>>vvvv<.vv^v<^v^^^.^>^><><^vvv.>.v^<<v>#\n#>vv.><>>^.^v<.>vv>>.^v^vv>^v^>>><v<<vv.><^><>.><>>><^<>v^v<>^<<<^>^>^<.><.v>><v<>v>>><vv^.>^v.>v^<>v>v^vv^<v<<^v^v<<^>^>#\n#<v^vvv<><<<vv.<^v<<v^^>v^<vvv^^vvv^^>v<.<>v^>>>>^>.^><^>^^<^<^.v>>>v>^<<<v>>.v^v<v><^>>v.<^>^v^vv>>^^vv<<v<v<<vv^<^<.vv>#\n#<^>v^^^^vv><>>^>v>^>v<vv<><v.v<^vv><v<^^^^^.<<><v<><^.>^^.<^v>^<v>^<<.v>>^v^^.^<.<v>.^><.>^v^^v<><v<.v<.>>><^>^vv<.^.>><#\n#<>^^vvv<<v^vv<<>v>.^<>v^>.vv<vv^>.<^>v>v^>.^v<.<>^<vv^v><^v.^v>^^>v^>>><>vv<>vv>.^v><>.<^^v<<<^>^^^><>^^>^.^>vv<.<><<^>>#\n#<<^^..>v>vv<v<>>v<><^<v<v^>.<>v>^^>^v<.v^.<>>.<<>>v^><<^^<>^.<v^vvv^v^<<<^v.^vv^^^<v><<<v^<<>^.<><vvv^<>v<^>.>>><>^v<>v<#\n#<>>^.>^^^vv<>^>^<v.^^v><.v<^<vv>^^v><<>v<>>>>>.<^^vv^^^>.<<>v^>>.^.<v^>.>.v^vv>><>.><<^^>>v^>>>^>v<>.^^v>^>vvv^^^^.^^^.<#\n#>v<vvv<^^>v^>>^^<vvvv.v.v>^<v^>>v><><<v^.<><v<^>v.<^v<>>^v<.^><^v>^^>v^<v^v>>^vv><v.^<><<v<<<v^v>vv><<^<>v^<<^...<<.<>^<#\n########################################################################################################################.#\n")

(defn parse-input [input]
  (let [board (vec (for [row (rest (drop-last (str/split-lines input)))]
                     (subs row 1 (dec (count row)))))]
    {:board  board
     :height (count board)
     :width  (-> board first count)}))

(defn pos-end [state]
  [(:height state) (dec (:width state))])

(def pos-start [-1 0])

(defn free? [state [row col] step]
  (let [{board :board, height :height, width :width} state]
    (or (= [row col] (pos-end state))
        (= [row col] pos-start)
        (and (<= 0 row (dec height))
             (<= 0 col (dec width))
             (not= (get-in board [(mod (- row step) height) col]) \v)
             (not= (get-in board [row (mod (- col step) width)]) \>)
             (not= (get-in board [(mod (+ row step) height) col]) \^)
             (not= (get-in board [row (mod (+ col step) width)]) \<)))))

(defn steps-to [state pos-start pos-end step-offset]
  (loop [candidates #{pos-start}, step step-offset]
    (if (seq candidates)
      (let [next-candidates (->> (mapcat (fn [[row col]]
                                           (map (fn [[drow dcol]]
                                                  [(+ row drow) (+ col dcol)])
                                                [[0 0] [-1 0] [0 1] [1 0] [0 -1]])) candidates)
                                 (filter #(free? state % (inc step)))
                                 (into #{}))]
        (if (contains? next-candidates pos-end)
          (inc step)
          (recur next-candidates (inc step))))
      nil)))

(defn part1 []
  (let [state (parse-input input)]
    (steps-to state pos-start (pos-end state) 0)))

(defn part2 []
  (let [state (parse-input input)]
    (->> (steps-to state pos-start (pos-end state) 0)
         (steps-to state (pos-end state) pos-start)
         (steps-to state pos-start (pos-end state)))))