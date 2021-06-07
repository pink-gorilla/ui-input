(ns pinkgorilla.input.goldly
  (:require
   [goldly.sci.bindings :refer [add-cljs-namespace add-cljs-bindings]]
   [systems.snippet-registry :refer [add-snippet]]))

(add-cljs-namespace [pinkgorilla.input.goldly])



; control


(add-snippet {:type :goldly
              :category :input
              :id :text
              :filename "snippets/input/text.edn"})

(add-snippet {:type :goldly
              :category :input
              :id :checkbox
              :filename "snippets/input/checkbox.edn"})

(add-snippet {:type :pinkie
              :category :input
              :id :button
              :filename "snippets/input/button.edn"})

(add-snippet {:type :goldly
              :category :input
              :id :slider
              :filename "snippets/input/slider.edn"})

(add-snippet {:type :goldly
              :category :input
              :id :slider-ion
              :filename "snippets/input/slider_ion.edn"})

(add-snippet {:type :goldly
              :category :input
              :id :select
              :filename "snippets/input/select.edn"})

(add-snippet {:type :pinkie
              :category :input
              :id :progress
              :filename "snippets/input/progress.edn"})

;; layout


(add-snippet {:type :pinkie
              :category :layout
              :id :tab1
              :filename "snippets/layout/tab1.edn"})

#_(add-snippet {:type :pinkie
                :category :layout
                :id :tab2
                :filename "snippets/layout/tab2.edn"})

(add-snippet {:type :pinkie
              :category :layout
              :id :gridlayout
              :filename "snippets/layout/gridlayout.edn"})

(add-snippet {:type :goldly-clj
              :category :layout
              :id :dialog
              :filename "snippets/layout/dialog.clj"})

(add-snippet {:type :pinkie
              :category :layout
              :id :popover
              :filename "snippets/layout/popover.edn"})

(add-snippet {:type :pinkie
              :category :layout
              :id :description-box
              :filename "snippets/layout/desc.edn"})

