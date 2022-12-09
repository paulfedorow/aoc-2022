(ns aoc.day9
  [:require [clojure.string :as str]])

(def input "L 1\nD 2\nR 2\nL 1\nD 1\nL 1\nU 1\nR 1\nL 2\nR 2\nL 2\nD 1\nR 2\nD 1\nU 2\nR 2\nD 1\nR 1\nL 2\nR 1\nD 1\nU 2\nR 2\nD 1\nR 2\nL 1\nD 1\nU 1\nR 1\nD 2\nL 1\nD 1\nL 1\nU 1\nL 2\nU 1\nL 1\nU 1\nL 1\nD 2\nR 2\nU 1\nD 2\nR 1\nU 1\nD 1\nR 1\nU 2\nL 2\nD 2\nR 1\nU 2\nL 2\nU 1\nD 1\nL 1\nR 2\nL 2\nR 1\nD 2\nL 1\nD 2\nL 1\nR 2\nU 2\nD 2\nU 1\nR 2\nD 2\nL 2\nU 1\nD 2\nR 1\nL 2\nR 1\nL 2\nU 2\nD 2\nU 2\nD 2\nR 1\nU 2\nL 2\nD 1\nU 2\nL 1\nD 1\nR 2\nU 1\nL 1\nD 1\nU 2\nD 2\nR 2\nU 1\nL 2\nD 2\nL 1\nD 2\nL 1\nU 1\nR 2\nL 2\nD 1\nR 2\nU 2\nR 2\nL 2\nD 1\nR 1\nL 1\nD 2\nU 2\nL 1\nD 1\nR 1\nL 3\nU 2\nR 2\nD 3\nL 1\nR 3\nL 1\nR 2\nD 1\nU 3\nL 1\nU 2\nL 1\nD 3\nR 3\nD 1\nU 3\nR 2\nD 2\nR 2\nL 1\nU 1\nR 1\nU 2\nR 3\nU 1\nD 1\nU 1\nR 3\nD 2\nR 2\nU 3\nD 1\nR 3\nL 3\nU 3\nD 3\nR 1\nD 3\nL 1\nD 3\nL 1\nR 1\nU 1\nL 2\nD 3\nU 3\nD 2\nU 3\nD 2\nU 2\nD 1\nR 2\nU 3\nL 1\nR 2\nL 3\nU 3\nD 3\nL 2\nR 2\nU 3\nR 1\nL 1\nU 1\nD 1\nR 2\nL 3\nU 3\nD 1\nL 1\nU 1\nR 1\nD 2\nL 2\nD 2\nR 3\nD 2\nR 3\nD 2\nR 1\nD 1\nR 3\nD 1\nU 1\nL 1\nR 2\nD 3\nL 3\nR 1\nL 2\nR 1\nU 3\nD 2\nR 1\nU 1\nL 2\nR 2\nL 2\nD 1\nU 3\nR 3\nD 2\nR 1\nU 2\nL 3\nR 1\nD 1\nU 2\nL 4\nD 2\nR 1\nU 4\nR 4\nD 3\nR 3\nU 2\nL 2\nU 1\nD 3\nU 2\nD 2\nR 4\nD 3\nR 3\nD 4\nR 2\nU 2\nL 1\nD 1\nL 2\nU 4\nD 4\nR 3\nU 2\nR 3\nU 4\nD 2\nR 3\nD 4\nL 3\nD 1\nR 4\nD 4\nR 4\nL 3\nU 1\nL 1\nD 4\nL 3\nD 1\nU 2\nR 4\nU 1\nL 4\nD 3\nU 2\nR 4\nD 3\nU 3\nL 4\nU 2\nR 1\nU 1\nD 3\nR 3\nD 1\nU 1\nL 3\nR 1\nD 2\nR 4\nU 2\nD 4\nU 4\nL 4\nR 4\nD 2\nR 3\nD 3\nR 4\nU 1\nD 3\nR 4\nL 1\nU 1\nL 1\nD 4\nR 1\nU 4\nL 3\nR 2\nU 4\nR 3\nU 1\nL 3\nD 2\nL 2\nR 3\nD 1\nL 4\nD 2\nR 3\nL 4\nR 4\nL 2\nU 1\nD 1\nR 3\nU 4\nD 4\nU 4\nD 4\nR 4\nU 4\nD 1\nU 1\nD 4\nU 1\nD 4\nU 4\nR 4\nD 4\nL 4\nD 1\nU 5\nR 5\nU 5\nL 1\nR 4\nU 5\nL 2\nU 4\nD 1\nR 1\nL 4\nD 2\nL 5\nD 5\nR 3\nL 2\nR 1\nU 1\nR 5\nD 2\nU 1\nL 1\nD 4\nU 4\nL 2\nU 4\nL 1\nD 4\nL 1\nU 4\nL 3\nU 3\nD 2\nL 3\nD 2\nL 3\nR 1\nL 2\nU 1\nL 2\nR 2\nD 5\nU 4\nR 1\nD 5\nU 2\nR 3\nU 4\nL 4\nR 5\nL 2\nR 5\nU 5\nD 3\nR 2\nD 1\nR 2\nU 4\nD 1\nR 4\nD 5\nR 4\nL 4\nU 5\nD 1\nL 2\nR 1\nU 3\nL 5\nR 5\nL 2\nD 3\nL 2\nR 3\nU 2\nD 2\nU 3\nL 5\nU 3\nD 1\nR 1\nD 3\nL 2\nU 1\nD 2\nU 4\nL 4\nU 5\nR 5\nU 5\nL 5\nR 3\nL 1\nD 1\nL 3\nD 4\nR 1\nD 1\nR 3\nL 4\nU 4\nL 1\nR 5\nD 4\nU 2\nD 6\nU 6\nL 6\nU 2\nL 2\nD 6\nU 2\nR 2\nU 1\nR 6\nD 6\nR 5\nL 4\nR 3\nD 4\nL 6\nU 1\nD 2\nL 5\nU 2\nD 1\nU 6\nR 4\nL 4\nU 3\nL 3\nU 6\nL 4\nR 4\nL 5\nU 2\nL 1\nU 1\nD 5\nU 4\nD 5\nL 5\nU 3\nR 6\nU 5\nD 6\nR 3\nD 1\nL 3\nR 5\nU 5\nR 4\nD 3\nU 1\nD 1\nR 1\nD 1\nL 6\nU 2\nD 4\nU 6\nD 4\nL 6\nD 4\nR 6\nU 4\nR 2\nD 2\nU 1\nL 2\nU 5\nD 4\nL 2\nD 1\nL 3\nR 1\nL 1\nR 6\nL 3\nU 5\nD 3\nR 5\nU 1\nD 5\nR 4\nD 2\nU 6\nR 1\nU 3\nD 2\nR 1\nU 6\nR 2\nU 5\nR 4\nD 6\nU 1\nR 2\nD 6\nU 5\nR 1\nD 6\nU 2\nL 3\nU 3\nD 1\nR 4\nD 1\nL 3\nR 4\nU 2\nR 6\nL 3\nR 6\nU 6\nD 1\nR 4\nD 2\nU 6\nD 3\nR 2\nL 1\nD 5\nL 6\nR 4\nD 3\nR 2\nU 2\nR 4\nD 1\nU 2\nL 6\nD 3\nL 3\nD 6\nL 4\nU 6\nD 2\nR 3\nD 7\nL 7\nR 4\nD 2\nL 1\nR 7\nL 7\nU 7\nD 3\nR 2\nL 4\nD 1\nL 2\nU 6\nD 6\nR 5\nL 5\nU 3\nL 5\nR 2\nL 5\nU 3\nD 6\nL 1\nD 3\nL 7\nU 1\nR 7\nD 1\nR 2\nU 5\nL 3\nD 2\nL 2\nR 2\nU 2\nR 1\nL 5\nU 2\nD 2\nU 1\nR 4\nD 4\nL 6\nD 3\nU 4\nL 3\nD 3\nU 2\nL 4\nD 2\nR 3\nL 6\nR 3\nD 6\nU 1\nR 3\nU 7\nD 5\nL 1\nR 7\nL 5\nR 1\nD 7\nU 4\nL 3\nR 4\nD 1\nL 5\nD 3\nR 6\nU 5\nL 5\nR 5\nD 5\nU 7\nR 1\nD 1\nU 5\nD 3\nR 2\nU 6\nD 5\nU 3\nL 3\nR 4\nL 8\nU 2\nR 1\nU 4\nR 5\nL 6\nU 4\nR 3\nD 2\nU 7\nR 7\nD 1\nU 1\nL 3\nR 4\nU 6\nL 8\nD 4\nR 5\nD 6\nL 1\nR 8\nU 2\nL 7\nU 5\nR 4\nU 1\nD 1\nR 8\nL 6\nD 6\nR 1\nD 1\nU 2\nR 1\nL 6\nR 5\nU 1\nD 1\nL 2\nU 7\nR 4\nD 6\nL 3\nD 7\nU 1\nR 7\nD 1\nR 6\nL 2\nD 4\nL 3\nR 7\nL 7\nU 8\nR 7\nL 5\nD 8\nR 1\nD 4\nL 3\nR 7\nL 4\nU 7\nL 3\nD 7\nL 4\nR 8\nL 5\nD 2\nR 3\nU 6\nD 1\nL 7\nD 1\nU 2\nD 8\nU 7\nR 1\nU 8\nL 4\nU 4\nD 5\nR 2\nD 7\nR 5\nL 4\nD 4\nU 4\nD 5\nU 2\nD 5\nR 8\nD 7\nU 2\nD 7\nR 3\nU 6\nL 6\nD 5\nL 5\nR 3\nU 5\nR 5\nL 3\nD 3\nR 3\nD 5\nR 6\nD 3\nL 1\nU 6\nR 4\nD 4\nR 8\nD 6\nL 8\nD 2\nU 1\nL 9\nR 8\nD 6\nL 8\nR 6\nL 8\nU 4\nL 6\nR 5\nU 1\nL 5\nR 8\nD 3\nL 8\nR 9\nU 9\nL 5\nD 5\nR 2\nD 1\nU 4\nR 1\nU 2\nR 2\nL 8\nD 1\nL 3\nD 2\nU 1\nL 8\nR 5\nL 2\nD 9\nU 4\nL 5\nD 3\nU 4\nD 8\nL 4\nU 1\nL 1\nD 2\nU 6\nD 8\nU 3\nD 2\nU 3\nR 5\nU 6\nL 1\nU 1\nL 8\nD 9\nL 4\nU 4\nR 3\nD 6\nU 5\nL 8\nD 4\nU 4\nL 8\nR 6\nL 4\nU 5\nR 5\nU 4\nD 4\nU 4\nR 7\nL 8\nU 6\nL 9\nR 8\nU 5\nL 2\nU 5\nD 3\nL 7\nD 4\nR 1\nU 3\nR 6\nL 9\nR 8\nU 8\nL 7\nR 1\nL 1\nU 3\nD 8\nR 3\nD 1\nL 5\nD 2\nR 4\nU 9\nL 1\nR 1\nL 5\nR 4\nU 8\nD 6\nU 6\nR 9\nL 3\nR 9\nU 1\nR 9\nD 5\nL 4\nR 6\nL 3\nR 2\nU 9\nD 8\nR 4\nL 6\nR 5\nL 6\nU 6\nR 8\nU 10\nL 2\nR 1\nD 8\nL 9\nU 4\nD 1\nL 9\nR 6\nU 7\nD 7\nL 6\nR 6\nU 2\nL 3\nU 6\nR 4\nL 10\nR 1\nL 5\nU 2\nR 6\nD 2\nR 9\nL 4\nU 4\nD 1\nL 7\nR 8\nL 1\nD 9\nL 6\nU 6\nL 10\nU 1\nL 2\nR 2\nU 3\nL 5\nD 4\nL 8\nD 7\nL 5\nR 3\nD 7\nU 10\nD 10\nR 8\nD 3\nR 7\nD 5\nR 10\nD 9\nR 6\nL 3\nR 1\nU 8\nL 3\nR 9\nU 4\nD 3\nR 8\nD 10\nU 5\nD 8\nL 3\nD 8\nL 3\nD 6\nR 6\nD 1\nR 3\nD 10\nR 4\nL 2\nR 10\nD 4\nU 1\nL 9\nR 9\nD 10\nU 4\nD 2\nL 5\nR 8\nL 2\nR 4\nU 5\nD 1\nL 3\nU 4\nD 7\nL 11\nR 2\nL 1\nD 3\nR 8\nD 5\nU 6\nL 11\nD 7\nU 3\nR 4\nL 6\nU 2\nD 1\nR 5\nU 9\nL 3\nU 4\nR 9\nD 11\nR 1\nU 8\nL 7\nR 6\nD 11\nU 8\nR 3\nD 8\nU 6\nR 8\nU 8\nD 9\nU 6\nR 7\nU 11\nL 10\nU 11\nL 10\nU 10\nR 11\nU 10\nL 10\nR 6\nL 11\nD 1\nL 5\nD 4\nR 1\nL 4\nU 2\nD 8\nL 10\nR 8\nD 10\nL 2\nD 5\nL 11\nR 6\nL 9\nU 3\nL 1\nR 11\nL 8\nD 5\nU 11\nD 10\nR 5\nU 9\nL 4\nU 5\nR 8\nL 8\nU 1\nL 11\nU 6\nR 6\nD 5\nR 5\nL 4\nU 2\nL 6\nD 3\nL 9\nR 6\nU 3\nD 11\nR 1\nD 11\nU 4\nR 9\nD 3\nL 7\nD 7\nL 11\nD 5\nL 5\nD 8\nR 10\nU 2\nL 6\nU 10\nD 10\nR 4\nD 6\nU 10\nR 3\nU 5\nR 4\nU 3\nD 11\nR 7\nD 10\nL 6\nU 2\nL 11\nD 12\nL 8\nU 11\nD 10\nL 2\nD 10\nL 2\nR 3\nD 6\nL 9\nD 9\nU 2\nL 5\nR 3\nU 3\nD 1\nU 7\nL 5\nR 2\nU 7\nD 12\nU 5\nL 1\nR 3\nD 8\nU 3\nD 12\nR 5\nL 11\nU 5\nL 2\nR 3\nL 7\nR 9\nU 5\nD 3\nL 4\nD 8\nU 6\nR 11\nD 10\nU 3\nR 4\nL 3\nR 9\nU 7\nD 5\nL 10\nR 1\nU 8\nD 9\nL 12\nR 6\nD 11\nU 7\nD 11\nR 2\nD 9\nR 11\nU 12\nR 2\nD 11\nR 5\nL 1\nU 6\nD 4\nL 7\nU 10\nL 5\nD 8\nR 7\nL 7\nR 6\nU 7\nL 9\nU 12\nL 9\nU 2\nR 6\nU 11\nL 2\nD 3\nR 3\nU 3\nR 8\nD 5\nR 10\nD 12\nR 1\nD 3\nU 8\nD 2\nL 4\nU 7\nR 1\nL 11\nR 7\nU 11\nD 3\nR 9\nL 1\nD 1\nR 11\nL 12\nR 1\nU 11\nR 9\nL 12\nR 5\nD 11\nR 10\nU 3\nL 13\nU 11\nL 11\nR 3\nL 1\nU 12\nL 7\nU 2\nD 7\nU 9\nR 2\nU 7\nL 10\nR 9\nU 13\nD 8\nR 7\nD 7\nL 1\nU 2\nL 9\nD 9\nL 13\nR 13\nL 12\nU 3\nL 1\nR 5\nD 8\nU 2\nD 12\nR 9\nD 11\nR 12\nL 10\nU 9\nR 1\nU 11\nL 5\nR 10\nL 7\nR 9\nU 11\nD 3\nR 13\nL 11\nU 4\nR 1\nU 7\nL 2\nR 3\nL 6\nD 4\nR 8\nL 8\nU 10\nD 12\nU 9\nR 7\nD 1\nR 8\nU 7\nR 1\nD 8\nL 6\nD 13\nL 6\nR 4\nU 13\nL 2\nR 5\nD 2\nU 1\nL 6\nR 7\nD 11\nL 9\nR 11\nU 3\nL 8\nR 6\nL 10\nU 9\nR 1\nU 3\nR 1\nL 6\nR 4\nL 3\nD 8\nU 3\nD 3\nU 8\nL 12\nR 5\nL 3\nD 8\nL 7\nD 3\nR 5\nL 3\nR 11\nL 4\nU 6\nR 7\nL 11\nD 4\nR 5\nU 6\nR 13\nD 11\nU 2\nD 12\nL 8\nR 3\nD 4\nL 12\nD 7\nR 14\nU 8\nR 12\nU 14\nR 1\nL 3\nR 13\nU 14\nD 12\nR 4\nU 4\nR 14\nD 10\nU 11\nR 10\nD 13\nU 8\nR 13\nL 9\nU 6\nD 7\nU 9\nL 11\nU 10\nL 14\nU 7\nL 5\nD 1\nU 8\nR 14\nU 12\nL 1\nR 4\nL 7\nD 2\nR 5\nD 3\nR 14\nU 6\nD 7\nR 2\nL 8\nU 14\nD 12\nU 12\nL 5\nD 5\nL 1\nU 3\nL 10\nR 4\nU 2\nR 11\nD 6\nU 12\nD 5\nU 11\nD 5\nL 8\nD 1\nL 7\nD 1\nU 9\nD 6\nL 10\nD 7\nL 10\nR 7\nD 11\nL 9\nU 13\nD 10\nU 10\nR 13\nL 2\nR 13\nU 3\nR 8\nD 7\nL 2\nU 1\nL 7\nD 11\nU 6\nD 12\nL 3\nR 3\nD 1\nR 2\nD 2\nU 4\nD 1\nL 12\nU 5\nL 3\nR 13\nD 9\nL 3\nD 10\nL 5\nR 13\nD 9\nL 7\nR 12\nL 8\nU 1\nR 5\nL 3\nD 7\nR 4\nU 6\nR 4\nU 15\nD 13\nR 3\nL 14\nR 1\nU 3\nR 4\nU 12\nR 7\nD 7\nU 1\nR 12\nD 9\nR 14\nU 10\nR 1\nU 6\nD 10\nR 14\nD 12\nR 6\nD 13\nU 14\nR 15\nU 9\nL 6\nR 7\nL 13\nR 4\nD 8\nR 2\nL 6\nU 13\nL 15\nD 5\nU 4\nR 14\nD 5\nL 6\nU 3\nR 10\nL 12\nU 9\nD 5\nL 4\nU 1\nR 10\nL 3\nU 5\nR 12\nD 14\nL 10\nR 9\nD 2\nU 6\nD 6\nU 1\nL 8\nR 3\nD 8\nR 1\nD 5\nL 10\nD 11\nL 5\nR 6\nD 8\nR 2\nD 12\nL 7\nR 14\nD 8\nL 8\nU 14\nR 13\nL 7\nD 8\nU 3\nL 6\nU 6\nD 4\nR 9\nL 11\nR 2\nU 10\nR 14\nD 8\nR 2\nU 10\nR 7\nU 3\nR 4\nD 9\nU 9\nD 11\nU 5\nD 9\nR 14\nU 13\nD 5\nL 7\nR 2\nD 9\nR 2\nU 9\nD 8\nL 12\nR 12\nL 3\nR 6\nU 16\nR 15\nD 8\nU 3\nR 1\nL 8\nD 11\nL 6\nR 5\nL 14\nD 15\nU 1\nR 10\nD 11\nR 16\nD 1\nU 6\nL 12\nU 3\nD 9\nU 1\nD 13\nL 14\nR 8\nL 9\nD 3\nU 16\nR 3\nL 11\nR 13\nL 10\nU 10\nL 3\nR 7\nL 6\nR 2\nD 4\nU 15\nR 13\nU 10\nD 1\nL 3\nD 15\nL 9\nR 8\nD 14\nR 11\nL 11\nD 4\nU 15\nL 2\nU 4\nD 3\nU 6\nD 5\nU 15\nL 8\nD 14\nR 1\nU 8\nL 7\nD 8\nR 8\nU 1\nD 8\nR 11\nD 4\nU 14\nR 11\nD 16\nU 4\nD 9\nR 11\nL 16\nD 5\nL 9\nR 6\nU 9\nD 16\nR 4\nL 11\nU 11\nD 6\nU 13\nD 14\nU 13\nL 8\nU 9\nD 3\nR 4\nL 8\nD 7\nL 12\nD 6\nR 14\nL 11\nU 7\nD 4\nU 1\nL 11\nU 15\nL 5\nD 8\nU 2\nR 15\nL 5\nR 7\nL 8\nR 15\nU 8\nD 7\nU 17\nD 5\nU 15\nR 8\nU 15\nL 7\nU 5\nD 11\nL 4\nU 11\nD 13\nR 10\nD 12\nL 16\nD 9\nL 17\nU 1\nR 10\nD 1\nR 16\nU 6\nL 2\nD 7\nU 8\nD 12\nL 15\nU 16\nR 5\nL 13\nD 2\nU 7\nL 14\nD 6\nL 8\nR 12\nU 4\nD 7\nU 4\nR 3\nU 8\nD 5\nL 4\nU 3\nR 13\nL 14\nR 7\nU 11\nD 9\nR 1\nD 15\nR 11\nL 8\nR 7\nD 17\nU 13\nR 15\nD 5\nR 15\nU 7\nR 10\nU 14\nD 2\nL 7\nR 13\nD 10\nU 16\nD 6\nR 10\nD 4\nU 1\nR 14\nU 2\nL 2\nU 1\nL 13\nD 2\nU 16\nD 1\nU 8\nL 7\nU 17\nD 9\nL 10\nU 16\nD 13\nU 15\nD 12\nL 6\nD 9\nL 3\nR 17\nU 16\nL 6\nD 11\nR 11\nL 11\nU 2\nR 15\nD 11\nR 10\nU 2\nD 18\nU 18\nD 18\nL 4\nU 1\nR 1\nL 7\nU 7\nL 12\nU 10\nR 13\nD 16\nR 1\nD 11\nL 12\nR 7\nD 13\nL 10\nD 6\nU 1\nL 14\nR 13\nD 14\nL 3\nD 18\nL 2\nU 9\nL 7\nR 18\nL 11\nU 16\nR 9\nU 8\nD 5\nL 15\nR 16\nD 17\nU 16\nL 10\nU 4\nR 1\nL 10\nU 4\nL 8\nU 5\nL 5\nR 18\nU 11\nR 18\nU 12\nD 18\nR 9\nL 2\nD 10\nL 15\nD 2\nR 7\nD 16\nL 4\nR 13\nL 4\nR 2\nL 14\nD 4\nR 15\nD 14\nR 13\nL 9\nD 1\nU 7\nR 17\nL 6\nU 1\nL 6\nD 6\nU 5\nL 5\nR 10\nU 1\nR 9\nU 3\nD 2\nL 4\nU 1\nD 12\nR 5\nU 3\nR 15\nL 18\nR 3\nD 1\nL 6\nD 16\nU 15\nD 6\nR 8\nD 4\nR 14\nU 10\nR 10\nD 5\nL 14\nU 6\nL 15\nR 10\nD 17\nR 7\nL 13\nR 12\nD 4\nR 11\nD 2\nL 1\nR 13\nU 6\nD 16\nR 5\nD 17\nU 17\nD 11\nR 15\nL 4\nD 13\nL 11\nU 16\nL 3\nD 5\nU 1\nR 16\nL 10\nU 7\nD 9\nR 10\nL 4\nD 4\nL 7\nD 12\nU 5\nD 10\nR 2\nL 9\nD 6\nU 14\nR 4\nU 2\nL 19\nR 15\nL 13\nR 12\nL 8\nD 15\nU 13\nL 4\nU 13\nD 3\nU 7\nL 10\nR 8\nD 14\nR 3\nL 10\nD 3\nU 5\nL 15\nR 5\nL 2\nR 10\nD 5\nR 13\nL 18\nR 7\nL 6\nU 12\nD 16\nR 3\nL 11\nD 7\nR 1\nU 9\nR 9\nD 6\nL 1\nD 13\nU 10\nL 17\nR 10\nL 12\nR 8\nD 10\nR 12\nU 12\nD 14\nU 1\nD 4\nU 2\nL 9\nR 3\nU 2\nR 19\nD 1\nL 4\nR 6\nL 4\nD 4\nR 16\nD 17\nR 3\nD 9\nL 8\nR 15\nD 4\nU 13\nD 4\nU 17\nD 15\nL 17\nD 1\nR 14\nL 18\nU 2\nR 11\nU 5\n")

(defn parse-input [input]
  (->> (str/split-lines input)
       (map #(str/split % #" "))
       (map (fn [[direction steps]]
              [(keyword direction) (parse-long steps)]))))

(defn state [knots]
  {:knots   (repeat knots [0 0])
   :visited #{[0 0]}})

(defn move-head [direction H]
  (let [[Hx Hy] H]
    (case direction
      :U [Hx (inc Hy)]
      :R [(inc Hx) Hy]
      :D [Hx (dec Hy)]
      :L [(dec Hx) Hy])))

(defn move-tail [H T]
  (let [[Hx Hy] H
        [Tx Ty] T
        dx (- Hx Tx)
        dy (- Hy Ty)]
    (if (or (> (abs dx) 1)
            (> (abs dy) 1))
      [(+ Tx (Integer/signum dx)) (+ Ty (Integer/signum dy))]
      T)))

(defn move-one [direction state]
  (let [{knots :knots, visited :visited} state
        knots' (reduce (fn [knots knot]
                         (conj knots (move-tail (last knots) knot)))
                       [(move-head direction (first knots))]
                       (rest knots))]
    {:knots   knots'
     :visited (conj visited (last knots'))}))

(defn move [state [direction steps]]
  (nth (iterate (partial move-one direction) state) steps))

(defn part1 []
  (-> (reduce move (state 2) (parse-input input)) :visited count))

(defn part2 []
  (-> (reduce move (state 10) (parse-input input)) :visited count))

(defn visualize [state]
  (let [all-coords (into (set (state :knots)) (state :visited))
        min-x (->> all-coords (map first) (reduce min))
        max-x (->> all-coords (map first) (reduce max))
        min-y (->> all-coords (map second) (reduce min))
        max-y (->> all-coords (map second) (reduce max))
        height (inc (abs (- min-y max-y)))
        width (inc (abs (- min-x max-x)))
        patch (fn [[x y]]
                [(+ x (abs min-x))
                 (- height (+ y (abs min-y)) 1)])
        patched-knots (set (map patch (:knots state)))
        patched-visited (set (map patch (:visited state)))]
    (str/join "\n"
              (for [y (range height)]
                (apply str
                       (for [x (range width)]
                         (cond
                           (contains? patched-knots [x y]) "K"
                           (contains? patched-visited [x y]) "#"
                           :else ".")))))))