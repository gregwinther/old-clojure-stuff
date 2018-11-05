(ns stuff.flower-petals
  (:require [quil.core :as q]))


(def petal-count (atom 4))
(defn draw []
  (q/background 0 0 0.5)
  (q/no-loop)
  (q/fill 0.3 0.5 0.5)
    (let [x (/ (q/width) 2)
          y (/ (q/height) 2 )
          r 480
          n 7
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
      (q/end-shape)
      (q/save (str  "flower_petals" n ".png"))))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 4)
  )


(q/defsketch flower-petals
  :size [1280 800]
  :draw draw
  :setup setup
  )

(defn refresh []
  (use :reload 'stuff.flower-petals)
  (.loop flower-petals))
