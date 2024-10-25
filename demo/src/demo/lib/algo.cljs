(ns demo.lib.algo
  (:require
   [reagent.core :as r]
   [ui.flexlayout :refer [component-ui get-data]]
   [ui.frisk :refer [frisk]]))

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

(defmethod component-ui "data" [{:keys [id state]}]
  (let [data-a (r/atom nil)
        fetch (fn []
                (println "fetching data..")
                (reset! data-a (get-data state)))]
    (fn [options]
      [:div
       "I can show the data of the layout:"
       [:br]
       [:button {:on-click #(fetch)} "get-data"]
       [:hr]
       "data"
       ;[:hr]
       ;(pr-str @data-a)
       [:hr]
       [frisk @data-a]
       
       ])))