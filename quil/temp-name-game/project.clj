(defproject temp-name-game "0.1.0-SNAPSHOT"
  :description "A small attempt at an even smaller game"
  :url "http://example.com/unknown"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.5.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [gil "1.0.0-SNAPSHOT"]]
  :main ^:skip-aot temp-name-game.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
