


(defn welcome-page [{:keys [route-params query-params handler] :as route}]
  [:div
   [:h1 "ui-input demos"]

   [:a {:href "input"} [:p "input"]]
   [:a {:href "layout"} [:p "layout"]]
   [:a {:href "description-list"} [:p "Description List"]]
   [:a {:href "grid-layout"} [:p "Grid Layout"]]])

(add-page welcome-page :welcome)
