(ns stuff.spiral 
  (:require [quil.core :as q]))

(defn f [t]
  (let [r (* 750 (q/sin t) (q/cos t))]
    [(* r (q/sin (* t 0.22)))
     (* r (q/cos (* t 0.22)))]))

(defn draw []
  (q/no-loop)
  (q/with-translation [(/ (q/width) 2)(/ (q/height) 2)]
  (doseq [t (range 0 3000 0.1)]
    (let [ c (q/map-range (q/cos t) -1 1 0 1)
          [x y] (f t)]
        (q/stroke c 0.5 0.5)
        (q/point x y)
        ;(q/line (f t) (f (+ t 0.1)))
        )))
  (q/save "spiral.png"))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/smooth 8)
  (q/frame-rate 200)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 2))

(q/defsketch spiral 
  :size [1280 800]
  :draw draw
  :setup setup)

(defn refresh []
  (use :reload 'stuff.spiral)
  (.loop spiral))
