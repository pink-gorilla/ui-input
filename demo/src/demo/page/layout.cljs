(ns demo.page.layout
  (:require
   [frontend.dialog :refer [dialog-show]]
   [ui.popover :refer [popover tooltip]]))

(defn my-dialog []
  [:div
   {:style {:background-color "white"
            :padding          "16px"
            :border-radius    "6px"
            :text-align "center"}} "Hello modal!"])

(defn layout-page [{:keys [route-params query-params handler] :as route}]
  [:div
   [:h2 "dialog window"]
   [:button.bg-blue-300
    {:title "Click to show dialog!"
     :on-click #(dialog-show (my-dialog))}
    "default-size dialog!"]

   [:h2 "Popover"]
   [:div
    [:popover {:color "yellow"
               :placement "left"
               :button-text "yellow-l"}
     [:tooltip {:color "red"
                :title  "oranges"
                :content "Lets make orange juice"}]]
    [popover {:color "green"
              :placement "right"
              :button-text "trees-r"}
     [tooltip {:color "green"
               :title  "tree"
               :content "How many trees are in a forest?"}]]]])

