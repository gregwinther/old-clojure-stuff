(ns temp-name-game.looped-walker
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

(defn random-color []
  (take 3 (repeatedly #(* 80 (+ 1 (rand-int 2))))))

(defn update-color [old-color]
  (if (= (rand-int 1000) 0)
    (random-color) 
    old-color
    ))


(defn update-state [state]
  (let [ speed (:speed state)
        N (:loop-length state)]
    (loop [pos (:pos state)
           vel (:vel state)
           color (:color state)
           new-points []
           new-colors []
           i 0]
      (if (< i N)
        (recur (update-pos pos vel speed) 
               (update-vel vel)
               (update-color color)
               (conj new-points pos)
               (conj new-colors color)
               (inc i))
        (-> state
            (assoc :pos pos)
            (assoc :vel vel)
            (assoc :color color) 
            (assoc :draw-points new-points)
            (assoc :draw-colors new-colors))))))


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

(defn draw-pos [pos color]
  (q/no-stroke)
  (let [[x y]  pos
        [r g b] color]
    (q/fill r g b 40)
    (q/with-translation [(/ width 2) (/ height 2)]
    (q/ellipse x y step step)
    (q/stroke r g b 1)
    (q/line 0 0 x y))))
  

(defn loop-draw [state]
  (comment doseq [pos (:draw-points state)])
  (draw-pos (:pos state) (80 80 160))
  state)


(defn draw-state [state]
  (-> state
    (loop-draw)))

(def init-state {:pix-per-unit step 
                 :pos [(/ step 2) (/ step 2) ]
                 :draw-points []
                 :draw-colors []
                 :vel [1 0]
                 :loop-length 1000
                 :speed step
                 :play? true
                 :color (random-color)})

(defn setup []
  (q/frame-rate 30)
  (let [state init-state]
    (draw-border state)
    (assoc state :graphic (q/create-graphics 100 height))))

(q/defsketch looped-walker
  :host "looped-walker"
  :size [width height]
  :setup setup
  :update update-state
  :draw draw-state
 :middleware [m/fun-mode
              m/pause-on-error])

(defn refresh []
  (use :reload 'temp-name-game.looped-walker)
  (.loop looped-walker))

