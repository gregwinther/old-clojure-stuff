(ns stuff.primitives-3d
  (:require [quil.core :as q]))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (q/smooth 8)
  (q/color-mode :hsb 1)
  (q/background 0)
  (q/lights))

(defn f [x y]
  ;(* 20 (q/cos y) (q/cos x))
  (* 100 (q/noise (/ x 100) (/ (- y (q/frame-count)) 100) ))
  )
(defn draw []
  (q/background 0 0 0.8)
  (q/stroke-weight 1)
  (q/fill 1 1 0.5)
  (q/translate (/ screen-w 2) (/ screen-h 2) 0)
  (q/push-matrix)
  (q/rotate-x 1.1)
  ;(q/rotate-z 4 )
  ;(q/rotate-z (/ (q/frame-count) 50))
  (let [res 20
        max-c 800
        half (/ max-c 2)]
    (doseq [y (range (- half) half res)]
      (q/begin-shape :triangle-strip)
      (doseq [ x (range (- half) half res)]  
        (q/vertex x y (f x y))
        (q/vertex x (+ y (quot max-c res)) (f x (+ y (quot max-c res))))
        (q/vertex (+ x (quot max-c res)) y (f (+ x (quot max-c res)) y))
        (q/vertex (+ x (quot max-c res)) (+ y (quot max-c res)) (f (+ x (quot max-c res)) (+ y (quot max-c res)))))
      (q/end-shape)))
  (q/pop-matrix)
  )

(doseq [x (range 3)
        y (range 3)]
        (println x y))

(q/defsketch main
  :title "primitives 3d"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
:renderer :p3d)

(defn refresh []
  (use :reload 'stuff.primitives-3d)
  (.loop main))
