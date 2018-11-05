(ns fractal-trees.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def max-depth 10)

(def dl 0.95) ; change in length
(def da 0.10) ; change in angle
(def init-len 60)

(def tree-count 15)

(def planet-radius 200)

(defn setup []
  (q/frame-rate 30)
  (q/fill 0)
  (q/color-mode :hsb)
  {:angle 0})

(defn update-state [state]
  state);{:angle (+ (:angle state) 0.01)})

(defn update-state [state]
  {:a (+ 1 (q/cos (+ (* 0.01 (q/frame-count)))))})

(defn update-state [state]
  {:angle (+ (:angle state) 0.01)})

(defn branch [len width depth angle]
  (q/stroke-weight width)
  (q/stroke 255)
  (q/line 0 0 0 len)
  (q/translate 0 len)
  (if (< depth max-depth)
    (do (q/push-matrix)
        (q/rotate angle)
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix)
        (q/push-matrix)
        (q/rotate (- (* angle 1)))
        (branch (* len dl) (* width dl) (+ depth 1) angle)
        (q/pop-matrix))))

(defn planet-with-trees [state]
  )


(defn draw-state [state]
  (q/background 51)
  (q/stroke 255)
  (q/stroke-weight 4)
  (q/translate (/ (q/width) 2) (/ (q/height) 2))
  (q/no-fill)
  (q/ellipse 0 0 planet-radius planet-radius)
  (q/fill 255 255 255)
  (doseq [i (range tree-count)]
    (q/rotate (/ q/TWO-PI tree-count))
    (q/push-matrix)
    (q/translate 0 (/ planet-radius 2))
    (branch init-len 2 1 (* q/PI da ))
    (q/pop-matrix))
  (q/no-loop))

(defn redraw [state]
  (q/start-loop)
  (q/redraw)
  state)

(q/defsketch fractal-trees
  :host "fractal-trees"
  :size [1280 800]
  :setup setup
  :mouse-pressed redraw
  :update update-state
  :draw draw-state
  ;:features [:keep-on-top]
  :middleware [m/fun-mode])



