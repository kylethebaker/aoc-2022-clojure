(ns aoc2022.day-01
  (:require [clojure.string :as str]))

(defn parse-elf-calories [calories-input]
  (->> calories-input
    (str/split-lines)
    (map #(Integer/parseInt %))))

(defn get-all-calories []
  (as-> "./resources/day01.input" _
    (slurp _)
    (str/split _ #"\n\n")
    (map parse-elf-calories _)))

(defn sum [items]
  (reduce + items))

(defn max-n [n nums]
  (->> nums
    (sort >)
    (take n)))

;; Answer should be 69912
(defn part-1 []
  (->> (get-all-calories)
    (map sum)
    (max-n 1)
    (first)))

;; Answer should be 209180
(defn part-2 []
  (->> (get-all-calories)
    (map sum)
    (max-n 3)
    (sum)))
