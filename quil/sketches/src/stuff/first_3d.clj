(ns stuff.first-3d
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def img (ref nil))

(defn draw-spheres [sketch-state]
  (q/translate 350 400 100);(/ (q/screen-width) 2) (/ (q/screen-height) 2) 0)
  (q/push-matrix)
  (apply q/translate (:offsets sketch-state))
  (apply q/rotate (:rotation sketch-state))
  (let [ n 5 
         s 50]
    (doseq [x (range 0 (* 2 q/PI) (/ q/PI n))
            y (range 0 (* 2 q/PI) (/ q/PI n))]
      (q/box s)
      (q/translate [(* (* s 1) (q/cos x)) 
                    (* (* s 2) (q/cos y))]))
    (q/pop-matrix)))

    
(defn draw [sketch-state]
  (q/fill 10 100 100 100)
  (q/background 100 100 10 100)
  (q/lights) 
  (draw-spheres sketch-state))

(defn setup []
  (q/no-stroke)
  (q/background 0 0 0)
  (dosync (ref-set img (q/load-image "1up.png")))
  ;State:
  {:offsets [0 0 0]
   :rotation [0 1 0 0]
   :rotspeed 0.00 })

(def a [1 2 3])
(def test-state {:offsets [0 0 0] 
                 :rotation [0 0 0 0] 
                 :rotspeed 0.01 } )

(update-state test-state)

draw draw
  :setup setup
  :update update-state
  :renderer :p3d
  :middleware [m/pause-on-error
               m/fun-mode])

(refresh)


(defn refresh []
  (use :reload 'stuff.first-3d)
  (.loop first-3d))
