(ns pinkgorilla.layout.spaces
  (:require
   ["react-spaces" :as spaces]))

(defn wrap [c]
  (fn [opts & [child]]
    [:> c opts child]))

(def viewport (wrap spaces/ViewPort))

(def fill (wrap spaces/Fill))
(def description (wrap spaces/Description))

(def left (wrap spaces/Left))
(def right (wrap spaces/Right))
(def top (wrap spaces/Top))
(def bottom (wrap spaces/Bottom))

(def left-resizeable (wrap spaces/LeftResizable))
(def right-resizeable (wrap spaces/RightResizable))
(def top-resizeable (wrap spaces/TopResizable))
(def bottom-resizeable (wrap spaces/BottomResizable))

(def centered-vertically (wrap spaces/CenteredVertically))

