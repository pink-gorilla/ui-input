(ns ui.gridlayout
  (:require
   ["react-grid-layout" :as ReactGridLayout]))

; https://github.com/STRML/react-grid-layout

(defn gridlayout [data & children]
  [:<>
   [:> ReactGridLayout data
    children]])

