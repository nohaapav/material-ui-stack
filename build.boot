(set-env!
 :resource-paths #{"resources"}
 :dependencies '[[cljsjs/boot-cljsjs "0.10.5" :scope "test"]
                 [cljsjs/react "16.8.6-0"]
                 [cljsjs/react-dom "16.8.6-0"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "4.9.0")
(def +version+ (str +lib-version+ "-1"))

(task-options!
 pom {:project     'cljsjs/material-ui-stack
      :version     +version+
      :description "A Set of React Components that Implement Google's Material Design bundled with supporting packages e.g. (Icons, Lab)"
      :url         "http://www.material-ui.com/"
      :scm         {:url "https://github.com/nohaapav/material-ui-stack"}
      :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(deftask package []
  (comp
   (run-commands :commands [["npm" "install" "--include-dev"]
                            ["npm" "run" "build:dev"]
                            ["npm" "run" "build:prod"]
                            ["rm" "-rf" "./node_modules"]])
   (sift :move {#".*material-ui-stack.inc.js"     "cljsjs/material-ui-stack/development/material-ui-stack.inc.js"
                #".*material-ui-stack.min.inc.js" "cljsjs/material-ui-stack/production/material-ui-stack.min.inc.js"})
   (sift :include #{#"^cljsjs"})
   (deps-cljs :foreign-libs [{:file           #"material-ui-stack.inc.js"
                              :file-min       #"material-ui-stack.min.inc.js"
                              :provides       ["@material-ui/core"
                                               "@material-ui/core/styles"
                                               "@material-ui/core/colors"
                                               "@material-ui/lab"
                                               "@material-ui/icons"
                                               "@material-ui/pickers"
                                               "@date-io/date-fns"]
                              :global-exports '{"@material-ui/core"                     MaterialUI
                                                "@material-ui/core/styles"              MaterialUIStyles
                                                "@material-ui/core/colors"              MaterialUIColors
                                                "@material-ui/lab"                      MaterialUILab
                                                "@material-ui/icons"                    MaterialUIIcons
                                                "@material-ui/pickers"                  MaterialUIPickers
                                                "@date-io/date-fns"                     DateFnsUtils}
                              :requires       ["react" "react-dom"]}]
              :externs [#"material-ui-stack.ext.js"])
   (pom)
   (jar)
   (validate-checksums)))
