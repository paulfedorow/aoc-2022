(ns aoc.day15
  [:require [clojure.string :as str]])

(def input "Sensor at x=3797530, y=3451192: closest beacon is at x=3316341, y=3328308\nSensor at x=3779164, y=33938: closest beacon is at x=4608350, y=708806\nSensor at x=1331810, y=3260896: closest beacon is at x=2075597, y=3280016\nSensor at x=393374, y=696899: closest beacon is at x=2021690, y=453306\nSensor at x=2928048, y=923094: closest beacon is at x=2021690, y=453306\nSensor at x=2386726, y=3645023: closest beacon is at x=2075597, y=3280016\nSensor at x=1900159, y=2381031: closest beacon is at x=1649961, y=2000000\nSensor at x=2601378, y=2979844: closest beacon is at x=2218962, y=2701963\nSensor at x=2254818, y=32199: closest beacon is at x=2021690, y=453306\nSensor at x=2689643, y=375840: closest beacon is at x=2021690, y=453306\nSensor at x=909141, y=2842547: closest beacon is at x=2218962, y=2701963\nSensor at x=3915731, y=2454320: closest beacon is at x=4268501, y=1853073\nSensor at x=1693574, y=1344104: closest beacon is at x=1649961, y=2000000\nSensor at x=1760260, y=3297662: closest beacon is at x=2075597, y=3280016\nSensor at x=1909567, y=3990737: closest beacon is at x=2075597, y=3280016\nSensor at x=2097863, y=3179766: closest beacon is at x=2075597, y=3280016\nSensor at x=3100489, y=3623847: closest beacon is at x=3104748, y=4102403\nSensor at x=2746023, y=2432826: closest beacon is at x=2218962, y=2701963\nSensor at x=3031245, y=3031354: closest beacon is at x=3316341, y=3328308\nSensor at x=277094, y=1999350: closest beacon is at x=1649961, y=2000000\nSensor at x=1763269, y=126349: closest beacon is at x=2021690, y=453306\nSensor at x=3287624, y=2695420: closest beacon is at x=3316341, y=3328308\nSensor at x=2371102, y=1745103: closest beacon is at x=1649961, y=2000000\nSensor at x=3553438, y=1563379: closest beacon is at x=4268501, y=1853073\nSensor at x=1529129, y=2735122: closest beacon is at x=2218962, y=2701963\nSensor at x=2826220, y=3958350: closest beacon is at x=3104748, y=4102403\nSensor at x=3999334, y=3912693: closest beacon is at x=3104748, y=4102403\nSensor at x=240430, y=3829436: closest beacon is at x=-742036, y=3963149\nSensor at x=3455748, y=3814861: closest beacon is at x=3316341, y=3328308\n")

(defn parse-longs [s]
  (->> (re-seq #"\d+" s)
       (map parse-long)))

(defn parse-input [input]
  (->> (str/split-lines input)
       (map (fn [line]
              (let [[x1 y1 x2 y2] (parse-longs line)]
                {:sensor [x1 y1]
                 :beacon [x2 y2]})))))

(defn distance [[x1 y1] [x2 y2]]
  (+ (abs (- x1 x2))
     (abs (- y1 y2))))

(defn scan-x [sensors y]
  (let [beacons (->> (map :beacon sensors)
                          (keep (fn [[bx by]]
                                  (if (= y by)
                                    bx
                                    nil)))
                          (set)
                          (count))
        intervals (keep (fn [sensor]
                          (let [[sx sy :as sensor-coord] (:sensor sensor)
                                radius (distance sensor-coord (:beacon sensor))
                                dy (abs (- y sy))]
                            (if (> radius dy)
                              [(- sx (- radius dy))
                               (+ sx (- radius dy))]
                              nil)))
                        sensors)
        minx (reduce min (map first intervals))
        maxx (reduce max (map second intervals))]
    (loop [x minx, sum 0, tuning-frequency nil]
      (if (< x maxx)
        (let [skips (keep (fn [[s e]]
                            (if (<= s x e)
                              e
                              nil))
                          intervals)]
          (if (empty? skips)
            (recur (inc x)
                   sum
                   (+ (* x 4000000) y))
            (let [max-skip (inc (reduce max skips))]
              (recur max-skip
                     (+ sum (- max-skip x))
                     tuning-frequency))))
        {:tuning-frequency tuning-frequency
         :non-beacon-count (- sum beacons)}))))

(defn part1 []
  (let [sensors (parse-input input)]
    (:non-beacon-count (scan-x sensors 2000000))))

(defn part2 []
  (let [sensors (parse-input input)]
    (first (keep (fn [y]
                   (let [result (scan-x sensors y)]
                     (if (contains? result :tuning-frequency)
                       (:tuning-frequency result)
                       nil)))
                 (range 0 (inc 4000000))))))