(ns temp-name-game.art
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojure.math.numeric-tower :as math]))

(def width 1280)
(def height 800)
(def step 4)


(defn legal-y? [y]
  (<= (math/abs y) (- (/ height 2) step) ))

(defn legal-x? [x]
  (<= (math/abs x) (- (/ width 2) step) ))

(defn legal-pos? [[x y]]
  (and (legal-x? x)
       (legal-y? y)))

(def testv [10 20])
(legal-pos? testv)

(defn acceleration [x y vx vy]
  [(/ x (+ 1 (math/abs vx)))   
   (/ y (+ 1 (math/abs vy)))])

(defn update-pos [pos vel speed]
  (let [new-pos (vec (map + pos (map #(* speed %1) vel )))]
    (if (legal-pos? new-pos)
      new-pos
      pos)))

(defn update-vel [vel]
  (let [d (rand-int 4)]
    (cond (= d 0) [1 0]
          (= d 1) [-1 0]
          (= d 2) [0 -1]
          (= d 3) [0 1]
          )))

(map + [1 2] (map #(* % 2) [1 2]))
(map #(* % 2) [1 2])


(defn update-state [state]
  (-> state
      (update :pos update-pos  (:vel state) (:speed state))
      (update :vel update-vel)
      ;(assoc :acc (acceleration x y vx vy))
      ))



(def teststate {:pix-per-unit 10
                :pos [1 2]
                :vel [1 0]
                :acc [1 1]
                :speed 7
                })

(update-state teststate)

(update-pos (:pos teststate) (:vel teststate) (:speed teststate))

(defn draw-char [state]
  (q/no-stroke)
  (q/fill 80 160 160 100)
  (let [[x y]  (:pos state)]
    (q/with-translation [(/ width 2) (/ height 2)]
    (q/ellipse x y 4 4))))

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
  ;(draw-border state)
  (loop [i 0
         new-state state]
    (when (< i 200000)
      (draw-char new-state) 
      (recur (inc i) (update-state new-state))))
  (q/no-loop)
  state)


(defn setup []
  (q/frame-rate 30)
  (let [state {:pix-per-unit step 
               :pos [(/ step 2) (/ step 2) ]
               :vel [1 0]
               :acc [0 0]
               :mass 10
               :speed step}]
    (draw-border state)
    (assoc state :graphic (q/create-graphics 100 height))))

(defn mouse-click [state event]
  (-> state 
      (assoc :pos [(- (:x event) (/ width 2)) 
                   (- (:y event) (/ height 2))])
      (assoc :vel [0 0])))


(defn key-press [state event]
  (cond (= (:key event) :left)
      (assoc state :vel [-1 0])
      (= (:key event) :right)
      (assoc state :vel [1 0])
      (= (:key event) :up)
      (assoc state :vel [0 -1])
      (= (:key event) :down)
      (assoc state :vel [0 1])
      ( = (:key event) :spacebar )
      (assoc state :vel [0 0])
      true state))
      

(defn key-release [state ]
  state)

(q/defsketch art
  :host "art"
  :size [width height]
  :draw draw-state
  :update update-state
  :mouse-pressed mouse-click
  :key-pressed key-press
  :key-released key-release
  :setup setup
  :middleware [m/pause-on-error 
               m/fun-mode])

(defn refresh []
  (use :reload 'temp-name-game.art)
  (.loop art))


