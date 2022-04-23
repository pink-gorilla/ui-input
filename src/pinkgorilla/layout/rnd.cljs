(ns pinkgorilla.layout.rnd
  "resizeable and draggable component"
  (:require
   [reagent.core :as r]
   ["react-rnd" :refer [Rnd]]))

(defn ^{:category :layout}
  rnd [data & [child]]
  [:> Rnd data child])


