(ns aoc.day25
  (:require [clojure.string :as str]
            [clojure.math :as math]))

(def input "1=021=20---11-1=1\n222==-=1-2=-11-11\n1010==0=0=1--=1\n2\n1122=\n2222==2222-2--10\n100=-2-002=\n12221-2-10-=1=\n1-=211000=221-1\n2=--12==11221-2-002\n1101==-01===01=\n220=0=-2-1-0===10=2\n2-0-\n1=-12=2==\n1-=-02==-1\n100-0-11--02--0=-\n1==0-1220011=-\n21\n1102-22==\n222\n1==0=22-0--011-0\n1020\n2==2-102\n1010-02==02=\n1=1\n2=010==12022=\n1=-22001-2=\n101=2-201122111\n1=-0021211-1-1-1202\n2-22-2\n1212002==1\n2=-0-\n1-1==1122110100\n1--020-2-=-0-21--\n2=22-21220022==1=\n1=-00-121-20-2122\n1-==-00\n20=21=0\n20-02-0-=-==-=22\n1120==-2\n2=--0-0-000201=101\n1---=02=21\n1-011\n211-2112=20--2\n1=-\n1=--=021\n1-1-122200==1-1111\n11001-\n200=200\n1===21=1-2\n1===10\n2=22\n10-110210=01112-\n2121=1=2=-201-\n1---2=-0=-\n211-120---000=-0-\n1-\n1-101-2=01020=-0=\n112-12--0=12=12--\n1-0-=2--=--12=20\n1=0=-121\n1000122-210--011200\n120==-1-=01\n1=2=1--\n101-1=--2-\n2=\n2--10\n2201020-=2-\n1===2001-1=0=\n222=-2---2=112\n2010=-22=012=202=\n111\n1-2112=0200=1\n2=02211=2\n2-121\n1----12=-2011\n2==0\n112\n22=0==1-1=\n1-20=0-\n1=110211-022201222\n20\n1-111\n1=-=022011=2=2=1\n1-02122\n2-1\n1-2-=2-0=0---\n1-20\n1=1=102\n1=1=0--1\n11222-==0101-\n2122100=0\n2011==12-10\n210--121-0=\n22121=01=0=-=-=-==\n10-0-=1-12\n2-2=21\n1=1-0-0-\n10020\n10-0-0120=211-2-\n1-==10-0-==001201-\n1-0\n21=00202002-0\n1-1=2021\n11=1==1=0\n1=102-1=-20-0\n2=100-12-0=\n1=0\n1--=\n12=212110200-\n222002100\n")

(defn parse-input [input]
  (str/split-lines input))

(defn ->dec [snafu]
  (->> (map {\2 2, \1 1, \0 0, \- -1, \= -2} snafu)
       (map #(* (long (math/pow 5 %1)) %2) (reverse (range 0 (count snafu))))
       (reduce +)))

(defn ->snafu [dec]
  (loop [dec dec, snafu '()]
    (let [mod (mod dec 5)
          quot (quot dec 5)]
      (cond
        (= mod quot 0) (str/join snafu)
        (>= mod 3) (recur (inc quot) (conj snafu ({4 \-, 3 \=} mod)))
        :else (recur quot (conj snafu ({2 \2, 1 \1, 0 \0} mod)))))))

(defn solve []
  (->snafu (reduce + (map ->dec (parse-input input)))))