(ns aoc2022.day-03
  (:require [clojure.string :as str]
            [clojure.set :refer [intersection]]))

(def letter-scores
  (let [lowers "abcdefghijklmnopqrstuvwxyz"
        uppers (str/upper-case lowers)
        joined (str lowers uppers)
        indexed (map-indexed #(vector %2 (inc %1)) joined)]
    (into {} indexed)))

(defn get-rucksacks []
  (->> "resources/day03.input"
    (slurp)
    (str/split-lines)))

(defn find-common [groups]
  (->> groups
    (map set)
    (apply intersection)
    (first)))

(defn common-in-compartments [sack]
  (->> sack
    (partition (/ (count sack) 2))
    (find-common)))

;; Answer should be 8493
(defn part-1 []
  (->> (get-rucksacks)
    (map common-in-compartments)
    (map #(letter-scores %))
    (reduce +)))

;; Answer should be 2552
(defn part-2 []
  (->> (get-rucksacks)
    (partition 3)
    (map find-common)
    (map #(letter-scores %))
    (reduce +)))
