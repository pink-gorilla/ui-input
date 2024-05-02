(ns demo.page.welcome
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [input]
   [ui.rnd :refer [rnd]]
   ;[demo.lib.debounce :refer [save-input-debounced!]]
   ))


(defn info-content []
  (let [*state (r/atom {:name "Peter"})]
    (fn []
      [:div.bg-green-300.p-5.border.border-solid.border-blue-800.w-full.h-full.overflow-hidden
       [:h1 "INFO !!!"]
       [input/textbox {:placeholder "Name"
                          ;:on-change save-input!
                       ;:on-change save-input-debounced!
                       }
        *state [:name]]
       [input/button {:on-click #(js/alert (str (:name @*state) " is traveling to Hawai!"))} "Travel!"]])))

(defn info-box []
  [rnd {:bounds "window"
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
   [:a {:href "/spaces/main"} [:p "layout"]]
   [:a {:href "description-list"} [:p "Description List"]]
   [:a {:href "grid-layout"} [:p "Grid Layout"]]
   [:a {:href "spaces/main"} [:p "Spaces Layout"]]
   [:a {:href "/sidebartree"} [:p "sidebar tree"]]
   [:a {:href "/devtools"} [:p "devtools"]]
   [:a {:href "/tab"} [:p "tab"]]
   ])


