(ns demo.page.desclist
  (:require
   [container]))

(defn desclist-page [{:keys [route-params query-params handler] :as route}]
  [:div
   [:h2 "description list"]
   [container/description-list
    "Applicant Information"
    "Personal details and application."

    {:name "Margot Foster"
     :position "Backend Developer"
     :email "margotfoster@example.com"
     :salary "$120,000"
     :memo "Fugiat ipsum ipsum deserunt culpa aute sint do nostrud anim incididunt cillum culpa consequat. Excepteur qui ipsum aliquip consequat sint. Sit id mollit nulla mollit nostrud in ea officia proident. Irure nostrud pariatur mollit ad adipisicing reprehenderit deserunt qui eu."}

    {:name "Full name"
     :position "Application for"
     :email "Email address"
     :salary "Salary expectation"
     :memo ""}]])