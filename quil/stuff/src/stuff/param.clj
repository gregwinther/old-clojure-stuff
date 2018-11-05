(ns stuff.param
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn update-state [state]
  state)

(defn draw-state [state]
  (draw-ellipse (get-in state [:objects :ellipses])))

(defn draw-ellipse [ellipse]
  (let [size (:size ellipse)
        pos (:pos ellipse)]
    (q/ellipse (:x pos) (:y pos) (:x size) (:y size))))

(defn hei [a b c]
  (println a b c))


(defn setup []
  (q/color-mode :hsb 1.0)
  {:objects {:ellipses [{:pos {:x 100 :y 100} :size {:x 10 :y 10}}]}})


(defn mouse-pos [ellipse]
  (assoc ellipse :pos {:x (q/mouse-x) :y (q/mouse-y)}))

(defn mouse-dragged [state]
  (update-in state [:objects :ellipses] mouse-pos))


(q/defsketch param
  :size [600 400]
  :mouse-dragged mouse-dragged
  :draw draw-state
  :update update-state
  :setup setup
  :middleware [m/fun-mode])

(defn refresh []
  (use :reload 'stuff.param)
  (.loop param))
