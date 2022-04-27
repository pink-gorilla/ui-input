

(defn input-page [{:keys [route-params query-params handler] :as route}]
  (let [*state (r/atom {:name "Someone Special"
                        :super? false
                        :language "ruby"
                        :a 3
                        :b [3 6]
                        :c 70000})]
    (fn [{:keys [route-params query-params handler] :as route}]
      [:div.bg-green-300
       [link-href "/" "main"]
       [:div.text-green-500.mt-5.text-xl.mb-5 "input demo"]
       [container/panel {:title "panel with controls"}
        [:div.grid.m-5 ; .grid-cols-2 
         {:style {:grid-template-columns "120px 1fr 120px 1fr"
                  :justify-content "space-between"}}
         ;{:class "flex items-center justify-between"}
         [:h2 "text"]
         [input/textbox {:placeholder "Name"
                      ;:on-change save-input!
                         :on-change save-input-debounced!}
          *state [:name]]
         [:h2 "text-w-64"]
         [input/textbox {:placeholder "Name"
                         :class "w-64"
                      ;:on-change save-input!
                         :on-change save-input-debounced!}
          *state [:name]]

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
         [:h2 "select (no nav)"]
         [:div [input/select
                {:nav? false
                 :items ["javascript" "ruby" "clojure" "clojurescript"
                         "ocaml" "scheme" "elixir" "c#" "R" "python"]}
                *state [:language]]]
         [:h2 "select (w-64)"]
         [:div
          [input/select
           {:class "w-64"
            :nav? false
            :items ["javascript" "ruby" "clojure" "clojurescript"
                    "ocaml" "scheme" "elixir" "c#" "R" "python"]}
           *state [:language]]]

         [:h2 "select (nav)"]
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

         [:p "flexbox test"]
         [:div.flex.flex-row.w-full
          [:div.bg-red-500.flex-none "b"]
          [:div.bg-green-500.grow "XXX"]
          [:div.bg-red-500.flex-none "e"]]

         [:div.bg-gray-500.mb-12 "state:"]
         [:p (pr-str @*state)]]]])))

(add-page input-page :input)


