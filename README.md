# cljsjs/material-ui-stack

Clojure package for Material UI bundled with supporting packages e.g. (Icons, Lab, Pickers)

https://material-ui.com

## Abstract

Current cljsjs material-ui related packages have dependency on core module which leads to several instances of 
`@material-ui/styles` if more than one is used. See [styles propagation issues](https://material-ui.com/getting-started/faq/#i-have-several-instances-of-styles-on-the-page)

To prevent such behaviour you can either use clojurescript with [webpack](https://clojurescript.org/guides/webpack)
or bundle these packages together in a single webpack module in separate clojure package.

## Packages

* @material-ui/core [4.9.0]
  - @material-ui/core/styles
  - @material-ui/core/colors
* @material-ui/lab [4.0.0-alpha.42]
* @material-ui/icons [4.9.1]
* @material-ui/pickers [4.0.0-alpha.1]
* @date-io/date-fns [2.4.0] (default pickers provider)

## Setup

To use cljsjs package from local project repo do following:

1. In `project.clj` setup local repository
```
:repositories {"local" "file:repo"}
```

2. Build package
```
boot package install target
```

3. Deploy `.jar` archive to local project repo e.g.
```
mvn deploy:deploy-file -Dfile=material-ui-stack-4.9.0-1.jar -DartifactId=material-ui-stack -Dversion=4.9.0-1 -DgroupId=cljsjs -Dpackaging=jar -Durl=file:repo
```

4. Finally add dependency to your maven repository
```
lein deps
```

## Usage

[](dependency)
```clojure
[cljsjs/material-ui-stack "4.9.0-1"]
```
[](/dependency)

This jar comes with `deps.cljs` as used by the [Foreign Libs][flibs] feature
of the ClojureScript compiler. After adding the above dependency to your project
you can require the packaged library like so:

```clojure
(ns application.core
  (:require ["@material-ui/core"]
            ["@material-ui/icons"]
            ["@material-ui/pickers"]))
```

[flibs]: https://clojurescript.org/reference/packaging-foreign-deps

