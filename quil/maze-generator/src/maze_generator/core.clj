(ns maze-generator.core
  #?(:clj
     (:require 
            [clojure.java.io :as io]
            [clojure.set :as cset]
            [quil.core :as q]
            [quil.util :as u]
            [quil.applet :as ap]
            [quil.middleware :as m])))





(def w  20)
; (def width  80)
; (def height  80)
; (def rows  (int (/ width w)))
; (def cols  (int (/ height w)))
(def rows  20)
(def cols  20)
(def width  (* rows w))
(def height  (* cols w))
(def directions [ :up :right :down :left ])
(def total-boxes (* rows cols))
(def output-folder "out/")
(def center (atom (get-index (/ cols 2) (/ rows 2))))


(defn get-pos [index]
  [(mod index cols) (quot index cols)])


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

(defn get-next [current visited]
  (rand-nth (seq (filter (fn [x] (if (legal-pos? x) 
                                   (not (.contains visited x))
                                   false)) 
                         (get-adjacent current)))))



(defn update-state [state]
  (if (< (count (:visited-set state)) total-boxes)
    ( let [current (:current state)
           visited (:visited-set state)]
      ; if there is no next, backtrack
      (if-let [next-current (get-next current visited)]
        (-> state
            (assoc :index (count (:backtrack state)))
            (update :visited-set #(conj % (:current state)))
            (update :backtrack #(conj % (:current state)))
            (assoc :current next-current))
        (-> state
            (update :index #(- % 1))
            (update :visited-set #(conj % (:current state)))
            (assoc :current (get (:backtrack state) (:index state)))
            (assoc :backtrack (vec (drop-last (:backtrack state))))
            )))
    state)
  )




(defn to-pixels [index]
  (map #(+ (* w %) (/ w 2)) (get-pos index)))

(defn draw-state [state]
  ; (let [[i j] (get-pos (:current state))]
  ;   (q/rect (* i w) (* j w) (- w 2) (- w 2)))
  (q/no-stroke)
  (q/fill 0 0 255)
  (q/stroke 10)
  (q/stroke-weight 2)
  ; (q/with-graphics (:graphics state)
  ;   (q/ellipse 10 10 10 10)
  ; )
  (q/set-image 0 0 (:background state))
  ; (draw-info state)
  (let [pos1 (to-pixels (:current state)) 
        [x1 y1] pos1]
    (q/point x1 y1)
    ; (println (to-pixels (last (:backtrack state))))
    (if-let [pos2 (to-pixels (last (:backtrack state)))]
      (let [[x2 y2] pos2]
        (q/point x2 y2)
        (q/line x1 y1 x2 y2))
      )
    ))


(def init-state (create-init-state))
(to-pixels (:current init-state))


(defn draw-background []
  (q/with-stroke nil
    (q/no-stroke)
    (q/fill 100 100 200)
    (q/background 255)
    ; draw all rects with colors
    (doseq [i (range cols)
            j (range rows)]
      (q/fill 100
              (q/map-range j 0 rows 0 255) 
              160
              100)
      (q/rect (* i w) (* j w) (- w 2) (- w 2)))
    )
  )



(defn draw-info [state]
  (q/rect 15 6 60 35)
  (let [index (:current state)
        [i j] (get-pos index)]
    (q/fill 0 0 0)
    (q/text (str (:current state)) 20 20)
    (q/text (str "(" i ", " j ")") 20 35)))


(q/set-image)

(q/create-graphics)

(q/with-graphics)

(defn create-init-state []
  {:current @center
      :visited-set #{}
      :backtrack []
      :index 0
      :line (q/create-graphics width height)
      :background (q/create-graphics width height)})


(defn setup-state []
  (let [init-state (create-init-state)]
    (q/with-graphics (:background init-state)
      (draw-background)
    )
    init-state))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  (setup-state)) 


(defn save-image []
  (q/save (str output-folder "labyrinth" (count-pngs) ".png")))


(defn count-pngs []
  (count 
    (let [grammar-matcher (.getPathMatcher 
                          (java.nio.file.FileSystems/getDefault)
                          "glob:*.{png}")]
  (->> output-folder
       clojure.java.io/file
       file-seq
       (filter #(.isFile %))
       (filter #(.matches grammar-matcher (.getFileName (.toPath %))))
       (mapv #(.getAbsolutePath %))))))


(defn reset-state [state event]
  (setup-state))

(defn key-press [state event]
  (println (:key event))
  (case (:key event)
    :s (save-image)
    nil)
  state)


(q/defsketch maze-generator
  :title "Maze Generator"
  :size [height width]
  :setup setup
  :update update-state
  :draw draw-state
  :mouse-pressed reset-state
  :key-pressed key-press
  :features [:keep-on-top]
  :middleware [m/fun-mode])
