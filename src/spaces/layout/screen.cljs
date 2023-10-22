(ns spaces.layout.screen
  (:require
   [spaces.core :as spaces]))

(defn screen-main-top [{:keys [top main top-size]
                        :or {top-size 50}}]
  ;(println "layout-viewport mt ...")
  [spaces/viewport
   [spaces/top-resizeable {:size top-size} top]
   [spaces/fill {:scrollable false}
    main]])

(defn screen-left-right-top [{:keys [top left right top-size left-size]
                              :or {top-size 50
                                   left-size "50%"}}]
  ;(println "layout-viewport lrt ...")
  [spaces/viewport
   [spaces/top-resizeable {:size top-size} top]
   [spaces/fill
    [spaces/left-resizeable {:size left-size :scrollable false} left]
    [spaces/fill {:scrollable false} right]]])

(defn screen-left-right-middle [{:keys [left right middle left-size right-size]
                                 :or {left-size "25%"
                                      right-size "25%"}}]
   ;(println "layout-viewport lrm ...")
  [spaces/viewport
   [spaces/left-resizeable {:size left-size} left]
   [spaces/right-resizeable {:size right-size} right]
   [spaces/fill {:scrollable false} middle]])
