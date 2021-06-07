(ns pinkgorilla.input.ionslider
  (:require
   [reagent.core :as r]
   ["jquery" :as jq] ; jquery is a dependency, but it needs to be required separately.
   ["react-ion-slider" :as IonRangeSlider]))


; shiny: http://ionden.com/a/plugins/ion.rangeSlider/demo_interactions.html
; https://www.npmjs.com/package/react-ion-slider#1-before-using-react-ion-slider-please-import-module

; type= {} 
; min= {} max= {} 
; from= {} to= {} 
; step= {} 
; values= {} keyboard= {}


(defn ^{:category :control}
  slider-ion [{:keys [on-change value] :as options}]
  (let [range? (vector? value)
        on-change-wrapped (fn [v]
                            (when on-change
                              (let [v-clj (js->clj v)
                                    v2 (if range?
                                         [(get v-clj "from") (get v-clj "to")]
                                         (get v-clj "from"))]
                                ;(println "v: " v-clj)
                                ;(println "v2: " v2)


                                (on-change v2))))
        opts-ion {:on-change on-change-wrapped}
        opts-ion-value (if range?
                         (let [[from to] value]
                           {:from from
                            :to to
                            :type "double"})
                         {:from value
                          :type "single"})
        options (merge options opts-ion opts-ion-value)]
    [:> IonRangeSlider options]
    #_(into [:> IonRangeSlider options]
            (r/children (r/current-component)))))




