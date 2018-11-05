(ns fractal-trees.core
  (:require [quil.core]
            [quil.middleware :as m]
            [clojure.math.numeric-tower :as math]))

(def max-depth 11)
(def init-size 1/6)

(def init-angle 0)
(def max-hue 255)

(def min-hue 0)
(def dl 0.75)

(def min-width 0 );(Math/pow dl max-depth ))

(defn setup []
  (q/frame-rate 30)
  (q/fill 0)
  ;(q/no-loop)
  (q/color-mode :hsb)
  {:angle 6.0})

(defn update-state [state]
  ;state)
  {:angle (+ (:angle state) 0.02)})

(defn triangle [x y width height]
  (q/triangle x y 
              (+ x (* width 1/2)) (+ y height)
              (+ x width) y))


(defn branch [len width depth angle]
  (q/stroke-weight width)
  (q/stroke 0 0 255 (- 255 (* width 200)))
  (q/fill (q/map-range width 0 1 min-hue max-hue) 200 200 80)
  ;(q/line 0 0 0 (- len))
  (q/rect 0 0 (/ len  dl) (/ len dl))
  (q/translate 0 (- len))
  (if (< depth max-depth)
    (do (q/push-matrix)
        (q/rotate angle)
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix)
        (q/push-matrix)
        (q/rotate (- (* angle 0.2)))
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix))))

(defn draw-state [state]
  (q/rect-mode :corner)
  (let [w (q/width)
        h (q/height)]
    (q/background 0)
    (q/stroke 255)
    (q/translate (* w 1/2) (* h 1/2))
    ;(q/rotate 0.5)
    (q/translate 0 (* h init-size))
    (branch (* h init-size) 1 1 (:angle state))))

(defn on-click [state]
  (q/ellipse (q/mouse-x) (q/mouse-y) 100 100)
  (q/start-loop)
  state)

(q/defsketch fractal-trees
  :host "fractal-trees"
  :size [600 600]
  :setup setup
  :update update-state
  :mouse-pressed on-click
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/pause-on-error
               m/fun-mode])



