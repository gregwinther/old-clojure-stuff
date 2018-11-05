(ns fractal-trees.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def maxDepth 10)
(def dl 0.8)

(defn setup []
  (q/frame-rate 30)
  (q/fill 0)
  (q/color-mode :hsb)
  {:angle 0})

(defn update-state [state]
  {:angle (+ (:angle state) 0.01)})

(defn branch [len width depth angle]
  (q/stroke-weight width)
  (q/line 0 0 0 ( - len ))
  (q/translate 0 ( - len ))
  (if (< depth maxDepth)
    (do (q/push-matrix)
        (q/rotate angle)
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix)
        (q/push-matrix)
        (q/rotate (- (* angle 0.2)))
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix))))

(defn draw-state [state]
  (q/background 51)
  (q/stroke 255)
  (q/translate 200 (q/height))
  (q/ellipse 0 0 10 10)
  (branch 100 4 1 (:angle state)))

(q/defsketch fractal-trees
  :host "fractal-trees"
  :size [640 480]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])



