(ns stuff.wave
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn update-state [state]
  state)

(defn draw-state [state]
  (draw-axes)
  state)


(defn draw-axes 
  ([] (draw-axes (range 0 100 0.1) (range 0 100 0.1)))
  ( [xticks yticks]
   (q/translate 100 100)
   (q/scale 3)
   (q/rotate (- (/ q/PI 2)))
   (q/line 10 0 0 1.1)
   (q/line 0 0 1.1 0)
   (doseq [xpos xticks] 
     (q/line xpos 0 xpos 0.05))
   (doseq [ypos yticks] 
     (q/line 0 ypos 0.05 ypos))))

q/scale
q/scale


(defn setup []
  (q/color-mode :hsb 1.0)
  {})

(q/defsketch wave
  :size [600 400]
  :draw draw-state
  :update update-state
  :setup setup
  :middleware [m/fun-mode])

(defn refresh []
  (use :reload 'stuff.wave)
  (.loop wave))
