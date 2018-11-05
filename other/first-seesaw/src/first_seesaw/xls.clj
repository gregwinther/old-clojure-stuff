(ns xls.clj)
(use 'seesaw.font)
(use 'clojure.repl)
(use 'seesaw.core)

(def f (frame :title "Halla" :width 100 :height 100 :visible? true))

(defn display [content]
  (config! f :content content)
  content)

(show! f)

(defmacro infix
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

(defmacro infix-2
  [[operand1 op operand2]]
  (list op operand1 operand2))


(defmacro my-print
  [expression]
  `(let [result# ~expression]
        (println result#)
        result#))

(my-print "hei")

(macroexpand (quote (println)))



(def lbl (label "halla"))
(display lbl)

(pack! f)

(config f :width 100)
