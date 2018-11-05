(ns stuff.clicker 
  (:require [quil.core :as q]))

(defn draw []
  (swap! circles grow-circles 1)
  (let [values @circles]
    ;(q/begin-shape)
    (doseq [{[x y] :loc size :size} @circles ]
      ;(q/vertex x y)
      (q/fill 204 102 0)
      (q/ellipse x y size size)
      )
    ;(q/end-shape)
    ))

(defn grow-circle
  [circle inc-amount]
  (if (:growing circle)
    (update circle :size + inc-amount)
    circle))

(defn grow-circles
  [state inc-amount]
  (map #(grow-circle % inc-amount) state ))


(defn add-circle
  [state x y size]
  (conj state {:loc [x y] :size size :growing true}))


(def circles (atom []))
(swap! circles add-circle 1 1 10)
(swap! circles grow-circles 1)

(defn setup []
  (q/frame-rate 20)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10)))

(defn draw-circle []
  (let [x (q/mouse-x) 
        y (q/mouse-y)
        size 10]
    (q/fill 204 102 0)
    (swap! circles add-circle x y size)
    (q/ellipse x y size size)))
(defn stop-growing []
  (swap! circles map #(update % :growing false)))
(stop-growing)

(q/defsketch clicker
  :size [600 600]
  :draw draw
  :setup setup
  :mouse-pressed draw-circle
  :key-pressed stop-growing)
