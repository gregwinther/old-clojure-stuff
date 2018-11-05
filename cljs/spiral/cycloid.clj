(ns stuff.cycloid
  (:require [quil.core :as q :include-macros true]
            [clojure.math.numeric-tower :as math]
            [quil.middleware :as m]))

; Nice tricks: 
; begin-shape with triangles
;
;
;
;
;


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
(def mod-count (atom 0))
(def point-count (atom 50))

(defn get-i []
  (+ @revs (/ @mod-count 500)))


(defn draw []
  (q/background 0)
  (q/stroke 0.3 0.8 0.7)

  (q/fill 0.6)
  (q/begin-shape :lines)
  (let [i (get-i);@revs;)
        n (/ i 10)]
    (q/text (str i) 10 10)
    (q/translate (/ (q/width) 2) (/ (q/height ) 2))
    (doseq [t (linspace 0 (* 20 (. Math PI)) @point-count)]
       (let [c (q/cos (* i t))
             s (q/sin (* i t)) ]
         (q/vertex (+ (* 2 r c) (* r (q/cos t))) 
                  (+ (* 2 r s) (* r (q/sin t))) )
         ; (q/point (+ (* 4 r s) (* r (q/cos t))) 
                  ; (+ (* 4 r c) (* r (q/sin t))) )
         ; (q/point (+ (* 6 r c) (* r (q/cos t))) 
                  ; (+ (* 6 r s) (* r (q/sin t))) )
         ; (q/point (+ (* 8 r s) (* r (q/cos t))) 
                  ; (+ (* 8 r c) (* r (q/sin t))) )
         ; (q/point (+ (* 10 r s) (* r (q/cos t))) 
                  ; (+ (* 10 r c) (* r (q/sin t))) )
        )))
  (q/end-shape)
  (if @play-state
    (q/no-loop)
    (swap! mod-count + 1)))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 20)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  (q/stroke-weight 2)
  )

(defn pause []
  (swap! play-state #(not %1)))

(defn inc-points []
  (swap! point-count + 1)
  (q/start-loop))

(defn dec-points []
  (swap! point-count - 1)
  (q/start-loop))


(defn go-to-integer []
  (swap! mod-count * 0)
  (q/start-loop))

(defn mouse-click []
  (if (> (/ (q/width) 2) (q/mouse-x))
    (swap! revs + 1)
    (swap! revs - 1))
  (q/start-loop))

(defn next-frame []
  (swap! mod-count + 1)
  (q/start-loop))

(defn prev-frame []
  (swap! mod-count - 1)
  (q/start-loop))

(format "h%.2f" (float (/ 1 2)))

(defn save-frame []
  (q/save (format "cycloid%.2f.png" (float (get-i)) )))

(def button-choices {:a inc-points
                     :d dec-points
                     :i go-to-integer
                     :n next-frame 
                     :p prev-frame 
                     :s save-frame
                     :x pause})
(defn button-press []
  (let [button (q/key-as-keyword)
        func (button button-choices)]
        (func)))

(q/defsketch cycloid
  :host "cycloid"
  :size [1280 800]
  :draw draw
  :setup setup
  :mouse-pressed mouse-click
  :key-pressed button-press)

(defn refresh []
  (use :reload 'stuff.cycloid)
  (.loop cycloid))
