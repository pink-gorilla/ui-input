(ns demo.page.flexlayout
  (:require
   [ui.flexlayout :refer [layout]]))

(def model
  {:global {:tabEnableRename false
            :tabEnableClose false}
   :layout {:type "row"
            :weight 100
            :children [{:type "tabset"
                        :weight 50
                        :children [{:type "tab"
                                    :name "One"
                                    :component "button"}]}
                       {:type "tabset"
                        :weight 100
                        :selected 0
                        :children
                        [{:type "tab"
                          :name "One"
                          :component "panel"}
                         {:type "tab"
                          :name "two"
                          :component "panel"}
                         {:type "tab"
                          :name "3"
                          :component "panel"}
                         {:type "tab"
                          :name "5"
                          :component "panel"}
                         {:type "tab"
                          :name "4"
                          :component "panel"}]}]}
   :borders [{:type "border"
              ;:selected 13,
              :size 188,
              :location "left"
              :children [{:type "tab",
                          :id "#1",
                          :name "Activity Blotter",
                          :component "grid",
                          :enableClose false}]}]})

(defn flex-layout-page [{:keys [route-params query-params handler] :as route}]
  [:div.h-screen.w-screen.bg-blue-100.
   [layout model]])

