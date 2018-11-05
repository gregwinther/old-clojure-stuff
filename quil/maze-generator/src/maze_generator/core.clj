(ns maze-generator.core
  (:require 
            [clojure.set :as cset]
            [quil.core :as q]
            [quil.middleware :as m]))


(def w  20)
(def width  640)
(def height  480)
(def rows  (int (/ width w)))
(def cols  (int (/ height w)))
(def directions [ :up :right :down :left ])


(defn get-pos [index]
  [(mod index cols) (quot index cols)])


(new-pos 300 :down)

(get-pos 23)
(get-index 24 0)
(legal-pos? 24 0)


(defn legal-pos? 
  ([index] (if (>= index 0)
             (apply legal-pos? (get-pos index))
             false))
  ([i j] 
   (not (or (< i 0) (< j 0) (> i (dec cols)) (> j (dec rows))))))


(defn get-index 
  ([[i j]] (get-index i j))
  ( [i j]
   (if (legal-pos? i j)
     (+ i (* cols j))
     -1)))

(defn test-getters []
  (assert (= (get-pos (get-index 10 10))
             [10 10]))
  (assert (= (get-index (get-pos 10))
             10)))
(test-getters)



(defn new-pos 
  ([index dir] 
   (let [[i j] (get-pos index)
         [new-i new-j] (case dir
                     :up    [i (dec j)]
                     :right [(inc i) j]
                     :down  [i (inc j)]
                     :left  [(dec i) j])]
     (get-index new-i new-j))))



(defn get-adjacent
  [index]
    (vec (map #(new-pos index %) directions)))

(defn is-visited? 
  [index visited]
  (.contains visited index))

(defn get-next-vec [current visited]
  (rand-nth (seq (filter (fn [x] (if (legal-pos? x) 
                                   (not (.contains visited x))
                                   false)) 
                         (get-adjacent current)))))

(get-next-vec 2 [1 2 3 2])

(defn get-next [current visited]
  (if-let [neighbors (seq (filter legal-pos? 
                                  (vec (cset/difference (get-adjacent current) 
                                                        visited))))]
    (rand-nth (vec neighbors))
    nil))



(defn update-state [state]
  ( let [current (:current state)
         visited (:visited state)]
    ; if there is no next, backtrack
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
            160
            100)
    (q/rect (* i w) (* j w) (- w 2) (- w 2)))
  (q/fill 100 100 100)
  (doseq [index (:visited state)]
    (let [[i j] (get-pos index)]
     (q/rect (* i w) (* j w) (- w 2) (- w 2))))
  (q/fill 100 100 200)
  (let [[i j] (get-pos (:current state))]
    (q/rect (* i w) (* j w) (- w 2) (- w 2)))
  (q/fill 0 0 255)
  (q/stroke 10)
  (q/stroke-weight 2)
  (q/rect 15 6 60 35)
  (let [index (:current state)
        [i j] (get-pos index)]
    (q/fill 0 0 0)
    (q/text (str (:current state)) 20 20)
    (q/text (str "(" i ", " j ")") 20 35)
    (q/text (str (:visited state)) 20 55)
    )
  )



(def init-state 
  {:current center
   :visited (conj (disj (get-adjacent center) (- center 1))
                  ;(- center w 5)
                  (+ center w 3)
                  (- center 2))})

(def center 0)
(def init-state 
  {:current center
   :visited (disj (get-adjacent center) (+ center 1)) })

(disj (get-adjacent center) (+ center 1)) 

union

(conj #{1 2} 3)


(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  init-state) 

(defn reset-state [state event]
  init-state)

(q/defsketch maze-generator
  :title "Maze Generator"
  :size [height width]
  :setup setup
  :update update-state
  :draw draw-state
  :mouse-pressed reset-state
  :features [:keep-on-top]
  ; fun-mode.
  :middleware [m/fun-mode])
