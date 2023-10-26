(ns spaces.layout.screen
  (:require
   [spaces.core :as spaces]))

(defn screen-main-top [{:keys [top top-size top-resizeable
                               main main-scrollable]
                        :or {top-size 50
                             top-resizeable true
                             main-scrollable true}}]
  ;(println "layout-viewport mt ...")
  [spaces/viewport
   (if top-resizeable
     [spaces/top-resizeable {:size top-size} top]
     [spaces/top {:size top-size} top])
   [spaces/fill {:scrollable main-scrollable}
    main]])

(defn screen-left-right-top [{:keys [top top-size top-resizeable
                                     left left-size left-resizeable
                                     right]
                              :or {top-size 50
                                   top-resizeable true
                                   left-size "50%"
                                   left-resizeable true}}]
  ;(println "layout-viewport lrt ...")
  [spaces/viewport
   (if top-resizeable
     [spaces/top-resizeable {:size top-size} top]
     [spaces/top {:size top-size} top])
   [spaces/fill
    (if left-resizeable
      [spaces/left-resizeable {:size left-size :scrollable false} left]
      [spaces/left {:size left-size :scrollable false} left])
    [spaces/fill {:scrollable false} right]]])

(defn screen-left-right-middle [{:keys [left left-size left-resizeable
                                        right right-size right-resizeable
                                        middle]
                                 :or {left-size "25%"
                                      right-size "25%"
                                      left-resizeable true
                                      right-resizeable true}}]
   ;(println "layout-viewport lrm ...")
  [spaces/viewport
   (if left-resizeable
     [spaces/left-resizeable {:size left-size} left]
     [spaces/left {:size left-size} left])
   (if right-resizeable
     [spaces/right-resizeable {:size right-size} right]
     [spaces/right {:size right-size} right])
   [spaces/fill {:scrollable false} middle]])
