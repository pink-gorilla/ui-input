(ns ui.flexlayout
  (:require
   [reagent.core :as r]
   [reagent.ratom :as ratom]
   ["flexlayout-react" :refer [Layout Model Actions]]
   [options.core :as oui]))

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

(defn make-handle-action [{:keys [selected-id-a] :as state}]
  (fn handle-action [^js action]
                     (when (= Actions.SELECT_TAB (.-type action))
                       (let [cell-id (-> action .-data .-tabNode)]
                         (println "selected tab: " cell-id)    
                         (reset! selected-id-a cell-id)
                         js/undefined
                       ))
                    ; (if (= FlexLayout.Actions.DELETE_TAB (.-type action))
                    ;   (let [cell-id (-> action .-data .-node)]
                    ;     (println "cell deleted: " cell-id)   
                    ;     js/undefined)
                    ;   action
                     action))

(defn add-node [{:keys [layout option-a edit-a]} {:keys [id options edit] :as node}]
  (when (and layout @layout)
    (println "new node: " id " options: " options)
    (when (and options id)
      (when edit
        (swap! edit-a assoc id edit))
      (swap! option-a assoc id options))
    (println "adding new node to layout..")
    (.addTabToActiveTabSet
     @layout
     (clj->js node))))

(defmulti component-ui  (fn [e] (:component e)))

(defn subscribe-options [option-a cell-id]
  ;(ratom/cursor option-a cell-id)
  (ratom/make-reaction
   (fn [] (get @option-a cell-id))))



(defn make-factory [{:keys [option-a] :as state}]
  (fn [^js node]
    (let [cell-id (.getId node)
          component (.getComponent node)
          comp (component-ui {:id cell-id
                              :component component
                              :state state})]
      (println "creating cell: " cell-id)
      (r/as-element
       [comp @(subscribe-options option-a cell-id)]
        ;[cell-panel {:!value)
        ;             :cell-id cell-id}]
       ))))

;node. getConfig():

(defn create-model [{:keys [model options]
                     :or {options {}}}]
  (let [layout (clojure.core/atom nil)
        state {:layout layout ; react-ref goes here
               :model model
               :option-a (reagent.core/atom options)
               :edit-a (reagent.core/atom {})
               :selected-id-a (reagent.core/atom nil)
               }]
    state
    ))

(defn layout
  "The model is tree of Node objects that define the structure of the layout.
   The factory is a function that takes a Node object and returns a React componen"
  [{:keys [layout model] :as state}]
  [:> Layout {:model (-> model
                         clj->js
                         (Model.fromJson))
              :ref (fn [el]
                     (reset! layout el))
              :factory (make-factory state)
              :titleFactory title-factory
              :onAction (make-handle-action state)}])

;; DEFAULT UI COMPONENTS

(defmethod component-ui :default [{:keys [component id]}]
  (fn [options]
    [:div "I am node id: " (str id)
     "component: " component
     "options: " (pr-str options)]))

(defmethod component-ui "url" [{:keys [id]}]
  ;[:div.bg-blue-100 "url: " (str options) "..."
  (fn [options]
    [:iframe {:title id
              :src options
              :style {:display "block"
                      :border "none"
                      :boxSizing "border-box"}
              :width "100%"
              :height "100%"}]))

(defmethod component-ui "panel" [{:keys [id]}]
  (fn [options]
    [:div.bg-red-300.w-full.h-full
     "I am panel id: " (str id)
     "options: " (pr-str options)]))


(defn options-ui [{:keys [class style
                          state 
                          edit
                          ]}] 
    (into [:div {:style style
                 :class class}]
          (map #(oui/create-edit-element state %) edit)))

(defn subscribe-selected-options [{:keys [option-a selected-id-a] :as state}]
  ;(ratom/cursor option-a cell-id)
  (ratom/make-reaction
   (fn [] (get @option-a @selected-id-a))))

(defn subscribe-selected-edit [{:keys [edit-a selected-id-a] :as state}]
  ;(ratom/cursor option-a cell-id)
  (ratom/make-reaction
   (fn [] (get @edit-a @selected-id-a))))

(defmethod component-ui "option" [{:keys [id state]}]
  (let [selected-id-a (:selected-id-a state)
        selected-options-a (subscribe-selected-options state)
        selected-edit-a (subscribe-selected-edit state)
        ]
    (fn [options]
      [:div.bg-blue-500.w-full.h-full
       [:p "option-ui"]
       [:br]
       [:p "selected cell: " @selected-id-a]
       [:br]
       [:p "selected options: " (pr-str @selected-options-a)]
       (if (and @selected-edit-a @selected-options-a)
        [options-ui {:edit @selected-edit-a
                     :state selected-options-a}] 
        [:p.bg-red-500 
          "this component does not have a edit-spec"])])))