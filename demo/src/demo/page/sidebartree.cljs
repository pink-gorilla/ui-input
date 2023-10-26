(ns demo.page.sidebartree
  (:require
   [reagent.core :as r]
   [spaces.layout.screen :as layout-viewport]
   [container :refer [sidebar-tree]]))

(defn welcome []
  [:div.bg-red-500.w-full.h-full "welcome"])

(defn fulfillment []
  [:div.bg-green-500.w-full.h-full "fulfillment"])

(defn product []
  [:div.bg-blue-500.w-full.h-full "product"])

(defn finance []
  [:div.bg-blue-500.w-full.h-full "finance"])

(defn menu [goto]
  [sidebar-tree
   {:name "Menu2"
    :items [{:name "Fulfillment" :class "ion-bag" :href "#" :on-click #(goto fulfillment)
             :items [{:name "Derps" :href "#" :class "ion-ios-color-filter-outline"}
                     {:name "Times" :href "#" :class "ion-ios-clock-outline"}
                     {:name "Hates" :href "#" :class "ion-android-star-outline"}
                     {:name "Beat" :href "#" :class "ion-heart-broken"}]}
            {:name "Product" :class "ion-ios-settings" :href "#" :on-click #(goto product)
             :items [{:name "Watch" :href "#" :class "ion-ios-alarm-outline"}
                     {:name "Creeper" :href "#" :class "ion-ios-camera-outline"}
                     {:name "Hate" :href "#" :class "ion-ios-chatboxes-outline"}
                     {:name "Grinder" :href "#" :class "ion-ios-cog-outline"}]}
            {:name "Finance" :class "ion-ios-briefcase-outline" :href "#" :on-click #(goto finance)
             :items [{:name "Burn" :href "#" :class "ion-ios-flame-outline"}
                     {:name "Bulbs" :href "#" :class "ion-ios-lightbulb-outline"}
                     {:name "Where You" :href "#" :class "ion-ios-location-outline"}
                     {:name "On Lock" :href "#" :class "ion-ios-locked-outline" :on-click #(goto welcome)}]}]}])

(defn sidebartree-page [_]
  (let [page-a (r/atom welcome)
        goto (fn [page]
               (println "changing page...")
               (reset! page-a page))]
    (fn []
      [layout-viewport/screen-left-right-top
       {:left [:div.bg-green-300.w-full.h-full [menu goto]]
        :left-resizeable false
        :left-size "17em"
        :right [:div.bg-blue-300.w-full.h-full [@page-a]]
        :top [:div.bg-gray-200.w-full.h-full "top"]
        :top-resizeable false
        :scrollable true}])))
