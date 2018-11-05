(ns stuff.spiral-shape
  (:require [quil.core :as q]
            [gil.core :as g]))

(defn linspace [start stop num]
   (take
     num
     (range
       start
       Double/POSITIVE_INFINITY
       (/ (- stop start) (dec num)))))


(defn flower 
  ([x y r] (flower x y x y r))
  ([center-x center-y anchor-x anchor-y r]
   (let [dx1 (+ center-x r)
         dx2 (- center-x r)
         dy1 (+ center-y r)
         dy2 (- center-y r)]
    (q/begin-shape) 
    (q/vertex anchor-x anchor-y)
    (q/bezier-vertex dx2 dy2 dx2 dy1 anchor-x anchor-y)
    (q/bezier-vertex dx1 dy2 dx2 dy2 anchor-x anchor-y)
    (q/bezier-vertex dx1 dy1 dx2 dy1 anchor-x anchor-y)
    (q/bezier-vertex dx1 dy2 dx1 dy1 anchor-x anchor-y)
    (q/end-shape))))


(defn nflower
  ([cx cy ax ay rmin rmax n] 
   (nflower cx cy ax ay rmin rmax n [[0.5 0.5] [0.5 0.5] [0.1 0.5]]))
  ([cx cy ax ay rmin rmax n hsb-ranges]
   (let [[[hmin hmax] [smin smax] [bmin bmax]] hsb-ranges]
     (doseq [[h s b r] (reverse (map list 
                                     (linspace hmin hmin n) 
                                     (linspace smin smax n)
                                     (linspace bmin bmax n)
                                     (linspace rmin rmax n)))]
        (q/fill h s b)
        (flower cx cy ax ay r)))))


(defn draw []
  (q/background 0 0 0)
  (q/stroke-weight 0)
  (q/ellipse-mode :radius)
  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) (* 0.8 150) (* 0.8 150))
  (let [r 35 ; set to zero for stationary  flower
        t (/ (q/frame-count) 20)
        cos (* r (q/cos t))
        sin (* r (q/sin t))
        x (/ (q/width) 2) 
        y (/ (q/height) 2)
        inner 50
        outer 150
        n 8] 
    (nflower x y (+ x cos) (+ y sin) inner outer n))
  (g/save-animation "spiral_anim.gif" 63 0.1))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 30)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 4)
  )

(q/defsketch spiral-shape
  :size [600 600]
  :draw draw
  :setup setup)

(defn refresh []
  (use :reload 'stuff.spiral-shape)
  (.loop spiral-shape))
