(ns aoc2022.day-02
  (:require [clojure.core.match :refer [match]]))

(defn parse-as-play [input]
  (case input
    ("A" "X") :rock
    ("B" "Y") :paper
    ("C" "Z") :scissors))

(defn parse-as-result [input]
  (case input
    "X" :lose
    "Y" :draw
    "Z" :win))

(defn parse-round-as-plays [[_ theirs ours]]
  [(parse-as-play theirs) (parse-as-play ours)])

(defn parse-round-as-result [[_ theirs result]]
  [(parse-as-play theirs) (parse-as-result result)])

(defn score-for-play [play]
  (case play
    :rock 1
    :paper 2
    :scissors 3))

(defn score-for-result [play]
  (case play
    :lose 0
    :draw 3
    :win 6))

(defn get-result [round]
  (match round
    [:rock :rock] :draw
    [:rock :paper] :win
    [:rock :scissors] :lose
    [:paper :rock] :lose
    [:paper :paper] :draw
    [:paper :scissors] :win
    [:scissors :rock] :win
    [:scissors :paper] :lose
    [:scissors :scissors] :draw))

(defn get-plays-for-result [round]
  (match round
    [:rock :lose] [:rock :scissors]
    [:rock :draw] [:rock :rock]
    [:rock :win] [:rock :paper]
    [:paper :lose] [:paper :rock]
    [:paper :draw] [:paper :paper]
    [:paper :win] [:paper :scissors]
    [:scissors :lose] [:scissors :paper]
    [:scissors :draw] [:scissors :scissors]
    [:scissors :win] [:scissors :rock]))

(defn score-round [[_ my-play :as round]]
  (let [result (get-result round)
        result-score (score-for-result result)
        play-score (score-for-play my-play)]
    (+ result-score play-score)))

(defn get-rounds []
  (->> "./resources/day02.input"
    (slurp)
    (re-seq #"(\w) (\w)\n")))

;; Answer should be 10595
(defn part-1 []
  (->> (get-rounds)
    (map parse-round-as-plays)
    (map score-round)
    (reduce +)))

;; Answer should be 9541
(defn part-2 []
  (->> (get-rounds)
    (map parse-round-as-result)
    (map get-plays-for-result)
    (map score-round)
    (reduce +)))
