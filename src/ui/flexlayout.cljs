(ns ui.flexlayout
  (:require
   [reagent.core :as r]
   [reagent.ratom :as ratom]
   ["flexlayout-react" :refer [Layout Model]]))

;; https://www.npmjs.com/package/flexlayout-react
;; 20k weekly downloads from npm

;; see:

;; official? demo
;; https://github.com/caplin/FlexLayout/blob/master/examples/demo/App.tsx

;; clojurescript example
;; https://github.com/dundalek/daba/blob/master/components/core/src/io/github/dundalek/daba/ui/viewers/root_docking.cljs

;; https://github.com/openworm/geppetto-client/tree/development/geppetto-ui/src/flex-layout/src

;(let [^js tabset-node
  ;        (tabset-with-most-children model)
  ;                  ; (.getActiveTabset ^js model)
  ;                  ; (.getFirstTabSet ^js model)
 ;
 ;         node #js {:id cell-id
 ;                   :type "tab"}
 ;         add-node-action (FlexLayout.Actions.addNode node (.getId tabset-node) (.-CENTER FlexLayout.DockLocation) -1)]
 ;     (.doAction ^js model add-node-action)))))

; cell-id (.getId node)

;  attributeDefinitions.add("config", undefined).setType(Attribute.JSON);

(defn tab-button-title [{:keys [cell-id]}]
  [:span (str cell-id)])

(def title-factory (fn [^js node]
                     (let [cell-id (.getId node)]
                       (r/as-element
                        [tab-button-title {;:!value (ratom/make-reaction
                                       ;         (fn [] cell-id))
                                           :cell-id cell-id}]))))

;; model.addChangeListener(onModelChange);
;;  model.removeChangeListener(onModelChange);
; doAction
; model.getActiveTabset

;; model.toJson ()

;;  const jsonText = JSON.stringify(model.toJson (), null, "\t");}

;; actions
; ADD_NODE
; ADJUST_BORDER_SPLIT
; ADJUST_WEIGHTS
; CLOSE_WINDOW
; CREATE_WINDOW
; DELETE_TAB
; DELETE_TABSET
; MAXIMIZE_TOGGLE
; MOVE_NODE
; POPOUT_TAB
; POPOUT_TABSET
; RENAME_TAB
; SELECT_TAB
; SET_ACTIVE_TABSET
; UPDATE_MODEL_ATTRIBUTES

(def handle-action (fn [^js action]
                    ; (if (= FlexLayout.Actions.DELETE_TAB (.-type action))
                    ;   (let [cell-id (-> action .-data .-node)]
                    ;     (println "cell deleted: " cell-id)   
                    ;     js/undefined)
                    ;   action
                     action))

(defn add-node [{:keys [layout option-a]} {:keys [id options] :as node}]
  (when (and layout @layout)
    (println "new node: " id " options: " options)
    (when (and options id)
      (swap! option-a assoc id options))
    (println "adding new node to layout..")
    (.addTabToActiveTabSet
     @layout
     (clj->js node))))

(defmulti component-ui  (fn [e] (:component e)))

(defmethod component-ui :default [{:keys [component
                                          id
                                          options]}]
  [:div "I am node id: " (str id)
   "component: " component
   "options: " (pr-str options)])

(defmethod component-ui "url" [{:keys [id options]}]
  ;[:div.bg-blue-100 "url: " (str options) "..."
  [:iframe {:title id
            :src options
            :style {:display "block"
                    :border "none"
                    :boxSizing "border-box"}
            :width "100%"
            :height "100%"}]
;   ]
  )

(defmethod component-ui "panel" [{:keys [id options]}]
  [:div.bg-red-300.w-full.h-full
   "I am panel id: " (str id)
   "options: " (pr-str options)])

(defn make-factory [option-a]
  (fn [^js node]
    (let [cell-id (.getId node)
          component (.getComponent node)
          option (get @option-a cell-id)
          comp (component-ui {:options option
                              :id cell-id
                              :component component})]
      (println "cell: " cell-id "option: " option)
      (r/as-element
       comp
        ;[cell-panel {:!value (ratom/make-reaction
        ;                       (fn [] (get @!cells cell-id)))
        ;             :cell-id cell-id}]
       ))))
;node. getConfig():

(defn create-model [{:keys [model options]
                     :or {options {}}}]
  (let [layout (clojure.core/atom nil)
        option-a (clojure.core/atom options)]
    {:model (-> model
                clj->js
                (Model.fromJson))
     :layout layout ; react-ref goes here
     :ref (fn [el]
            (reset! layout el))
     :option-a option-a
     :factory (make-factory option-a)
     :titleFactory title-factory
     :onAction handle-action}))

(defn layout
  "The model is tree of Node objects that define the structure of the layout.
   The factory is a function that takes a Node object and returns a React componen"
  [spec]
  [:> Layout spec])

