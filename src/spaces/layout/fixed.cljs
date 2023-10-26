(ns spaces.layout.fixed
  (:require
   [spaces.core :as spaces]))

(defn main-top [{:keys [top main top-size height width scrollable resizeable-top]
                 :or {top-size 50
                      height "100%"
                      width "100%"
                      scrollable false
                      resizeable-top true}}]
  ;(println "layout-fixed mt ...")
  [spaces/fixed {:height height :width width}
   (if resizeable-top 
     [spaces/top-resizeable {:size top-size :scrollable scrollable} top]
     [spaces/top {:size top-size :scrollable scrollable} top])
   [spaces/fill {:scrollable scrollable}
    main]])

(defn left-right-top [{:keys [top left right top-px left-size height width scrollable resizeable-left resizeable-top]
                       :or {top-px 50
                            left-size "50%"
                            height "100%"
                            width "100%"
                            scrollable false
                            resizeable-top true
                            resizeable-left true}}]
  ;(println "layout-fixed lrt ...")
  [spaces/fixed {:height height :width width}
   (if resizeable-top 
      [spaces/top-resizeable {:size top-px} top]
      [spaces/top {:size top-px} top])
   [spaces/fill
     (if resizeable-left
      [spaces/left-resizeable {:size left-size :scrollable scrollable} left]
      [spaces/left {:size left-size :scrollable scrollable} left])
    [spaces/fill {:scrollable scrollable} right]]])

(defn left-right-middle [{:keys [left right middle left-size right-size height width scrollable
                                 resizeable-left resizeable-right]
                          :or {left-size "25%"
                               right-size "25%"
                               height "100%"
                               width "100%"
                               scrollable false
                               resizeable-left true
                               resizeable-right true}}]
  ;(println "layout-fixed lrm ...")
  [spaces/fixed {:height height :width width}
   (if resizeable-left
     [spaces/left-resizeable {:size left-size :scrollable scrollable} left]  
     [spaces/left {:size left-size :scrollable scrollable} left])
   (if resizeable-right
     [spaces/right-resizeable {:size right-size :scrollable scrollable} right]
     [spaces/right {:size right-size :scrollable scrollable} right])
   [spaces/fill {:scrollable scrollable} middle]])
