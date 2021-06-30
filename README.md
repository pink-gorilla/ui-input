# ui-input [![GitHub Actions status |pink-gorilla/ui-binary-clock](https://github.com/pink-gorilla/ui-input/workflows/CI/badge.svg)](https://github.com/pink-gorilla/ui-input/actions?workflow=CI)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/ui-input.svg)](https://clojars.org/org.pinkgorilla/ui-input)

Input controls:
- text
- checkbox
- button
- slider
- select

## Demo

```
clojure -X:goldly
```

Navigate your webbrowser to port 8000. 
Snippets are in `running systems` / `snippet-registry`

## In Goldly as a ui extension

In deps.edn add quil as dependency and add goldly alias

```
:goldly
  {:extra-deps {org.pinkgorilla/goldly {:mvn/version "RELEASE"}
               {org.pinkgorilla/ui-input {:mvn/version "0.0.2"}}
   :exec-fn goldly-server.app/goldly-server-run!
   :exec-args {:profile "watch"
               :config {:goldly {}}}}
```




