(ns stuff.flower-petals-clicker
  (:require [quil.core :as q]))

(def petal-count (atom 30))
(defn draw []
  (q/background 0 0 0.5)
  (q/no-loop)
  (q/fill 0.3 0.5 0.5)
    (let [x (/ (q/width) 2)
          y (/ (q/height) 2 )
          r 480
          n @petal-count
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
      (q/save (str  "flower_petals" n ".tif"))))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 20)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 4)
  )

(+ 1 2 3)

(defn change-petals [f]
  (swap! petal-count f))
(change-petals dec)

(defn mouse-press []
  (if (> (q/mouse-x) (/ (q/width) 2))
         (change-petals inc)
         (change-petals dec))
  (q/start-loop))

(q/defsketch flower-petals-clicker
  :size [1280 800]
  :draw draw
  :setup setup
  :mouse-pressed mouse-press)

(defn refresh []
  (use :reload 'stuff.flower-petals-clicker)
  (.loop flower-petals-clicker))
