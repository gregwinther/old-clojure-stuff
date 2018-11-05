(ns stuff.conversation "A small conversation app")

(def initial-replies 
  ["Heisann" "Hallo"])

(defn get-input [replies]
  (println (str " - " (rand-nth replies)))
  (println replies)
  (read-line))

(defn -main
  [& args]
  (loop [replies initial-replies]
    (if-let [user-inp (get-input replies)]
      (if (= user-inp "stop")
        replies
        (recur (conj replies user-inp))))))
(-main)
