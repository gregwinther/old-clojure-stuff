(ns fractal-trees.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def maxDepth 14)
(def dl 0.66)

(defn setup []
  (q/frame-rate 30)
  (q/fill 0)
  (q/no-loop)
  (q/color-mode :hsb)
  {:angle 6.0})

(defn update-state [state]
  state)
  ;{:angle (+ (:angle state) 0.01)})

(defn branch [len width depth angle]
  (q/stroke-weight width)
  ;(q/line 0 0 0 (- len))
  (q/ellipse 0 0 (/ len  dl) (/ len dl))
  (q/translate 0 (- len))
  (if (< depth maxDepth)
    (do (q/push-matrix)
        (q/rotate angle)
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix)
        (q/push-matrix)
        (q/rotate (- (* angle 0.2)))
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix)))
)

(defn draw-state [state]
  (comment let [w (q/width)
        h (q/height)]
    (q/background 51)
    (q/stroke 255)
    (q/translate (* w 1/2) (* h 3/4))
    (q/rotate 0.5)
    (q/translate 0 (* h 1/3))
    (branch (* h 8/20) 2 1 (:angle state))
    ))


(q/defsketch fractal-trees
  :host "fractal-trees"
  :size [1920 1200]
  :setup setup
  :update update-state
  :draw draw-state
  ;:features [:keep-on-top]
  :middleware [m/fun-mode])



