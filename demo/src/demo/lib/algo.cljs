(ns demo.lib.algo
  (:require
   [ui.flexlayout :refer [component-ui]]
   ))


(defmethod component-ui "algo" [{:keys [id]}]
  (fn [options]
    [:div 
       "I am an algo"
       [:br]
       "options"
       [:br]
       (pr-str options)]))
  