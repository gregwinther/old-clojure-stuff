(ns stuff.cycloid
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))


(defn linspace [start stop num]
   (take
     num
     (range
       start
       Double/POSITIVE_INFINITY
       (/ (- stop start) (dec num)))))

(defn inc-if [v bool]
        (if bool

          (inc v)
          v))

(defn update-state[state]
  (-> state
      (update-in [:frame] inc-if (:play? state))
      (assoc :i (/ (:frame state) 500))))
      

(defn draw-state [state]
  (q/background 0.2)
  (q/stroke 0.3 0.7 0.7)
  (q/stroke-weight (:size state))
  (q/fill 0.3)
  (q/text (str (:point-count state)) 10 20)
  (q/begin-shape (:mode state))
   (let [i (:i state) 
         r (:r state)
         point-count (:point-count state)]
     (q/text (str i) 10 10)
     (q/translate (/ (q/width) 2) (/ (q/height ) 2))
     (doseq [t (linspace 0 (* 20 (. Math PI)) point-count)]
        (let [c (q/cos (* i t))
              s (q/sin (* i t)) ]
          (q/vertex (+ (* 2 r c) (* r (q/cos t))) 
                   (+ (* 2 r s) (* r (q/sin t))) )))
   (q/end-shape)
   (if (not (:play? state))
       (q/no-loop))))

(defn setup []
  (q/color-mode :hsb 1.0)
  (q/frame-rate 20)
  (q/background 0 0 0)
  (q/text "By halvarsu" (- (q/width) 80) (- (q/height) 10))
  { :play? false
   :frame 100000
   :point-count 50
   :i 0
   :r 72
   :mode :points
   :size 2})

(defn mouse-click [state event]
  (q/save-frame "output.png")
    )


(defn save-frame [state]
  (q/save (format "cycloid%.2f.png" (float (:i state)) )))

(def button-choices {:a #(update %1 :point-count inc)
                     :q #(update %1 :point-count + 50)
                     :d #(update %1 :point-count dec)
                     :e #(update %1 :point-count - 50)
                     :n #(update %1 :frame inc)
                     :p #(update %1 :frame dec)
                     :s save-frame
                     :x #(assoc %1 :play? (not (:play? %1)))
                     :1 #(assoc %1 :mode :points)
                     :2 #(assoc %1 :mode :lines)
                     :3 #(assoc %1 :mode :triangles)
                     :up #(update %1 :r inc)
                     :down #(update %1 :r dec)
                     :r #(assoc %1 :frame 0)
                     :< #(update %1 :size dec)
                     :> #(update %1 :size inc)})


(defn key-pressed [state event]
  (q/start-loop)
  (let [c (:key event)
        func (c button-choices)]
    (if func
      (func state)
      state)))

  ;(let [button (q/key-as-keyword)
       ;func (button button-choices)]
       ;(func state)
  ;     (q/redraw))) 

(q/defsketch cycloid
  :host "cycloid"
  :size [1280 800]
  :draw draw-state
  :update update-state
  :setup setup
  :mouse-pressed mouse-click
  :key-pressed key-pressed
  :middleware [;m/pause-on-error 
               m/fun-mode])

(defn refresh []
  (use :reload 'stuff.cycloid)
  (.loop cycloid))

; ============================================================
; ============================================================
; ============================================================

