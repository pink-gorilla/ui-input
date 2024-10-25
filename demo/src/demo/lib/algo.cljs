(ns demo.lib.algo
  (:require
   [reagent.core :as r]
   [options.core :refer [options-ui]]
   [ui.flexlayout :refer [component-ui]]
   ))

(def state (r/atom {}))

(def config
  {:state state
   :options [{:type :select
              :path [0 :asset],
              :name "asset",
              :spec
              ["EUR/USD" "USD/CHF" "GBP/USD" "USD/SEK" "USD/NOK" "USD/CAD" "USD/JPY"
               "AUD/USD" "NZD/USD" "USD/MXN" "USD/ZAR" "EUR/JPY" "EUR/CHF" "EUR/GBP" "GBP/JPY"]}
             {:type :select :path [2 :trailing-n], :name "DailyLoad#", :spec [2 5 10 20 30 50 80 100 120 150]}
             {:type :select :path [2 :atr-n], :name "dATR#", :spec [5 10 20 30]}
             {:type :select :path [2 :percentile], :name "dPercentile", :spec [10 20 30 40 50 60 70 80 90]}
             {:type :select :path [2 :step], :name "dStep", :spec [0.001 1.0E-4 4.0E-5]}
             {:type :select :path [4 :max-open-close-over-low-high], :name "doji-co/lh max", :spec [0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9]}],
   :current
   {[0 :asset] "USD/JPY",
    [2 :trailing-n] 120,
    [2 :atr-n] 10,
    [2 :percentile] 70,
    [2 :step] 1.0E-4,
    [4 :max-open-close-over-low-high] 0.3}})



(defmethod component-ui "algo" [{:keys [id options]}]
  [options-ui {:class "bg-blue-300 options-debug"
               :style {:width "50vw"
                           ;:height "40vh"
                       }}config])