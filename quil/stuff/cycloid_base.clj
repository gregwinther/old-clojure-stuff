(ns stuff.cycloid
  (:require [quil.core :as q]
            [clojure.math.numeric-tower :as math]))

(defn linspace [start stop num]
   (take
     num
     (range
       start
       Double/POSITIVE_INFINITY
       (/ (- stop start) (dec num)))))

(def r 72)
(def revs (atom 0))
(def play-state (atom true))

(defn draw []
  (if @play-state
    (q/no-loop))
    (q/background 0)
      (q/stroke 1)
      (q/fill 1)
      (let [i (+ @revs (/ (q/frame-count) 500));@revs;)
            k (/ (q/frame-count) 10)
            n (/ i 10)]
        (q/text (str i) 10 10)
        (q/translate (/ (q/width) 2) (/ (q/height ) 2))
        (doseq [t (linspace 0 (* 20 (. Math PI)) 2000)]
           (let [c (q/cos (* i t))
                 s (q/sin (* i t)) ]
             (q/point (+ (* 2 r c) (* r (q/cos t))) 
                      (+ (* 2 r s) (* r (q/sin t))) )
             (q/point (+ (* 4 r s) (* r (q/cos t))) 
                      (+ (* 4 r c) (* r (q/sin t))) )
             (q/point (+ (* 6 r c) (* r (q/cos t))) 
                      (+ (* 6 r s) (* r (q/sin t))) )
             (q/point (+ (* 8 r s) (* r (q/cos t))) 
                      (+ (* 8 r c) (* r (q/sin t))) )
            ))))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 20)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 2)
  )

(defn pause []
  (swap! play-state #(not %1))
  (if @play-state
    (q/no-loop)
    (q/start-loop)))

(defn mouse-click []
  (if (> (/ (q/width) 2) (q/mouse-x))
    (swap! revs + 1)
    (swap! revs - 1)))

(q/defsketch cycloid
  :size [1280 800]
  :draw draw
  :setup setup
  :mouse-pressed mouse-click)

(defn refresh []
  (use :reload 'stuff.cycloid)
  (.loop cycloid))
