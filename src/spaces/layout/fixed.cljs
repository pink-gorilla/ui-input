(ns spaces.layout.fixed
  (:require
   [spaces.core :as spaces]))

(defn main-top [{:keys [height width scrollable
                        top top-size top-resizeable
                        main]
                 :or {height "100%"
                      width "100%"
                      scrollable false
                      top-size 50
                      top-resizeable true}}]
  ;(println "layout-fixed mt ...")
  [spaces/fixed {:height height :width width}
   (if top-resizeable
     [spaces/top-resizeable {:size top-size :scrollable scrollable} top]
     [spaces/top {:size top-size :scrollable scrollable} top])
   [spaces/fill {:scrollable scrollable}
    main]])

(defn left-right-top [{:keys [height width scrollable
                              top top-size top-resizeable
                              left left-size left-resizeable
                              right]
                       :or {height "100%"
                            width "100%"
                            scrollable false
                            top-size 50
                            top-resizeable true
                            left-size "50%"
                            left-resizeable true}}]
  ;(println "layout-fixed lrt ...")
  [spaces/fixed {:height height :width width}
   (if top-resizeable
     [spaces/top-resizeable {:size top-size} top]
     [spaces/top {:size top-size} top])
   [spaces/fill
    (if left-resizeable
      [spaces/left-resizeable {:size left-size :scrollable scrollable} left]
      [spaces/left {:size left-size :scrollable scrollable} left])
    [spaces/fill {:scrollable scrollable} right]]])

(defn left-right-middle [{:keys [height width scrollable
                                 left left-size left-resizeable
                                 right right-size right-resizeable
                                 middle]
                          :or {height "100%"
                               width "100%"
                               scrollable false
                               left-size "25%"
                               left-resizeable true
                               right-size "25%"
                               right-resizeable true}}]
  ;(println "layout-fixed lrm ...")
  [spaces/fixed {:height height :width width}
   (if left-resizeable
     [spaces/left-resizeable {:size left-size :scrollable scrollable} left]
     [spaces/left {:size left-size :scrollable scrollable} left])
   (if right-resizeable
     [spaces/right-resizeable {:size right-size :scrollable scrollable} right]
     [spaces/right {:size right-size :scrollable scrollable} right])
   [spaces/fill {:scrollable scrollable} middle]])
