(ns stuff.flower-petals-animation
  (:require [quil.core :as q]))

(defn draw []
  (q/background 0 0 0.4)
  (q/fill 0.5 0.5 0.5)
    (let [x (/ (q/width) 2)
          y (/ (q/height) 2 )
          r 300
          n (+ 3 (mod (q/frame-count) 100)) 
          dt (/ (* 2 Math/PI) n)]
      (q/begin-shape)
      (q/vertex x y)
      (doseq [t (range 0 (* 2 Math/PI) dt)]
         (let [cx1 (+ x (* r (q/cos t)))
               cy1 (+ y (* r (q/sin t)))
               cx2 (+ x (* r (q/cos (+ t dt))))
               cy2 (+ y (* r (q/sin (+ t dt))))]
           (q/bezier-vertex cx1 cy1 cx2 cy2 x y)
           ))
      (q/end-shape)))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 20)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 4)
  )

(q/defsketch flower-petals-animation
  :size [600 600]
  :draw draw
  :setup setup)

(defn refresh []
  (use :reload 'stuff.flower-petals-animation)
  (.loop flower-petals-animation))
