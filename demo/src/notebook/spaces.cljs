(ns notebook.spaces
  (:require
   [spaces.layout.screen :as layout-viewport]
   [spaces.layout.fixed :as layout-fixed]))

(pr-str
 (layout-viewport/screen-left-right-middle
  {:left [:div.bg-green-300.w-full.h-full "left"]
   :right [:div.bg-blue-300.w-full.h-full "right"]
   :middle [:div.bg-gray-200.w-full.h-full "middle"]}))

(pr-str
 (layout-fixed/left-right-middle
  {:left [:div.bg-green-300.w-full.h-full "left"]
   :right [:div.bg-blue-300.w-full.h-full "right"]
   :middle [:div.bg-gray-200.w-full.h-full "middle"]}))

