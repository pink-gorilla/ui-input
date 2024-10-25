(ns ui.flexlayout
  (:require
   [reagent.core :as r]
   [reagent.ratom :as ratom]
   ["flexlayout-react" :refer [Layout Model Actions  TabSetNode]]
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
        js/undefined))
                    ; (if (= FlexLayout.Actions.DELETE_TAB (.-type action))
                    ;   (let [cell-id (-> action .-data .-node)]
                    ;     (println "cell deleted: " cell-id)   
                    ;     js/undefined)
                    ;   action
    action))

(defn add-node [{:keys [layout option-a edit-a model-a]} {:keys [id options edit] :as node}]
  (when (and layout @layout)
    (println "new node: " id " options: " options)
    (when (and options id)
      (when edit
        (swap! edit-a assoc id edit))
      (swap! option-a assoc id options))
    (println "adding new node to layout..")
    (let [tabset (or (.getActiveTabset  ^Model @model-a)
                     (.getFirstTabSet  ^Model @model-a))]
      #_(.addTabToActiveTabSet
         ^Model
         @layout
         (clj->js node))

      (.addTabToTabSet
       ^Model
       @layout
       (.getId ^TabSetNode tabset)
       (clj->js node)))))

(defmulti component-ui  (fn [e] (:component e)))

(defn subscribe-options [option-a cell-id]
  ;(ratom/cursor option-a cell-id)
  (ratom/make-reaction
   (fn [] (get @option-a cell-id))))

(defn component-panel [comp options-a]
  [comp @options-a])

(defn make-factory [{:keys [option-a] :as state}]
  (fn [^js node]
    (let [cell-id (.getId node)
          component (.getComponent node)
          comp (component-ui {:id cell-id
                              :component component
                              :state state})
          options-for-component (subscribe-options option-a cell-id)]
      (println "creating component " component " id: " cell-id)
      (r/as-element [component-panel comp options-for-component])
      ;(r/as-element (component-panel comp options-for-component))
      )))

;node. getConfig():

(defn create-model [{:keys [model options edit]
                     :or {options {}
                          edit {}}}]
  (let [layout (clojure.core/atom nil)
        model-a (clojure.core/atom nil)
        state {:layout layout ; react-ref goes here
               :model model
               :model-a model-a
               :option-a (reagent.core/atom options)
               :edit-a (reagent.core/atom edit)
               :selected-id-a (reagent.core/atom nil)}]
    state))

(defn get-data [{:keys [model-a edit-a option-a]}]
  {:model (js->clj (.toJson ^Model @model-a))
   :options @option-a
   :edit @edit-a})

(defn layout
  "The model is tree of Node objects that define the structure of the layout.
   The factory is a function that takes a Node object and returns a React componen"
  [{:keys [layout model] :as state}]
  (let [model (-> model
                  clj->js
                  (Model.fromJson))]
    (reset! (:model-a state) model)
    [:> Layout {:model model
                :ref (fn [el]
                       (reset! layout el))
                :factory (make-factory state)
                :titleFactory title-factory
                :onAction (make-handle-action state)}]))

;; DEFAULT UI COMPONENTS

;; COMPONENT: DEFAULT-UI

(defmethod component-ui :default [{:keys [component id]}]
  (fn [options]
    [:div.bg-red-500
     "Error: no component-ui known for component: " component
     "I am node id: " (str id)
     "options: " (pr-str options)]))

;; COMPONENT: URL

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

(defmethod component-ui "text" [{:keys [id]}]
  ;[:div.bg-blue-100 "url: " (str options) "..."
  (fn [options]
    [:textarea {:title id
                :defaultValue options
                :style {:position "absolute"
                        :width "100%"
                        :height "100%"
                        :resize "none"
                        :boxSizing "border-box"
                        :border "none"}}]))

;; COMPONENT: OPTION-UI 

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
        option-a (:option-a state)]
    (fn [options]
      [:div.bg-blue-500.w-full.h-full
       [:p "option-ui"]
       [:br]
       [:p "selected cell: " @selected-id-a]
       [:br]
       (if (and @selected-edit-a @selected-options-a)
         [oui/options-ui2 {:class "options-label-left"
                           :edit @selected-edit-a
                           :state selected-options-a
                           :set-fn (fn [path v]
                                     (if option-a
                                       (swap! option-a assoc-in [@selected-id-a path] v)
                                       (println "cannot set options .. option-a is nil")))}]
         [:div.bg-red-500
          "this component does not have a edit-spec"
          [:hr]
          (pr-str @selected-options-a)])])))