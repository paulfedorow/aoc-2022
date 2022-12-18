(ns aoc.day16
  [:require [clojure.string :as str]
            [clojure.math :as math]])

(def input "Valve AW has flow rate=0; tunnels lead to valves LG, TL\nValve OM has flow rate=0; tunnels lead to valves XK, IM\nValve BG has flow rate=0; tunnels lead to valves MP, SB\nValve XB has flow rate=0; tunnels lead to valves MA, TL\nValve CD has flow rate=0; tunnels lead to valves VL, OF\nValve VF has flow rate=0; tunnels lead to valves CS, XK\nValve HK has flow rate=0; tunnels lead to valves RL, QB\nValve QN has flow rate=0; tunnels lead to valves IV, QR\nValve OF has flow rate=4; tunnels lead to valves TQ, CD, IR, IM, JE\nValve QB has flow rate=14; tunnels lead to valves HK, XE, CS, VO\nValve ZE has flow rate=7; tunnels lead to valves JB, NC, SE, OI\nValve OW has flow rate=0; tunnels lead to valves MB, JB\nValve MA has flow rate=0; tunnels lead to valves XB, MB\nValve MP has flow rate=0; tunnels lead to valves VK, BG\nValve UE has flow rate=9; tunnels lead to valves ZM, RZ, WI, HO, FO\nValve QR has flow rate=24; tunnel leads to valve QN\nValve TQ has flow rate=0; tunnels lead to valves OF, AA\nValve SE has flow rate=0; tunnels lead to valves ZE, ZZ\nValve AQ has flow rate=20; tunnel leads to valve CX\nValve XE has flow rate=0; tunnels lead to valves JQ, QB\nValve DC has flow rate=8; tunnels lead to valves ZD, MJ, RZ\nValve ZM has flow rate=0; tunnels lead to valves YJ, UE\nValve VK has flow rate=21; tunnel leads to valve MP\nValve VR has flow rate=0; tunnels lead to valves WV, PS\nValve BH has flow rate=0; tunnels lead to valves AA, MB\nValve ZR has flow rate=0; tunnels lead to valves LG, AI\nValve JE has flow rate=0; tunnels lead to valves OF, HO\nValve IR has flow rate=0; tunnels lead to valves IV, OF\nValve FO has flow rate=0; tunnels lead to valves XQ, UE\nValve AA has flow rate=0; tunnels lead to valves NC, VY, BH, TQ, YJ\nValve ZZ has flow rate=0; tunnels lead to valves SE, TL\nValve XQ has flow rate=0; tunnels lead to valves IV, FO\nValve WI has flow rate=0; tunnels lead to valves UE, VO\nValve VY has flow rate=0; tunnels lead to valves AA, LG\nValve XK has flow rate=15; tunnels lead to valves VF, OM, ZD\nValve CX has flow rate=0; tunnels lead to valves AQ, MB\nValve JQ has flow rate=0; tunnels lead to valves XE, IV\nValve LG has flow rate=3; tunnels lead to valves VY, PS, ZR, AW, OI\nValve JB has flow rate=0; tunnels lead to valves ZE, OW\nValve OI has flow rate=0; tunnels lead to valves ZE, LG\nValve YJ has flow rate=0; tunnels lead to valves ZM, AA\nValve NC has flow rate=0; tunnels lead to valves AA, ZE\nValve KR has flow rate=0; tunnels lead to valves SB, MJ\nValve MB has flow rate=17; tunnels lead to valves CX, BH, AI, OW, MA\nValve AI has flow rate=0; tunnels lead to valves ZR, MB\nValve TL has flow rate=16; tunnels lead to valves ZZ, XB, AW\nValve RL has flow rate=0; tunnels lead to valves WV, HK\nValve CS has flow rate=0; tunnels lead to valves VF, QB\nValve WV has flow rate=25; tunnels lead to valves RL, VL, VR\nValve ZD has flow rate=0; tunnels lead to valves XK, DC\nValve IV has flow rate=23; tunnels lead to valves XQ, IR, JQ, QN\nValve PS has flow rate=0; tunnels lead to valves VR, LG\nValve RZ has flow rate=0; tunnels lead to valves DC, UE\nValve VO has flow rate=0; tunnels lead to valves WI, QB\nValve MJ has flow rate=0; tunnels lead to valves DC, KR\nValve IM has flow rate=0; tunnels lead to valves OM, OF\nValve VL has flow rate=0; tunnels lead to valves CD, WV\nValve SB has flow rate=18; tunnels lead to valves BG, KR\nValve HO has flow rate=0; tunnels lead to valves JE, UE\n")

(defn parse-longs [s]
  (->> (re-seq #"\d+" s)
       (map parse-long)))

(defn parse-input [input]
  (->> (str/split-lines input)
       (map (fn [line]
              (let [[flow-rate] (parse-longs line)
                    [valve & to-valves] (->> (re-seq #"[A-Z]{2}" line)
                                             (map keyword))]
                [valve {:flow-rate flow-rate
                        :to-valves (set to-valves)}])))
       (reduce conj {})))

(defn distance-matrix [graph]
  (let [init-dist (into {} (for [u (keys graph)
                                 v (keys graph)]
                             [[u v]
                              (cond
                                (contains? (get-in graph [u :to-valves]) v) 1
                                (= u v) 0
                                :else ##Inf)]))]
    (reduce (fn [dist [k i j]]
              (if (> (dist [i j]) (+ (dist [i k]) (dist [k j])))
                (assoc dist [i j] (+ (dist [i k]) (dist [k j])))
                dist))
            init-dist
            (for [k (keys graph)
                  i (keys graph)
                  j (keys graph)]
              [k i j]))))

(defn annotate-path [graph dist path]
  (if (empty? path)
    []
    (reduce (fn [path' valve]
              (let [{prev-valve :valve, prev-opened :opened} (last path')]
                (conj path' {:valve     valve
                             :flow-rate (get-in graph [valve :flow-rate])
                             :opened    (+ prev-opened (dist [prev-valve valve]) 1)})))
            [{:valve     (first path)
              :flow-rate (get-in graph [(first path) :flow-rate])
              :opened    (inc (dist [:AA (first path)]))}]
            (rest path))))

(defn released-pressure
  ([graph dist path]
   (released-pressure graph dist path 30))
  ([graph dist path minutes]
   (->> (annotate-path graph dist path)
        (filter #(< (:opened %) minutes))
        (map (fn [{opened :opened, flow-rate :flow-rate}]
               (* (- minutes opened) flow-rate)))
        (reduce +))))

(defn released-pressure-with-elephant [graph dist path path-elephant]
  (+ (released-pressure graph dist path 26)
     (released-pressure graph dist path-elephant 26)))

(defn simulated-annealing [params]
  (let [{s0        :s0
         energy    :energy
         init-temp :init-temp
         stop-temp :stop-temp
         max-steps :max-steps
         mutate    :mutate} params]
    (loop [step 0, s s0, s-best s0]
      (let [temp (* (+ stop-temp (- init-temp stop-temp)) (/ (- max-steps step) max-steps))]
        (if (or (<= temp stop-temp) (>= step max-steps))
          s-best
          (let [s-new (mutate s)
                energy-old (energy s)
                energy-new (energy s-new)
                p (math/exp (/ (- (- energy-old energy-new)) temp))]
            (recur (inc step)
                   (if (or (> energy-new energy-old) (< (rand) p))
                     s-new
                     s)
                   (if (> energy-new (energy s-best))
                     s-new
                     s-best))))))))

(defn mutate-path [path]
  (if (empty? path)
    path
    (let [i (rand-int (count path))
          j (rand-int (count path))]
      (-> path
          (assoc i (get path j))
          (assoc j (get path i))))))

(defn part1 []
  (let [graph (parse-input input)
        dist (distance-matrix graph)
        s0 (vec (keep (fn [[k v]]
                        (if (zero? (:flow-rate v))
                          nil
                          k)) graph))
        path (simulated-annealing
               {:s0        s0
                :energy    #(released-pressure graph dist %)
                :init-temp 100.0
                :stop-temp 0.01
                :max-steps 250000
                :mutate    mutate-path})]
    (released-pressure graph dist path)))

(defn part2 []
  (let [graph (parse-input input)
        dist (distance-matrix graph)
        valves (vec (keep (fn [[k v]]
                            (if (zero? (:flow-rate v))
                              nil
                              k)) graph))
        s0 [valves, []]
        energy (fn [[path path-elephant]]
                 (released-pressure-with-elephant graph dist path path-elephant))
        mutate (fn [[path path-elephant]]
                 (if (< (rand) 0.5)
                   (if (< (rand) 0.5)
                     [(mutate-path path) path-elephant]
                     [path (mutate-path path-elephant)])
                   (let [valve (rand-nth valves)
                         path' (filterv #(not= % valve) path)
                         path-elephant' (filterv #(not= % valve) path-elephant)]
                     (if (< (rand) 0.5)
                       [(let [[a b] (split-at (rand-int (inc (count path'))) path')]
                          (vec (concat a [valve] b)))
                        path-elephant']
                       [path'
                        (let [[a b] (split-at (rand-int (inc (count path-elephant'))) path-elephant')]
                          (vec (concat a [valve] b)))]))))
        [path path-elephant] (simulated-annealing
                               {:s0        s0
                                :energy    energy
                                :init-temp 100.0
                                :stop-temp 0.01
                                :max-steps 250000
                                :mutate    mutate})]
    (released-pressure-with-elephant graph dist path path-elephant)))