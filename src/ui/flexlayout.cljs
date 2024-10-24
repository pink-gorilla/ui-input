(ns ui.flexlayout
  (:require
   [reagent.core :as r]
   [reagent.ratom :as ratom]
   ["flexlayout-react" :refer [Layout Model]]))

;; https://www.npmjs.com/package/flexlayout-react
;; 20k weekly downloads from npm
; https://github.com/caplin/FlexLayout/blob/master/examples/demo/App.tsx

;; see:
;; https://github.com/dundalek/daba/blob/master/components/core/src/io/github/dundalek/daba/ui/viewers/root_docking.cljs

(defn create-model [model]
  (-> model
      clj->js
      (Model.fromJson)))

(def factory (fn [^js node]
               (let [cell-id (.getId node)]
                 (r/as-element
        ;[cell-panel {:!value (ratom/make-reaction
        ;                       (fn [] (get @!cells cell-id)))
        ;             :cell-id cell-id}]
                  [:div "I am cell: " (str cell-id)]))))

(def handle-action (fn [^js action]
                    ; (if (= FlexLayout.Actions.DELETE_TAB (.-type action))
                    ;   (let [cell-id (-> action .-data .-node)]
                    ;     (println "cell deleted: " cell-id)   
                    ;     js/undefined)
                    ;   action
                     action))

;(let [^js tabset-node
  ;        (tabset-with-most-children model)
  ;                  ; (.getActiveTabset ^js model)
  ;                  ; (.getFirstTabSet ^js model)
 ;
 ;         node #js {:id cell-id
 ;                   :type "tab"}
 ;         add-node-action (FlexLayout.Actions.addNode node (.getId tabset-node) (.-CENTER FlexLayout.DockLocation) -1)]
 ;     (.doAction ^js model add-node-action)))))

(defn tab-button-title [{:keys [cell-id]}]
  [:span (str cell-id)])

(def title-factory (fn [^js node]
                     (let [cell-id (.getId node)]
                       (r/as-element
                        [tab-button-title {;:!value (ratom/make-reaction
                                       ;         (fn [] cell-id))
                                           :cell-id cell-id}]))))

(defn layout
  "The model is tree of Node objects that define the structure of the layout.
   The factory is a function that takes a Node object and returns a React componen
   "
  [model]
  [:> Layout
   {:model (create-model model)
    :factory factory
    :titleFactory title-factory
    :onAction handle-action}])

