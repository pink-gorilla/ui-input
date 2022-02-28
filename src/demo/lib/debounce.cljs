
(defn save-input! [input & args]
  (js/console.log "Saving input: " input))

;; note how we use def instead of defn
(def save-input-debounced!
  (debounce save-input! 1000))

(save-input-debounced! "he")
(save-input-debounced! "hello")
(save-input-debounced! "hello, ")
(save-input-debounced! "hello, world")
(save-input-debounced! "hello, world!")

