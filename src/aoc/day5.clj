(ns aoc.day5
  [:require [clojure.string :as str]])

(def input "        [F] [Q]         [Q]        \n[B]     [Q] [V] [D]     [S]        \n[S] [P] [T] [R] [M]     [D]        \n[J] [V] [W] [M] [F]     [J]     [J]\n[Z] [G] [S] [W] [N] [D] [R]     [T]\n[V] [M] [B] [G] [S] [C] [T] [V] [S]\n[D] [S] [L] [J] [L] [G] [G] [F] [R]\n[G] [Z] [C] [H] [C] [R] [H] [P] [D]\n 1   2   3   4   5   6   7   8   9 \n\nmove 3 from 5 to 2\nmove 3 from 8 to 4\nmove 7 from 7 to 3\nmove 14 from 3 to 9\nmove 8 from 4 to 1\nmove 1 from 7 to 5\nmove 2 from 6 to 4\nmove 4 from 5 to 7\nmove 1 from 3 to 6\nmove 3 from 4 to 3\nmove 1 from 4 to 1\nmove 5 from 1 to 9\nmove 1 from 4 to 6\nmove 4 from 7 to 4\nmove 15 from 9 to 2\nmove 7 from 1 to 6\nmove 3 from 3 to 5\nmove 1 from 4 to 9\nmove 2 from 5 to 3\nmove 2 from 4 to 9\nmove 4 from 1 to 6\nmove 1 from 3 to 1\nmove 1 from 3 to 2\nmove 4 from 6 to 3\nmove 24 from 2 to 8\nmove 4 from 9 to 8\nmove 1 from 1 to 3\nmove 2 from 5 to 4\nmove 1 from 2 to 4\nmove 19 from 8 to 1\nmove 5 from 3 to 9\nmove 8 from 1 to 3\nmove 3 from 4 to 1\nmove 6 from 9 to 5\nmove 2 from 3 to 4\nmove 1 from 8 to 5\nmove 2 from 4 to 6\nmove 11 from 6 to 1\nmove 8 from 8 to 7\nmove 1 from 6 to 5\nmove 13 from 1 to 3\nmove 1 from 1 to 7\nmove 2 from 7 to 8\nmove 5 from 7 to 1\nmove 2 from 8 to 4\nmove 3 from 5 to 3\nmove 11 from 3 to 1\nmove 2 from 5 to 3\nmove 2 from 5 to 3\nmove 2 from 7 to 1\nmove 7 from 3 to 1\nmove 1 from 4 to 5\nmove 1 from 6 to 4\nmove 3 from 4 to 7\nmove 3 from 7 to 1\nmove 6 from 3 to 5\nmove 1 from 5 to 9\nmove 4 from 5 to 4\nmove 2 from 3 to 4\nmove 8 from 9 to 2\nmove 5 from 4 to 6\nmove 1 from 6 to 5\nmove 1 from 4 to 9\nmove 39 from 1 to 7\nmove 7 from 2 to 6\nmove 1 from 9 to 3\nmove 1 from 2 to 7\nmove 1 from 3 to 1\nmove 5 from 7 to 3\nmove 4 from 5 to 1\nmove 19 from 7 to 9\nmove 1 from 9 to 8\nmove 1 from 9 to 7\nmove 5 from 9 to 3\nmove 6 from 6 to 7\nmove 1 from 8 to 3\nmove 4 from 1 to 4\nmove 23 from 7 to 6\nmove 1 from 1 to 6\nmove 21 from 6 to 2\nmove 3 from 4 to 8\nmove 7 from 6 to 1\nmove 1 from 4 to 9\nmove 1 from 6 to 7\nmove 6 from 1 to 2\nmove 1 from 7 to 4\nmove 15 from 2 to 8\nmove 5 from 3 to 8\nmove 22 from 8 to 7\nmove 1 from 8 to 1\nmove 5 from 3 to 4\nmove 1 from 3 to 2\nmove 1 from 1 to 2\nmove 3 from 4 to 8\nmove 3 from 8 to 9\nmove 11 from 2 to 1\nmove 2 from 1 to 4\nmove 15 from 9 to 5\nmove 22 from 7 to 3\nmove 2 from 4 to 9\nmove 3 from 4 to 2\nmove 8 from 1 to 8\nmove 6 from 8 to 6\nmove 1 from 6 to 2\nmove 3 from 6 to 9\nmove 3 from 2 to 7\nmove 4 from 2 to 9\nmove 2 from 7 to 5\nmove 1 from 1 to 7\nmove 2 from 8 to 2\nmove 2 from 7 to 5\nmove 9 from 5 to 3\nmove 8 from 5 to 2\nmove 1 from 6 to 4\nmove 1 from 6 to 9\nmove 1 from 2 to 9\nmove 2 from 5 to 1\nmove 7 from 2 to 3\nmove 1 from 4 to 3\nmove 1 from 2 to 4\nmove 5 from 3 to 4\nmove 6 from 9 to 3\nmove 1 from 2 to 6\nmove 6 from 9 to 6\nmove 2 from 1 to 8\nmove 3 from 6 to 3\nmove 2 from 8 to 6\nmove 6 from 4 to 1\nmove 14 from 3 to 9\nmove 1 from 6 to 4\nmove 3 from 3 to 9\nmove 1 from 4 to 5\nmove 10 from 9 to 6\nmove 6 from 6 to 7\nmove 2 from 1 to 8\nmove 1 from 8 to 6\nmove 16 from 3 to 2\nmove 1 from 8 to 1\nmove 1 from 7 to 1\nmove 7 from 3 to 4\nmove 1 from 6 to 5\nmove 4 from 2 to 3\nmove 5 from 4 to 9\nmove 2 from 4 to 5\nmove 4 from 7 to 4\nmove 5 from 9 to 6\nmove 2 from 5 to 4\nmove 11 from 6 to 7\nmove 1 from 6 to 8\nmove 5 from 1 to 5\nmove 2 from 6 to 4\nmove 7 from 7 to 3\nmove 1 from 8 to 6\nmove 2 from 7 to 3\nmove 1 from 1 to 3\nmove 3 from 2 to 8\nmove 9 from 2 to 5\nmove 1 from 6 to 1\nmove 1 from 4 to 8\nmove 7 from 4 to 7\nmove 8 from 5 to 6\nmove 1 from 7 to 2\nmove 1 from 7 to 4\nmove 3 from 7 to 8\nmove 1 from 2 to 3\nmove 1 from 1 to 2\nmove 1 from 1 to 7\nmove 3 from 7 to 6\nmove 11 from 6 to 2\nmove 4 from 8 to 7\nmove 2 from 8 to 7\nmove 15 from 3 to 2\nmove 7 from 9 to 4\nmove 3 from 3 to 2\nmove 4 from 4 to 7\nmove 5 from 7 to 3\nmove 3 from 4 to 6\nmove 3 from 6 to 9\nmove 1 from 4 to 2\nmove 1 from 8 to 1\nmove 2 from 3 to 7\nmove 2 from 3 to 7\nmove 23 from 2 to 5\nmove 1 from 9 to 1\nmove 1 from 7 to 9\nmove 1 from 1 to 8\nmove 8 from 7 to 1\nmove 1 from 8 to 4\nmove 1 from 4 to 2\nmove 3 from 9 to 8\nmove 1 from 7 to 9\nmove 22 from 5 to 9\nmove 1 from 8 to 5\nmove 1 from 7 to 4\nmove 1 from 4 to 5\nmove 1 from 8 to 3\nmove 2 from 9 to 3\nmove 5 from 5 to 2\nmove 5 from 5 to 4\nmove 3 from 2 to 7\nmove 1 from 7 to 3\nmove 6 from 1 to 7\nmove 4 from 3 to 1\nmove 6 from 2 to 8\nmove 1 from 5 to 6\nmove 2 from 8 to 1\nmove 12 from 9 to 4\nmove 8 from 9 to 4\nmove 1 from 2 to 9\nmove 2 from 9 to 8\nmove 3 from 2 to 8\nmove 5 from 8 to 6\nmove 7 from 7 to 1\nmove 4 from 8 to 9\nmove 1 from 6 to 1\nmove 17 from 4 to 7\nmove 1 from 2 to 4\nmove 2 from 4 to 1\nmove 6 from 4 to 6\nmove 1 from 1 to 4\nmove 7 from 1 to 5\nmove 9 from 7 to 9\nmove 8 from 9 to 8\nmove 5 from 8 to 3\nmove 1 from 5 to 6\nmove 2 from 3 to 6\nmove 1 from 9 to 1\nmove 1 from 6 to 1\nmove 10 from 6 to 1\nmove 1 from 5 to 1\nmove 2 from 9 to 1\nmove 1 from 9 to 7\nmove 2 from 6 to 8\nmove 2 from 8 to 2\nmove 1 from 6 to 8\nmove 22 from 1 to 9\nmove 9 from 7 to 5\nmove 1 from 8 to 1\nmove 2 from 8 to 3\nmove 4 from 5 to 9\nmove 1 from 8 to 3\nmove 5 from 1 to 9\nmove 2 from 7 to 3\nmove 2 from 4 to 7\nmove 1 from 8 to 5\nmove 2 from 2 to 4\nmove 1 from 5 to 8\nmove 9 from 5 to 8\nmove 2 from 7 to 5\nmove 2 from 4 to 5\nmove 3 from 8 to 4\nmove 3 from 4 to 3\nmove 2 from 8 to 6\nmove 1 from 6 to 4\nmove 3 from 5 to 9\nmove 1 from 6 to 3\nmove 12 from 3 to 5\nmove 1 from 3 to 1\nmove 7 from 5 to 4\nmove 1 from 1 to 3\nmove 1 from 8 to 1\nmove 7 from 5 to 1\nmove 6 from 9 to 6\nmove 29 from 9 to 5\nmove 2 from 4 to 6\nmove 26 from 5 to 2\nmove 24 from 2 to 7\nmove 1 from 3 to 2\nmove 8 from 1 to 7\nmove 7 from 6 to 9\nmove 2 from 5 to 3\nmove 1 from 6 to 4\nmove 3 from 8 to 5\nmove 2 from 3 to 8\nmove 2 from 2 to 8\nmove 5 from 9 to 2\nmove 27 from 7 to 2\nmove 2 from 8 to 3\nmove 2 from 9 to 5\nmove 3 from 8 to 5\nmove 2 from 7 to 4\nmove 3 from 4 to 7\nmove 2 from 3 to 2\nmove 4 from 5 to 1\nmove 5 from 7 to 2\nmove 29 from 2 to 8\nmove 9 from 8 to 3\nmove 2 from 4 to 8\nmove 7 from 3 to 2\nmove 3 from 5 to 4\nmove 1 from 7 to 5\nmove 3 from 5 to 6\nmove 2 from 1 to 8\nmove 2 from 6 to 8\nmove 3 from 4 to 2\nmove 4 from 4 to 2\nmove 1 from 6 to 8\nmove 8 from 2 to 4\nmove 2 from 3 to 5\nmove 1 from 4 to 1\nmove 3 from 1 to 2\nmove 4 from 8 to 2\nmove 3 from 4 to 9\nmove 3 from 4 to 1\nmove 2 from 9 to 5\nmove 1 from 4 to 6\nmove 4 from 5 to 1\nmove 1 from 6 to 8\nmove 1 from 9 to 3\nmove 4 from 2 to 3\nmove 15 from 8 to 2\nmove 9 from 8 to 1\nmove 1 from 3 to 9\nmove 5 from 1 to 9\nmove 3 from 9 to 7\nmove 2 from 7 to 6\nmove 3 from 3 to 2\nmove 1 from 7 to 8\nmove 1 from 9 to 6\nmove 1 from 9 to 8\nmove 2 from 8 to 2\nmove 1 from 1 to 2\nmove 1 from 3 to 7\nmove 4 from 1 to 7\nmove 19 from 2 to 5\nmove 1 from 1 to 4\nmove 1 from 7 to 4\nmove 1 from 1 to 5\nmove 3 from 1 to 4\nmove 1 from 1 to 8\nmove 6 from 2 to 4\nmove 7 from 2 to 1\nmove 2 from 7 to 9\nmove 8 from 2 to 8\nmove 2 from 7 to 3\nmove 1 from 6 to 4\nmove 10 from 4 to 6\nmove 5 from 6 to 7\nmove 2 from 9 to 8\nmove 6 from 8 to 9\nmove 1 from 2 to 3\nmove 2 from 8 to 3\nmove 5 from 1 to 8\nmove 8 from 5 to 2\nmove 8 from 8 to 7\nmove 7 from 2 to 8\nmove 1 from 1 to 2\nmove 1 from 9 to 7\nmove 1 from 4 to 2\nmove 2 from 2 to 6\nmove 5 from 9 to 3\nmove 2 from 8 to 6\nmove 2 from 3 to 9\nmove 4 from 8 to 6\nmove 7 from 6 to 1\nmove 8 from 1 to 5\nmove 1 from 8 to 7\nmove 1 from 9 to 6\nmove 12 from 5 to 3\nmove 1 from 4 to 8\nmove 2 from 9 to 5\nmove 1 from 2 to 3\nmove 3 from 5 to 1\nmove 1 from 1 to 5\nmove 21 from 3 to 8\nmove 2 from 1 to 5\nmove 6 from 5 to 7\nmove 2 from 5 to 6\nmove 10 from 6 to 9\nmove 1 from 6 to 8\nmove 13 from 8 to 2\nmove 2 from 5 to 4\nmove 2 from 4 to 3\nmove 4 from 9 to 1\nmove 5 from 7 to 8\nmove 12 from 8 to 1\nmove 5 from 9 to 6\nmove 1 from 3 to 7\nmove 2 from 6 to 5\nmove 11 from 2 to 1\nmove 1 from 8 to 4\nmove 16 from 1 to 9\nmove 1 from 2 to 6\nmove 1 from 8 to 5\nmove 12 from 9 to 3\nmove 14 from 7 to 2\nmove 1 from 7 to 9\nmove 1 from 4 to 2\nmove 1 from 7 to 5\nmove 3 from 9 to 5\nmove 4 from 6 to 9\nmove 3 from 9 to 4\nmove 1 from 8 to 4\nmove 2 from 4 to 5\nmove 1 from 7 to 1\nmove 5 from 3 to 5\nmove 2 from 4 to 2\nmove 8 from 2 to 7\nmove 7 from 2 to 4\nmove 1 from 3 to 7\nmove 3 from 9 to 7\nmove 2 from 2 to 9\nmove 3 from 4 to 5\nmove 6 from 1 to 8\nmove 6 from 1 to 5\nmove 3 from 9 to 2\nmove 22 from 5 to 9\nmove 1 from 5 to 6\nmove 2 from 2 to 3\nmove 5 from 7 to 6\nmove 5 from 8 to 9\nmove 2 from 7 to 2\nmove 20 from 9 to 4\nmove 1 from 8 to 3\nmove 2 from 2 to 5\nmove 1 from 2 to 5\nmove 15 from 4 to 8\nmove 1 from 5 to 7\nmove 6 from 9 to 1\nmove 5 from 4 to 8\nmove 2 from 4 to 8\nmove 1 from 2 to 1\nmove 5 from 6 to 5\nmove 5 from 5 to 7\nmove 1 from 9 to 8\nmove 5 from 7 to 2\nmove 2 from 5 to 1\nmove 4 from 7 to 5\nmove 1 from 5 to 9\nmove 1 from 6 to 8\nmove 1 from 7 to 2\nmove 6 from 3 to 4\nmove 3 from 5 to 7\nmove 1 from 9 to 2\nmove 6 from 2 to 3\nmove 1 from 3 to 4\nmove 13 from 8 to 9\nmove 7 from 1 to 5\nmove 6 from 9 to 2\nmove 1 from 1 to 4\nmove 6 from 2 to 3\nmove 1 from 1 to 4\nmove 5 from 9 to 7\nmove 11 from 8 to 4\nmove 7 from 7 to 3\nmove 2 from 7 to 8\nmove 1 from 8 to 2\nmove 8 from 4 to 1\nmove 2 from 1 to 6\nmove 2 from 5 to 8\nmove 3 from 1 to 9\nmove 1 from 8 to 2\nmove 11 from 3 to 2\nmove 2 from 8 to 9\nmove 9 from 4 to 7\nmove 11 from 3 to 8\nmove 7 from 9 to 6\nmove 5 from 4 to 6\nmove 3 from 7 to 3\nmove 1 from 7 to 1\nmove 5 from 7 to 6\nmove 2 from 3 to 5\nmove 1 from 3 to 4\nmove 5 from 2 to 5\nmove 1 from 1 to 7\nmove 1 from 4 to 8\nmove 1 from 7 to 6\nmove 7 from 5 to 7\nmove 2 from 5 to 7\nmove 3 from 1 to 7\nmove 1 from 2 to 3\nmove 1 from 6 to 4\nmove 1 from 3 to 4\nmove 1 from 5 to 3\nmove 18 from 6 to 4\nmove 9 from 7 to 1\nmove 14 from 4 to 6\nmove 3 from 6 to 4\nmove 12 from 6 to 7\nmove 2 from 5 to 3\nmove 3 from 7 to 4\nmove 6 from 4 to 7\nmove 5 from 1 to 7\nmove 5 from 4 to 5\nmove 5 from 2 to 1\nmove 9 from 8 to 4\nmove 9 from 1 to 3\nmove 2 from 8 to 2\nmove 4 from 2 to 4\nmove 1 from 7 to 6\nmove 1 from 2 to 3\nmove 1 from 8 to 9\nmove 1 from 6 to 9\nmove 2 from 9 to 3\nmove 3 from 4 to 1\nmove 13 from 3 to 5\nmove 12 from 5 to 1\nmove 7 from 1 to 8\nmove 1 from 3 to 6\nmove 4 from 5 to 4\nmove 1 from 5 to 2\nmove 8 from 4 to 9\n")

(defn transpose [m]
  (apply map vector m))

(defn parse-input [input]
  (let [[stacks instructions] (str/split input #"\n\n")]
    {:stacks       (->> (str/split-lines stacks)
                        (map #(str % " "))
                        (map (partial partition 4))
                        (map (fn [line]
                               (->> line
                                    (map str/join)
                                    (map (partial re-find #"[A-Z0-9]")))))
                        (transpose)
                        (map reverse)
                        (map (partial filter (comp not nil?)))
                        (map (fn [[n & stack]]
                               [(parse-long n) (vec (map keyword stack))]))
                        (into {}))
     :instructions (->> (str/split-lines instructions)
                        (map (partial re-seq #"[0-9]+"))
                        (map (fn [vals]
                               (let [[move from to] (map parse-long vals)]
                                 {:move move
                                  :from from
                                  :to   to}))))}))


(defn move [stacks from to num]
  (let [crates (get stacks from)
        split-idx (- (count crates) num)]
    (-> stacks
        (update from #(subvec % 0 split-idx))
        (update to #(into % (subvec crates split-idx))))))

(defn exec-bulk [stacks instruction]
  (let [{move' :move, from :from, to :to} instruction]
    (move stacks from to move')))

(defn exec-iter [stacks instruction]
  (let [{move' :move, from :from, to :to} instruction]
    (nth (iterate #(move % from to 1) stacks) move')))

(def ^:dynamic *move-fn* exec-iter)

(defn exec-all [state]
  (reduce *move-fn* (:stacks state) (:instructions state)))

(defn solve []
  (->> (parse-input input)
       exec-all
       (sort-by key)
       (map val)
       (map last)
       (map name)
       (str/join)))

(defn part1 []
  (binding [*move-fn* exec-iter] (solve)))

(defn part2 []
  (binding [*move-fn* exec-bulk] (solve)))