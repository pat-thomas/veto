##Example usage:

```clj
(ns my-ns
  (:require [veto.core :refer [make-type needs-keys uses-type]]))

(make-type person
  {:demographic (needs-keys :first-name :last-name)
   :account     (needs-keys :email)})

(defn normalized-name
  [person-data]
  (uses-type person
    (str (get-in person-data [:demographic :last-name]) ", " (get-in person-data [:demographic :last-name]))))

;; the following form will not compile
(defn full-name
  [person-data]
  (uses-type person
    (str (get-in person-data [:demographic :last-name]) ", " (get-in person-data [:demographic :last-name]) (get-in person-data [:demographic :middle-name]))))
```
