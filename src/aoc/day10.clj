(ns aoc.day10
  [:require [clojure.string :as str]])

(def input "noop\nnoop\naddx 5\naddx 3\nnoop\naddx 14\naddx -12\nnoop\naddx 5\naddx 1\nnoop\naddx 19\naddx -15\nnoop\nnoop\nnoop\naddx 7\naddx -1\naddx 4\nnoop\nnoop\naddx 5\naddx 1\naddx -38\nnoop\naddx 21\naddx -18\naddx 2\naddx 2\nnoop\naddx 3\naddx 5\naddx -6\naddx 11\nnoop\naddx 2\naddx 19\naddx -18\nnoop\naddx 8\naddx -3\naddx 2\naddx 5\naddx 2\naddx 3\naddx -2\naddx -38\nnoop\naddx 3\naddx 4\naddx 5\nnoop\naddx -2\naddx 5\naddx -8\naddx 12\naddx 3\naddx -2\naddx 5\naddx 11\naddx -31\naddx 23\naddx 4\nnoop\nnoop\naddx 5\naddx 3\naddx -2\naddx -37\naddx 1\naddx 5\naddx 2\naddx 12\naddx -10\naddx 3\naddx 4\naddx -2\nnoop\naddx 6\naddx 1\nnoop\nnoop\nnoop\naddx -2\naddx 7\naddx 2\nnoop\naddx 3\naddx 3\naddx 1\nnoop\naddx -37\naddx 2\naddx 5\naddx 2\naddx 32\naddx -31\naddx 5\naddx 2\naddx 9\naddx 9\naddx -15\nnoop\naddx 3\naddx 2\naddx 5\naddx 2\naddx 3\naddx -2\naddx 2\naddx 2\naddx -37\naddx 5\naddx -2\naddx 2\naddx 5\naddx 2\naddx 16\naddx -15\naddx 4\nnoop\naddx 1\naddx 2\nnoop\naddx 3\naddx 5\naddx -1\naddx 5\nnoop\nnoop\nnoop\nnoop\naddx 3\naddx 5\naddx -16\nnoop\n")

(defn parse-input [input]
  (->> (str/split-lines input)
       (map #(str/split % #" "))
       (map (fn [[op & rest]]
              (case op
                "addx" {:op :addx, :val (parse-long (first rest))}
                "noop" {:op :noop})))))

(defn reg-vals [ops]
  (->> (mapcat (fn [op]
                 (case (:op op)
                   :addx [0 (:val op)]
                   :noop [0]))
               ops)
       (reductions + 1)
       (map-indexed #(vec [(inc %1) %2]))))

(defn part1 []
  (->> (parse-input input)
       (reg-vals)
       (drop 19)
       (take-nth 40)
       (map #(apply * %))
       (reduce +)))

(defn sprite-positions [val]
  (range val (+ val 3)))

(defn part2 []
  (->> (for [reg-vals (partition 40 (reg-vals (parse-input input)))]
         (for [[cycle val] reg-vals
               :let [sprite-positions (set (sprite-positions val))]]
           (if (contains? sprite-positions (mod cycle 40)) \# \.)))
       (map #(str/join "" %))
       (str/join "\n")))