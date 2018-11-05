(ns temp-name-game.random-walker
  (:require [quil.core :as q :include-macros true]
            ;[clojure.core.matrix :as mat]
            ;[clojure.core.matrix.operators :as op]
            [quil.middleware :as m]))



(def width 880)
(def height 640)
(def step 8)

(defn abs [v]
  (if (< v 0)
    (- v)
    v))

(defn legal-y? [y]
  (<= (abs y) (- (/ height 2) step) ))


(defn legal-x? [x]
  (<= (abs x) (- (/ width 2) step) ))

(defn legal-pos? [[x y]]
  (and (legal-x? x)
       (legal-y? y)))

(defn update-pos [pos vel speed]
  (let [new-pos (vec (map + pos (map #(* speed %1) vel )))]
    (if (legal-pos? new-pos)
      new-pos
      pos)))

(defn update-vel [vel]
  (let [choices {0 [-1 0] 
                 1 [0 1] 
                 2 [1 0] 
                 3 [0 -1] }]
  (get  choices (rand-int 4))))

(update-vel [0 1])


(defn update-state [state]
  (-> state
      (update :pos update-pos  (:vel state) (:speed state))
      (update :vel update-vel)
      (update :color update-color)))

(defn update-color [old-color]
  (if (= (rand-int 1000) 0)
    (random-color) 
    old-color
    ))



(defn random-color []
  (take 3 (repeatedly #(* 80 (+ 1 (rand-int 2))))))

(defn draw-char [state]
  (q/no-stroke)
  (let [[x y]  (:pos state)
        [r g b] (:color state)]
    (q/fill r g b 40)
    (q/with-translation [(/ width 2) (/ height 2)]
    (q/ellipse x y step step)
    (q/stroke r g b 1)
    (q/line 0 0 x y))))


(defn draw-border [state]
  (q/no-stroke)
  (q/fill 255)
  (let [step  (:pix-per-unit state)
        roundoff (* step 0.2)]
    (doseq [x (range 0 width step)]
      (q/rect x 0      step step roundoff)
      (q/rect x (- height step) step step roundoff))
    (doseq [y (range step height step)]
      (q/rect 0     y step step roundoff)
      (q/rect (- width step) y step step roundoff)))
  state)

(defn draw-state [state]
  ;(q/fill (get-in  state [:pos 1]) (get-in  state [:pos 0]) 200 )
  ;(q/no-loop)
  (-> state
    ;(draw-char) 
    (loop-draw)))

(defn loop-draw [state]
  (loop [i 0 
         new-state state]
    (if (< i 100)
      (do (draw-char new-state) 
          (recur (inc i) (update-state new-state)))
      new-state)))

(def init-state {:pix-per-unit step 
                 :pos [(/ step 2) (/ step 2) ]
                 :draw-points []
                 :vel [1 0]
                 :speed step
                 :play? true
                 :color (random-color)})

(defn setup []
  (q/frame-rate 30)
  (let [state init-state]
    (draw-border state)
    (assoc state :graphic (q/create-graphics 100 height))))

(q/defsketch random-walker
  :host "random-walker"
  :size [width height]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])


