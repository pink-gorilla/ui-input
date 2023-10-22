(ns spaces.layout.fixed
  (:require
   [spaces.core :as spaces]))

(defn main-top [{:keys [top main top-size height width]
                 :or {top-size 50
                      height "100%"
                      width "100%"}}]
  ;(println "layout-fixed mt ...")
  [spaces/fixed {:height height :width width}
   [spaces/top-resizeable {:size top-size} top]
   [spaces/fill {:scrollable false}
    main]])

(defn left-right-top [{:keys [top left right top-px left-size height width]
                       :or {top-px 50
                            left-size "50%"
                            height "100%"
                            width "100%"}}]
  ;(println "layout-fixed lrt ...")
  [spaces/fixed {:height height :width width}
   [spaces/top-resizeable {:size top-px} top]
   [spaces/fill
    [spaces/left-resizeable {:size left-size :scrollable false} left]
    [spaces/fill {:scrollable false} right]]])

(defn left-right-middle [{:keys [left right middle left-size right-size height width]
                          :or {left-size "25%"
                               right-size "25%"
                               height "100%"
                               width "100%"}}]
  ;(println "layout-fixed lrm ...")
  [spaces/fixed {:height height :width width}
   [spaces/left-resizeable {:size left-size} left]
   [spaces/right-resizeable {:size right-size} right]
   [spaces/fill {:scrollable false} middle]])
