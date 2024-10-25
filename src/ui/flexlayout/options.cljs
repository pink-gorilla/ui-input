(ns ui.flexlayout.options
   (:require
    [ui.flexlayout :refer [component-ui]]
    [options.core :refer [options-ui]]))





(defmethod component-ui "panel" [{:keys [id options]}]
  [options-ui {:class "bg-blue-300 options-debug"
               :style {:width "50vw"
                           ;:height "40vh"
                       }} config])

