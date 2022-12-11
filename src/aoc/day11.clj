(ns aoc.day11
  [:require [clojure.string :as str]])

(def input "Monkey 0:\n  Starting items: 53, 89, 62, 57, 74, 51, 83, 97\n  Operation: new = old * 3\n  Test: divisible by 13\n    If true: throw to monkey 1\n    If false: throw to monkey 5\n\nMonkey 1:\n  Starting items: 85, 94, 97, 92, 56\n  Operation: new = old + 2\n  Test: divisible by 19\n    If true: throw to monkey 5\n    If false: throw to monkey 2\n\nMonkey 2:\n  Starting items: 86, 82, 82\n  Operation: new = old + 1\n  Test: divisible by 11\n    If true: throw to monkey 3\n    If false: throw to monkey 4\n\nMonkey 3:\n  Starting items: 94, 68\n  Operation: new = old + 5\n  Test: divisible by 17\n    If true: throw to monkey 7\n    If false: throw to monkey 6\n\nMonkey 4:\n  Starting items: 83, 62, 74, 58, 96, 68, 85\n  Operation: new = old + 4\n  Test: divisible by 3\n    If true: throw to monkey 3\n    If false: throw to monkey 6\n\nMonkey 5:\n  Starting items: 50, 68, 95, 82\n  Operation: new = old + 8\n  Test: divisible by 7\n    If true: throw to monkey 2\n    If false: throw to monkey 4\n\nMonkey 6:\n  Starting items: 75\n  Operation: new = old * 7\n  Test: divisible by 5\n    If true: throw to monkey 7\n    If false: throw to monkey 0\n\nMonkey 7:\n  Starting items: 92, 52, 85, 89, 68, 82\n  Operation: new = old * old\n  Test: divisible by 2\n    If true: throw to monkey 0\n    If false: throw to monkey 1\n")

(defn parse-longs [s]
  (->> (re-seq #"\d+" s)
       (map parse-long)))

(defn parse-input [input]
  (->> (str/split input #"\n\n")
       (map (fn [s]
              (let [[_ item-levels operation test if-true if-false] (str/split-lines s)]
                {:item-levels (parse-longs item-levels)
                 :op          (->> (str/split operation #" ")
                                   (take-last 3)
                                   (map #(case %
                                           "old" :old
                                           "*" *
                                           "+" +
                                           (parse-long %))))
                 :div         (first (parse-longs test))
                 :move-to     {true  (first (parse-longs if-true))
                               false (first (parse-longs if-false))}
                 :turns       0})))
       (vec)))

(defn turn [monkeys idx manage-fn]
  (if (seq (get-in monkeys [idx :item-levels]))
    (let [{[item-level & rest-item-levels] :item-levels
           [l op r]                        :op
           div                             :div
           move-to                         :move-to} (get monkeys idx)
          [l r] (map #(case % :old item-level %) [l r])
          item-level' (long (manage-fn (op l r)))
          test (zero? (mod item-level' div))
          move-to (move-to test)
          monkeys' (-> monkeys
                       (assoc-in [idx :item-levels] rest-item-levels)
                       (update-in [idx :turns] inc)
                       (update-in [move-to :item-levels] conj item-level'))]
      (recur monkeys' idx manage-fn))
    monkeys))

(defn round [monkeys manage-fn]
  (reduce #(turn %1 %2 manage-fn) monkeys (range 0 (count monkeys))))

(defn monkey-business [monkeys round-num manage-fn]
  (->> (nth (iterate #(round % manage-fn) monkeys) round-num)
       (sort-by :turns >)
       (take 2)
       (map :turns)
       (apply *)))

(defn part1 []
  (monkey-business (parse-input input) 20 #(/ % 3)))

(defn part2 []
  (let [monkeys (parse-input input)
        div (->> (map :div monkeys)
                 (reduce *))]
    (monkey-business (parse-input input) 10000 #(mod % div))))