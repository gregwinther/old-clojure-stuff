(ns stuff.testsketch
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def current-pos (atom [500 500]))
(defn setup []
  (q/frame-rate 1)
  (q/background 200)
  (q/smooth)
  (q/no-loop))

(defn draw []
  (q/stroke (q/random 255))
  (q/stroke-weight (q/random 10))
  (q/fill (q/random 255))
  
  (let [diam (q/random 100)
        x    (q/random (q/width))
        y    (q/random (q/height))]
    (q/ellipse x y diam diam)))

q/range-incl
(defn make-ellipse
  [x y]
  (push-matrix)
  (translate x y)
  (no-stroke)
  (fill 100)
  (ellipse x y 100 100)
  (pop-matrix))

(defn draw []
  (q/stroke 200)
  (q/fill 100)

  (q/ellipse 1 1 100 100)
  (q/rect 500 500 100 100))

(q/defsketch thetest
  :title "test"
  :settings #(q/smooth 2)
  :size [1000 1000]
  :setup setup
  :draw draw)
