(ns stuff.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot"))


(defn add100
  [number]
  (+ number 100))

(defn dec-maker
  [dec-by]
  #(- % dec-by))

(defn my-first
  [[first-thing]]
  first-thing)

(defn chooser
  [[first-choice second-choice & all-other-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "All your other choices are: " (clojure.string/join ", " all-other-choices))))


(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(map (fn [name] (str "Hi, " name))
    ["Darth Vader" "Mr. Magoo"])

((fn [x] (* x 3)) 8)

(map #(str "Hi, " %)
    ["Darth Vader" "Mr. Magoo"])

(def addition-list (list + 1 4))
(list? (read-string "(+ 1 2)"))
(read-string "; Heisann\n#(+ 1 %)")
(clojure.core/deref)

(defn overskrift
  [word])
