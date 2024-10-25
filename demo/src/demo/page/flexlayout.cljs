(ns demo.page.flexlayout
  (:require
   [ui.flexlayout :refer [create-model layout add-node]]))

(def model
  {:global {:tabEnableRename false
            :tabEnableClose false
            :tabEnableFloat true
            :tabSetEnableActiveIcon true
            }
   :layout {:type "row"
            :weight 100
            :children [{:type "tabset"
                        :weight 50
                        :children [{:type "tab"
                                    :name "One"
                                    :component "button"
                                    :enableClose false,
                                    :icon "/r/images/bar_chart.svg"
                                    }]}
                       {:type "tabset"
                        :weight 100
                        :selected 1
                        :children
                        [{:type "tab"
                          :name "One"
                          :component "url"
                          :icon "/r/images/add.svg"
                          :helpText "this tab has helpText defined"
                          :id "50"}
                         {:type "tab"
                          :name "two"
                          :component "panel"
                          :icon "/r/images/article.svg"
                          :id "100"
                          }
                         {:type "tab"
                          :name "3"
                          :component "panel"}
                         {:type "tab"
                          :name "5"
                          :component "panel"}
                         {:type "tab"
                          :name "4"
                          :component "panel"}]}]}
   :borders [{:type "border"
              ;:selected 13,
              :size 188,
              :location "left"
              :children [{:type "tab",
                          :id "#1",
                          :name "Options",
                          :component "option",
                          :enableClose false}]}]})

(def m (create-model
       {:model model
        :options {"50" "https://en.wikipedia.org/wiki/Main_Page"
                  "100" 10000
                  }}))

(set! (.-model js/window) m)

(defn flex-layout-page [{:keys [route-params query-params handler] :as route}]
  [:div.h-screen.w-screen
   {:style {:display "flex"
            :flex-direction "column"
            :flex-grow 1}}
     [:div {:dir "ltr"
                  :style {:margin "2px"
                          :display "flex"
                          :align-items "center"
                          }}
     [:button
      {:on-click #(add-node m {:component "grid"
                               :icon "/r/images/article.svg",
                               :name "Grid-added"})
       :style {:border-radius "5px"
               :border "1px solid lightgray"}}
      "add node"
      ]
      
      [:button
       {:on-click #(add-node m {:component "url"
                                :icon "/r/images/article.svg",
                                :name "duck"
                                :options "https://kibot.com"
                                :id "duck1"
                                })
        :style {:border-radius "5px"
                :border "1px solid lightgray"}}
       "add duck"]
      
      ]
   [:div {:style {:display "flex"
                  :flex-grow "1"
                  :position "relative"
                  :border "1px solid #ddd"}}
    [layout m]
    ]
   ])


 ; if (node.getExtraData().data == null) {
; // create data in node extra data first time accessed
;                node.getExtraData().data = this.makeFakeData();
;            }

 ;const config = node.getConfig();
;                ;if (config.type === "url") {
;                    return <iframe title={node.getId()} src={config.data} style={{ display: "block", border: "none", boxSizing: "border-box" }} width="100%" height="100%" />;
;                } else if (config.type === "html") {
;                    return (<div dangerouslySetInnerHTML={{ __html: config.data }} />);
;                } else if (config.type === "text") {
;                    return (
;                        <textarea style={{ position: "absolute", width: "100%", height: "100%", resize: "none", boxSizing: "border-box", border: "none" }}
;                            defaultValue={config.data}
;                        />);
;                }