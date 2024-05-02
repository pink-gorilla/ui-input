(ns demo.page.tab
  (:require
   [ui.site.ipsum :refer [random-paragraph]]
   [container :refer [tab]]))

(defn tab-page [{:keys [route-params query-params handler] :as route}]
  [:div
   [tab
    "a" [:h4 "We love the A-team !"]
    "b" [:h4 "Bananas are a great potassium source!"]]

   [tab {:class "bg-green-300"
         :style {:height "400px"
                 :width "400px"}}
    "a"
    [:h4 "We love the A-team !"]
    "b"
    [:h4 "Bananas are a great potassium source!"]
    "c"
    [:h4 "Christmas or santa claus?"]
    "d"
    [:div {:style {:overflow "scroll" :height "100%" :width "100%" }
           }
     [random-paragraph 50]]
    ]])