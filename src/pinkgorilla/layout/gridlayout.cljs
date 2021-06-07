(ns pinkgorilla.layout.gridlayout
  (:require
   ["react-grid-layout" :as ReactGridLayout]))

; https://github.com/STRML/react-grid-layout


(defn ^{:category :layout}
  gridlayout [data & children]
  [:<>
   [:> ReactGridLayout data
    children]])

