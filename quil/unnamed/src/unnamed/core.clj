(ns unnamed.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:color 0
   :angle 0 })

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (mod (+ (:angle state) 0.1) (* (Math/PI) 2))   })

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 240)
  ; Set circle color.
  (q/fill (:color state) 255 255)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x 0 ;(* 150 (q/cos angle))
        y 0 ;(* 150 (q/sin angle))
        r 50
        dt (/ Math/PI 3)]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      take
      ; Draw the circle.
      (doseq [x-val (repeatedly 1 rand)
              y-val (repeatedly 1 rand)]
      (draw-flower x-val y-val  :r 400 :start-angle (:angle state))
      (q/ellipse x-val y-val 50 50)
      ))))

(take 5 (repeatedly rand))

(defn draw-flower 
  [x y & {:keys [start-angle stop-angle n r]
          :or {start-angle 0
               stop-angle (+ start-angle (* (Math/PI) 2))
               n 5
               r 50}}]
  (let [dt (/ (- stop-angle start-angle) n) ]
   (q/begin-shape)
   (q/vertex x y)
   (doseq [t (range start-angle stop-angle dt)]
     (let [cx1 (+ x (* r (q/cos t)))
           cy1 (+ y (* r (q/sin t)))
           cx2 (+ x (* r (q/cos (+ t dt))))
           cy2 (+ y (* r (q/sin (+ t dt))))]
       (q/bezier-vertex cx1 cy1 cx2 cy2 x y)
       ))
   (q/end-shape)
   )
  )

(q/defsketch unnamed
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
