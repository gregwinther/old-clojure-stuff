(ns spiral.core
  (:require [quil.core :as q :include-macros true]
            [clojure.math.numeric-tower :as math]
            [quil.middleware :as m]))

(defn draw []
  (if (= 0 ((mod (q/frame-count) 100)))
    (q/background 0 0 0))
  (let [t (/ (q/frame-count) 10)
        c (q/map-range (q/cos t) -1 1 0 1)
        w2 (/ (q/width) 2)
        h2 (/ (q/height) 2)
        [x y] (f t)]
    (q/with-translation [w2 h2]
      (q/stroke c 1 1)
      (q/point x y))))

(defn f [t]
  (let [r (* 500 (q/sin t) (q/cos t))]
    [(* r (q/sin (* t 0.22)))
     (* r (q/cos (* t 0.22)))]))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/smooth 8)
  (q/frame-rate 200)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 4))


(defn draw-circle []
  (q/fill 0.5 1 1)
  (q/ellipse (q/mouse-x) (q/mouse-y) 12 12))

(q/defsketch spiral 
  :host "spiral"
  :size [600 600]
  :draw draw
  :setup setup
  :mouse-pressed draw-circle)

