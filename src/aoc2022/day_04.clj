(ns aoc2022.day-04)

(defn- parse-pairing [raw-pairing]
  (->> raw-pairing
    (drop 1)
    (map #(Integer/parseInt %))
    (partition 2)))

(defn- get-pairings []
  (->> "resources/day04.input"
    (slurp)
    (re-seq #"(\d+)-(\d+),(\d+)-(\d+)")
    (map parse-pairing)))

(defn- is-contained? [[[a1 a2] [b1 b2]]]
  (or
    (and (<= a1 b1) (>= a2 b2))
    (and (<= b1 a1) (>= b2 a2))))

(defn- is-overlapped? [[[a1 a2] [b1 b2]]]
  (and (<= a1 b2) (>= a2 b1)))

;; Answer should be 444
(defn part-1 []
  (->> (get-pairings)
    (filter is-contained?)
    (count)))

;; Answer should be 801
(defn part-2 []
  (->> (get-pairings)
    (filter is-overlapped?)
    (count)))
