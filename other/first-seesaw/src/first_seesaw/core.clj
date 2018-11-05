(ns first-seesaw.core)

(use 'seesaw.font)

(use 'clojure.repl)

(use 'seesaw.core)


(def f (frame :title "Get to know Seesaw"))


(-> f pack! show!)

(config f :title)
(config! f :title "NO RLY")
(config! f :content "This is some content")
(def lbl (label "I'm TEE label"))
(config! f :content lbl)


(defn display [content]
  (config! f :content content)
  content)

(display lbl)
(config! lbl :background :green :foreground "#00f")
(config! lbl :font "ARIAL-BOLD-21")
(config! lbl :font (font :name :monospaced
                         :style #{:bold :italic}
                         :size 18))

(def b (button :text "Click Me!"))

(alert "I'm an alert")
(input "Color of magic?")
(display b)

(listen b :action (fn [e] (alert e "Thanks!")))

(listen b :mouse-entered #(config! % :foreground :blue)
          :mouse-exited #(config! % :foreground :red))

(def lb (listbox :model (-> 'seesaw.core ns-publics keys sort)))


(display (scrollable lb))

(-> f pack! show!)

(selection lb {:multi? true})

(selection! lb 'all-frames)

(listen lb :selection (fn [e] (println (selection e))))

(*1)

(def field (display (text "This is a text field.")))

(text! field (slurp ""))
(print )

(text field)

(text! field "A new value")
(config! field :font "MONOSPACED-PLAIN-12" :background "#f88")

(def area (text :multi-line? true :font "MONOSPACED-PLAIN-14"
                :text "This
is 
multi
line
text"))

(selection lb)
(display area)

(text! area (slurp "http://clojure.com"))

(display (scrollable area))

(scroll! area :to :top)
(scroll! area :to :bottom)

(scroll! area :to [:line 50])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SPLITTING
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def split (left-right-split (scrollable lb) (scrollable area) :divider-location 1/3))
;=> #'user/split

(display split)


(defn doc-str [s] (-> (symbol "seesaw.core" (name s)) resolve meta :doc))

(listen lb :selection
        (fn [e]
          (when-let [s (selection e)]
            (-> area
              (text!   (doc-str s))
              (scroll! :to :top)))))


(def rbs (for [i [:source :doc]] 
           (radio :id i :class :type :text (name i))))

(display (border-panel 
           :north (horizontal-panel :items rbs)
           :center split
           :vgap 5 :hgap 5 :border 5))

(def group (button-group))

(config! (select f [:.type]) :group group)

(selection group)

(-> group selection id-of)

(listen group :selection 
        (fn [e] 
          (when-let [s (selection group)] 
(println "Selection is " (id-of s)))))
