(ns pinkgorilla.input.progressbar)

; stolen from:
; https://www.creative-tim.com/learning-lab/tailwind-starter-kit/documentation/css/progressbars

(defn ^{:category :control}
  progressbar [percent]
  [:div {:class "relative pt-1"}
   [:div {:class "overflow-hidden h-2 mb-4 text-xs flex rounded bg-pink-200"}
    [:div {:style {:width (str percent "%")}
           :class "shadow-none flex flex-col text-center whitespace-nowrap text-white justify-center bg-pink-500"}]]])



