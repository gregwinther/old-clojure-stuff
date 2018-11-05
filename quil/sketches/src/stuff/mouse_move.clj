(ns stuff.mouse-move
  (:require [quil.core :as q]))

(defn draw []
      )
  

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/smooth 8)
  (q/frame-rate 30)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  )

(defn mouse-moved
  []
  (q/ellipse (q/mouse-x) (q/mouse-y) 10 10))
(defn mouse-click
  []
  (q/ellipse (q/mouse-x) (q/mouse-y) 100 100))

(q/defsketch mouse-move
  :size [600 600]
  :draw draw
  :setup setup
  :mouse-moved mouse-moved
  :mouse-pressed mouse-click)

(defn refresh []
  (use :reload 'stuff.mouse-move
  (.loop mouse-move)))
