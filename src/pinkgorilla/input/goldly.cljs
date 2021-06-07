(ns pinkgorilla.input.goldly
  (:require
   [re-frame.core :as rf]

   ; pinkie
   [pinkie.default-setup] ; side-effects
   [pinkie.pinkie :refer-macros [register-component]]

   ; helper fns
   [pinkgorilla.input.css :as css]
   [pinkgorilla.input.bind :refer [bind]]

    ; input
   [pinkgorilla.input.button :refer [button]]
   [pinkgorilla.input.checkbox :refer [checkbox]]
   [pinkgorilla.input.input :refer [input]]
   [pinkgorilla.input.select :refer [select-nav]]
   ;[pinkgorilla.input.slider :refer [slider]]
   [pinkgorilla.input.ionslider :refer [slider-ion]]
   [pinkgorilla.input.progressbar :refer [progressbar]]

   ; layout
   [pinkgorilla.layout.sidebar :refer [sidebar]]
   [pinkgorilla.layout.gridlayout :refer [gridlayout]]
   [pinkgorilla.layout.panel :refer [panel]]
   [pinkgorilla.layout.tailsui :refer [tab tabs]]
   [pinkgorilla.layout.popover :refer [tooltip popover]]
   [pinkgorilla.layout.description-list :refer [description-list]]

;   
   ))

(rf/dispatch [:css/add-components css/components css/config])


; controls
(register-component :p/button button)

; text
(register-component :p/input input)
(def input-a (bind input))
(register-component :p/input-a input-a)

(def checkbox-a (bind checkbox))
(register-component :p/checkbox checkbox-a)


(def select-a (bind select-nav))
(register-component :p/select select-a)

;(register-component :p/slider slider)

(def slider-ion-a (bind slider-ion))
(register-component :p/slider slider-ion-a)

(register-component :p/progressbar progressbar)


; layout

(register-component :p/gridlayout gridlayout)
(register-component :p/panel panel)
(register-component :p/sidebar sidebar)

(register-component :p/tab tab)
(register-component :p/tabs tabs)

(register-component :p/tooltip tooltip)
(register-component :p/popover popover)

(register-component :p/description-list description-list)
