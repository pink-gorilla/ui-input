


(defn my-dialog []
  [:div
   {:style {:background-color "white"
            :padding          "16px"
            :border-radius    "6px"
            :text-align "center"}} "Hello modal!"])

(defn input-page [{:keys [route-params query-params handler] :as route}]
  (let [*state (r/atom {:name "Someone Special"
                        :super? false
                        :language "ruby"
                        :a 3
                        :b [3 6]
                        :c 70000})]
    (fn [{:keys [route-params query-params handler] :as route}]
      [:div
       [link-href "/" "main"]
       [:div.text-green-500.mt-5.text-xl.mb-5 "input demo"]
       [layout/panel {:title "panel with controls"}
        [:div.grid ; .grid-cols-2 
         {:style {:grid-template-columns "140px 1fr"}}
         ;{:class "flex items-center justify-between"}
         [:h2 "input"]
         [input/text {} *state [:name]]
         [:h2 "button"]
         [input/button {:on-click #(alert "clicked")} "click me!"]
         [:h2 "checkbox"]
         [input/checkbox {} *state [:super?]]
         [:h2 "progress bar"]
         [:div
          [input/progressbar]
          [input/progressbar 30]
          [input/progressbar 80]
          [input/progressbar 100]]
         [:h2 "select"]
         [:div [input/select
                {:nav? true
                 :items ["javascript" "ruby" "clojure" "clojurescript"
                         "ocaml" "scheme" "elixir" "c#" "R" "python"]}
                *state [:language]]]

         [:h2 "slider"]
         ; shiny equivalent:
        ; sliderInput ("n", label = "Number of samples", min = 2, max = 1000, value = 100)
         [:div
          [input/slider {:min 1 :max 10} *state [:a]]
          [input/slider {:min 1 :max 10} *state [:b]]
          [input/slider {:min 1000 :max 100000 :step 500} *state [:c]]]

         [:h2 "Popover"]
         [:div
          [:popover {:color "yellow"
                     :placement "left"
                     :button-text "yellow-l"}
           [:tooltip {:color "red"
                      :title  "oranges"
                      :content "Lets make orange juice"}]]
          [layout/popover {:color "green"
                           :placement "right"
                           :button-text "trees-r"}
           [layout/tooltip {:color "green"
                            :title  "tree"
                            :content "How many trees are in a forest?"}]]]

         [:h2 "description list"]
         [layout/description-list
          "Applicant Information"
          "Personal details and application."

          {:name "Margot Foster"
           :position "Backend Developer"
           :email "margotfoster@example.com"
           :salary "$120,000"
           :memo "Fugiat ipsum ipsum deserunt culpa aute sint do nostrud anim incididunt cillum culpa consequat. Excepteur qui ipsum aliquip consequat sint. Sit id mollit nulla mollit nostrud in ea officia proident. Irure nostrud pariatur mollit ad adipisicing reprehenderit deserunt qui eu."}

          {:name "Full name"
           :position "Application for"
           :email "Email address"
           :salary "Salary expectation"
           :memo ""}]

         [:h2 "dialog window"]
         [:button.bg-blue-300
          {:title "Click to show dialog!"
           :on-click #(dialog (my-dialog))}
          "default-size dialog!"]

         ;[:h2.mt-5 "Tabs"]
         #_[:div
            (layout/tab
             "a" [:h4 "We love the A-team !"]
             "b" [:h4 "Bananas are a great potassium source!"])

            [layout/tab {:box :md :class "bg-green-300"}
             "a"
             [:h4 "We love the A-team !"]
             "b"
             [:h4 "Bananas are a great potassium source!"]
             "c"
             [:h4 "Christmas or santa claus?"]
             "d"
             [:h4 "do it. just  do it"]]]

         [:div.bg-gray-500.mb-12 "state:"]
         [:p (pr-str @*state)]]]])))

(add-page input-page :input)


