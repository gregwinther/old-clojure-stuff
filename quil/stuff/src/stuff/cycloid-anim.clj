(ns stuff.cycloid
  (:require [quil.core :as q]))

(def points (atom []))

(defn add-point 
  [state x y]
  (conj state [x y]))

(defn draw []
  (q/background 0)
  (q/stroke 1)
  (q/no-fill)
  (q/translate (/ (q/width) 2) (/ (q/height ) 2))
  (let [t (/ (q/frame-count) 20)
        r 80
        prev-points @points
        x (* 2 r (q/cos (* 4 t)))
        y (* 2 r (q/sin (* 4 t)))]
    ;(q/translate (- x) (- y))
    (q/ellipse 0 0 (* 6 r) (* 6 r))
    (swap! points conj [(+ x (* r (q/cos t))) (+ y (* r (q/sin t)))])
    (q/stroke (/ x 600) (/ y 600) 1)
    (q/ellipse  x y (* 2 r) (* 2 r))
    (doseq [[x-prev y-prev] prev-points]
      (q/stroke (/ x-prev 600) 1 1)
      (q/point x-prev y-prev ))
    )
  )

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 30)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 2)
  )

(q/defsketch cycloid
  :size [600 600]
  :draw draw
  :setup setup)

(defn refresh []
  (use :reload 'stuff.cycloid)
  (.loop cycloid))
