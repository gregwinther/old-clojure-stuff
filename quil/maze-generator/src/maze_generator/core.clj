(ns maze-generator.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))


var cols, rows;


(def w  20)
(def width  640)
(def height  480)
(def rows  (int (/ width w)))
(def cols  (int (/ height w)))
(def directions [ :up :right :down :left ])

(defn get-index 
  ([[i j]] (get-index i j))
  ( [i j]
   (if (legal-pos? i j)
     (+ i (* cols j))
     -1)))

(defn get-pos [index]
  [(mod index cols) (quot index cols)])

(defn legal-pos? 
  ([[i j]] (legal-pos? i j))
  ([i j] 
   (not (or (< i 0) (< j 0) (> i (dec rows)) (> j (dec cols))))))

(defn test-getters []
  (assert (= (get-pos (get-index 10 10))
             [10 10]))
  (assert (= (get-index (get-pos 10))
             10)))

(def cells (init-cells))


(defn new-pos 
  [[i j] dir]
  (case dir
    :up    [i (dec j)]
    :right [(inc i) j]
    :down  [i (inc j)]
    :left  [(dec i) j]) )

(defn get-neighbors [current visited]
  (filter #(not (.contains visited (vector %))) 
                  (map #(new-pos (get-pos current) %) directions)))

(defn get-next [current visited]
  (if-let [neighbors (get-neighbors current visited)]
    (rand-nth neighbors)
    nil))



(filter #(not (.contains [1 1] %)) 
        (map #(new-pos (get-pos [1 1]) %) directions))


(.contains [[1 2]] [1 2])

(get-neighbors 10 [10 -1])

(get-next 10 (get-neighbors 10 []))



(defn update-state [state]
  ( let [current (:current state)
         visited (:visited state)]
    (if-let [next-current (get-next current visited)]
      (-> state
          (assoc :current next-current)
          (update :visited #(conj % (:current state))))
      state)))

(defn draw-state [state]
  (q/no-stroke)
  (q/background 255)
  (doseq [i (range cols)
          j (range rows)]
    (q/fill (q/map-range j 0 rows 0 255) 
            200
            160)
    (q/rect (* i w) (* j w) (- w 2) (- w 2))))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:current 0
   :visited [] }) 

(q/defsketch maze-generator
  :title "Maze Generator"
  :size [height width]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; fun-mode.
  :middleware [m/fun-mode
               m/pause-on-error])
