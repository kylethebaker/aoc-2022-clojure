(ns aoc2022.day-05
  (:require [clojure.string :as str]))

(defn- parse-layout-row [row]
  (re-seq #"((?:\[\w\])|(?:\s{3}))\s?" row)
  )

(defn- parse-layout [raw-layout]
  (->> raw-layout
       (str/split-lines)
       (drop-last)
       (map parse-layout-row)
  ))

(defn- parse-moves [raw-moves]
  (->> raw-moves
    (re-seq #"move (\d+) from (\d+) to (\d+)")
    (map #(->> % (drop 1) (all-to-int)))))

(defn- parse-input [[raw-layout raw-moves]]
  [(parse-layout raw-layout) (parse-moves raw-moves)])

(defn- get-input []
  (-> "resources/day05.input"
    (slurp)
    (str/split #"\n\n")
    (parse-input)))


;; Answer should be
(defn part-1 []
(->> (get-input) (first))
  )

;; Answer should be
(defn part-2 []
  )
