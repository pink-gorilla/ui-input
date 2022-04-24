

(rf/dispatch [:css/set-theme-component :tailwind-full "light"])
(rf/dispatch [:css/set-theme-component :tailwind-girouette false])

(defn info-content []
  (let [*state (r/atom {:name "Peter"})]
    (fn []
      [:div.bg-green-300.p-5.border.border-solid.border-blue-800.w-full.h-full.overflow-hidden
       [:h1 "INFO !!!"]
       [input/textbox {:placeholder "Name"
                          ;:on-change save-input!
                       :on-change save-input-debounced!}
        *state [:name]]
       [input/button {:on-click #(alert (str (:name @*state) " is traveling to Hawai!"))} "Travel!"]])))

(defn info-box []
  [container/rnd {:bounds "window"
                  :scale 0.7
                  :default {:width 200
                            :height 200
                            :x 200
                            :y 100}
                  :style {:display "flex"
                       ;:alignItems "center"
                          :justifyContent "center"
                          :border "solid 1px #ddd"
                          :background "#f0f0f0"}}
    ;[:h1 "asdf"]
   [info-content]])

(defn welcome-page [{:keys [route-params query-params handler] :as route}]
  [:div
   [info-box]
   [:h1 "ui-input demos"]

   [:a {:href "input"} [:p "input"]]
   [:a {:href "layout"} [:p "layout"]]
   [:a {:href "description-list"} [:p "Description List"]]
   [:a {:href "grid-layout"} [:p "Grid Layout"]]])

(add-page welcome-page :welcome)
