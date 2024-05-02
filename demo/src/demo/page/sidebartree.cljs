(ns demo.page.sidebartree
  (:require
   [reagent.core :as r]
   [ui.site.ipsum :refer [random-paragraph]]
   [container :refer [with-sidebar-menu]]))

(defn welcome []
  [:div.bg-red-500.w-full.h-full "welcome"])

(defn fulfillment []
  [:div.bg-green-500.w-full.h-full 
   [:div {:style {;:overflow-x "scroll"
                  ;:overflow-y "scroll"
                  }
          :class "w-full h-full bg-green-500"
          }
    "fulfillment"
    [:div.w-full.h-full 
      [random-paragraph 50] 
     ]
    ]]
  )

(defn product []
  [:div.bg-blue-500.w-full.h-full "product"])

(defn finance []
  [:div.bg-blue-500.w-full.h-full "finance"])

(defn menu [page goto]
  [with-sidebar-menu
   {:page [page]
    :name "Menu2"
    :items [{:name "Fulfillment" :class "ion-bag" :href "#" 
             :items [{:name "Fulfillment1" :href "#" :class "ion-ios-color-filter-outline"
                      :on-click #(goto fulfillment)}
                     {:name "Times" :href "#" :class "ion-ios-clock-outline"}
                     {:name "Hates" :href "#" :class "ion-android-star-outline"}
                     {:name "Beat" :href "#" :class "ion-heart-broken"}]}
            {:name "Product" :class "ion-ios-settings" :href "#" 
             :items [{:name "2" :href "#" :class "ion-ios-alarm-outline"
                      :on-click #(goto product)
                      }
                     {:name "Creeper" :href "#" :class "ion-ios-camera-outline"}
                     {:name "Hate" :href "#" :class "ion-ios-chatboxes-outline"}
                     {:name "Grinder" :href "#" :class "ion-ios-cog-outline"}]}
            {:name "Finance" :class "ion-ios-briefcase-outline" :href "#" 
             :items [{:name "3" :href "#" :class "ion-ios-flame-outline"
                      :on-click #(goto finance)
                      }
                     {:name "Bulbs" :href "#" :class "ion-ios-lightbulb-outline"}
                     {:name "Where You" :href "#" :class "ion-ios-location-outline"}
                     {:name "On Lock" :href "#" :class "ion-ios-locked-outline" :on-click #(goto welcome)}]}]}])

(defn sidebar-page [_]
  (let [page-a (r/atom welcome)
        goto (fn [page]
               (println "changing page...")
               (reset! page-a page))]
    (fn [_]
      [menu @page-a goto]

      )))
