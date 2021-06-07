(ns pinkgorilla.layout.tailsui
  (:require
   [reagent.core :as r]
   ["tails-ui" :refer [Button Tab Tabs]]))

#_(defn ^{:category :control} button1 []
    [:<>
     [:link {:rel "stylesheet" :href css}]
     [:p "tails ui button:"]
     [:> Button {:color "blue"
                 :type "submit"
                 :fullWidth true
                 :outline true}

      "Submit"]])

#_(register-component :p/button1 button1)

;; tabs
;; tails-ui-tabs are a little complicated as they expect react 
;; components. We don't want the :> syntax in gorilla-ui
;; Our implementation does not really need :p/tab tag as we
;; replace the children with instantiated react :> Tab
;; anyhow. But for some strange reason, we need the :p/tab
;; definition anyhow. Not sure why
;; see:
;; https://presumably.de/reagent-mysteries-part-4-children-and-other-props.html
;; 
(defn ^{:category :layout}
  tab [options]
  (into [:> Tab options]
        (r/children (r/current-component))))

(defn ^{:category :layout}
  tabs [& children]
  [:<>
   (into [:> Tabs]
         (map (fn [child]
                (into [:> Tab] (rest child))) children))])



