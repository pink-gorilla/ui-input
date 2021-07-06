(ns pinkgorilla.layout.tab
  (:require
   [reagent.core :as r]
   [pinkie.box :refer [apply-style]]))

(defn- tab-menu [{:keys [active select-page]
                  :or {active 0}} tabs]
  [:div {:style {:border-bottom "2px solid #eaeaea"}}
   (into [:ul.flex.cursor-pointer]
         (map-indexed (fn [i v]
                        [:li.py-2.px-6.rounded-t-lg
                         {:class (if (= active i)
                                   "bg-blue-200"
                                   "bg-white")
                          :on-click #(select-page i)}
                         (first v)]) tabs))])

(defn tab [& args]
  (let [active (r/atom 0)]
    (fn [& args]
      (let [[props children] (if (map? (first args))
                               [(first args) (rest args)]
                               [{} args])
            tabs (partition 2 children)]
        ;(println "tabs: " tabs "props: " props)
        [:div (apply-style props)
         [tab-menu {:active @active
                    :select-page (fn [i]
                                   ;(println "selected index: " i)
                                   (reset! active i))}
          tabs]
         (let [page (nth tabs @active)]
           (if page
             (second page)
             [:div "no tab selected page:" @active]))]))))