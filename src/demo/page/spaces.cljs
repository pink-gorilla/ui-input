(ns demo.page.spaces
  (:require [user :refer [add-page]]))

(defn spaces-1 []
  [spaces/viewport
   [spaces/left {:size "20%" :style "blue" :trackSize true}
    [spaces/description "Left"]]
   [spaces/fill {:style "green" :trackSize true}
    [spaces/bottom {:size "20%" :style "red" :trackSize true}]
    [spaces/right {:size "20%" :style "blue" :trackSize true}]]])

(defn spaces-demo []
  [spaces/viewport
   [spaces/top-resizeable {:size 50} "top"]
   [spaces/fill {:class "bg-green-500"}
    [:div
     [spaces/left-resizeable {:size 200} "left"]
     [spaces/right-resizeable {:size 200}
            ;"right"
      [:div
       [spaces/fill {:class "bg-green-500"} "fill"]
       [spaces/bottom-resizeable {:size 50} "bottom"]]]
     [spaces/fill {:class "bg-green-500"}
           ;[spaces-1]
      [:div.bg-red-300.w-full.h-full "filling"]]]]])

(defn spaces-page [{:keys [route-params query-params handler] :as route}]
  [spaces-demo])

(add-page spaces-page :spaces)
