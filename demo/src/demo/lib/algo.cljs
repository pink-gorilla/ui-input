(ns demo.lib.algo
  (:require
   [ui.flexlayout :refer [component-ui]]
   ))

(defmethod component-ui "panel" [{:keys [id]}]
  (fn [options]
    [:div.bg-blue-300.w-full.h-full
     "I am panel id: " (str id)
     "options: " (pr-str options)]))


(defmethod component-ui "algo" [{:keys [id]}]
  (fn [options]
    [:div 
       "I am an algo"
       [:br]
       "options"
       [:br]
       (pr-str options)]))
  