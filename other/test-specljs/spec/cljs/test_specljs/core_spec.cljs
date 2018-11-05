(ns test-specljs.core-spec 
  (:require-macros [speclj.core :refer [describe it should=]])
  (:require [speclj.core]
            [test-specljs.core]))

(describe "A ClojureScript test"
  (it "fails. Fix it!"
    (should= 0 1)))
