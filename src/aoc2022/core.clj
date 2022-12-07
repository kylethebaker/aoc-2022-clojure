(ns aoc2022.core)

(defn to-int [s]
  (Integer/parseInt s))

(defn all-to-int [ss]
  (map to-int ss))

(defn sum [xs]
  (reduce + xs))
