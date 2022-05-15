
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
     :on-click #(dialog (my-dialog))}
    "default-size dialog!"]

   [:h2 "Popover"]
   [:div
    [:popover {:color "yellow"
               :placement "left"
               :button-text "yellow-l"}
     [:tooltip {:color "red"
                :title  "oranges"
                :content "Lets make orange juice"}]]
    [container/popover {:color "green"
                        :placement "right"
                        :button-text "trees-r"}
     [container/tooltip {:color "green"
                         :title  "tree"
                         :content "How many trees are in a forest?"}]]]

;[:h2.mt-5 "Tabs"]
   #_[:div
      (container/tab
       "a" [:h4 "We love the A-team !"]
       "b" [:h4 "Bananas are a great potassium source!"])

      [container/tab {:box :md :class "bg-green-300"}
       "a"
       [:h4 "We love the A-team !"]
       "b"
       [:h4 "Bananas are a great potassium source!"]
       "c"
       [:h4 "Christmas or santa claus?"]
       "d"
       [:h4 "do it. just  do it"]]]])

(add-page layout-page  :layout)