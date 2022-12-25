(ns aoc.day19
  [:require [clojure.string :as str]
            [clojure.math :as math]])

(def input "Blueprint 1: Each ore robot costs 3 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 19 clay. Each geode robot costs 3 ore and 8 obsidian.\nBlueprint 2: Each ore robot costs 3 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 9 clay. Each geode robot costs 2 ore and 10 obsidian.\nBlueprint 3: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 20 clay. Each geode robot costs 2 ore and 12 obsidian.\nBlueprint 4: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 19 clay. Each geode robot costs 3 ore and 13 obsidian.\nBlueprint 5: Each ore robot costs 3 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 16 clay. Each geode robot costs 3 ore and 14 obsidian.\nBlueprint 6: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 2 ore and 18 clay. Each geode robot costs 4 ore and 20 obsidian.\nBlueprint 7: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 11 clay. Each geode robot costs 4 ore and 12 obsidian.\nBlueprint 8: Each ore robot costs 3 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 20 clay. Each geode robot costs 2 ore and 20 obsidian.\nBlueprint 9: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 2 ore and 11 clay. Each geode robot costs 4 ore and 8 obsidian.\nBlueprint 10: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 5 clay. Each geode robot costs 3 ore and 15 obsidian.\nBlueprint 11: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 15 clay. Each geode robot costs 2 ore and 15 obsidian.\nBlueprint 12: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 19 clay. Each geode robot costs 3 ore and 10 obsidian.\nBlueprint 13: Each ore robot costs 3 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 16 clay. Each geode robot costs 2 ore and 18 obsidian.\nBlueprint 14: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 11 clay. Each geode robot costs 4 ore and 7 obsidian.\nBlueprint 15: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 4 ore and 18 clay. Each geode robot costs 3 ore and 13 obsidian.\nBlueprint 16: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 14 clay. Each geode robot costs 3 ore and 16 obsidian.\nBlueprint 17: Each ore robot costs 3 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 6 clay. Each geode robot costs 3 ore and 16 obsidian.\nBlueprint 18: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 19 clay. Each geode robot costs 4 ore and 12 obsidian.\nBlueprint 19: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 17 clay. Each geode robot costs 4 ore and 20 obsidian.\nBlueprint 20: Each ore robot costs 3 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 16 clay. Each geode robot costs 3 ore and 15 obsidian.\nBlueprint 21: Each ore robot costs 3 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 19 clay. Each geode robot costs 2 ore and 9 obsidian.\nBlueprint 22: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 20 clay. Each geode robot costs 2 ore and 17 obsidian.\nBlueprint 23: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 7 clay. Each geode robot costs 4 ore and 11 obsidian.\nBlueprint 24: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 9 clay. Each geode robot costs 3 ore and 9 obsidian.\nBlueprint 25: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 15 clay. Each geode robot costs 2 ore and 8 obsidian.\nBlueprint 26: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 16 clay. Each geode robot costs 3 ore and 13 obsidian.\nBlueprint 27: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 4 ore and 18 clay. Each geode robot costs 4 ore and 11 obsidian.\nBlueprint 28: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 4 ore and 8 clay. Each geode robot costs 3 ore and 7 obsidian.\nBlueprint 29: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 20 clay. Each geode robot costs 3 ore and 14 obsidian.\nBlueprint 30: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 11 clay. Each geode robot costs 3 ore and 8 obsidian.\n")

(defn parse-longs [s]
  (->> (re-seq #"\d+" s)
       (map parse-long)))

(defn parse-input [input]
  (->> (str/split input #"\n")
       (map (fn [s]
              (let [[blueprint
                     ore-robot-ore-cost
                     clay-robot-ore-cost
                     obsidian-robot-ore-cost
                     obsidian-robot-clay-cost
                     geode-robot-ore-cost
                     geode-robot-obsidian-cost] (parse-longs s)]
                {:id         blueprint
                 :robot-cost {:ore      {:ore ore-robot-ore-cost}
                              :clay     {:ore clay-robot-ore-cost}
                              :obsidian {:ore obsidian-robot-ore-cost, :clay obsidian-robot-clay-cost}
                              :geode    {:ore geode-robot-ore-cost, :obsidian geode-robot-obsidian-cost}}})))
       (vec)))

(defn max-geodes [blueprint minutes]
  (let [robot-cost (:robot-cost blueprint)
        max-spend (->> robot-cost
                       (map val)
                       (apply merge-with max))
        rec (fn [rec robots resources time-left]
              (->> (for [robot [:ore :clay :obsidian :geode]
                         :let [cost (robot-cost robot)
                               time-jump (try (->> (merge-with #(long (math/ceil (/ %1 %2)))
                                                               (merge-with - cost (select-keys resources (keys cost)))
                                                               (select-keys robots (keys cost)))
                                                   (vals)
                                                   (reduce max 0))
                                              (catch ArithmeticException _ nil))]
                         :when (and (int? time-jump)
                                    (< time-jump time-left)
                                    (or (= robot :geode)
                                        (< (robots robot) (max-spend robot))))]
                     (let [time-left' (- time-left time-jump 1)]
                       (rec rec
                            (update robots robot inc)
                            (merge-with min
                                        (merge-with - (merge-with + resources (update-vals robots #(* % (inc time-jump)))) cost)
                                        (update-vals max-spend #(* % time-left')))
                            time-left')))
                   (reduce max (+ (* time-left (:geode robots)) (:geode resources)))))
        rec-mem (memoize rec)]
    (rec-mem rec-mem
             {:ore 1, :clay 0, :obsidian 0, :geode 0}
             {:ore 0, :clay 0, :obsidian 0, :geode 0}
             minutes)))

(defn part1 []
  (->> (parse-input input)
       (map (fn [blueprint]
              (* (:id blueprint) (max-geodes blueprint 24))))
       (reduce +)))

(defn part2 []
  (->> (parse-input input)
       (take 3)
       (map #(max-geodes % 32))
       (reduce *)))