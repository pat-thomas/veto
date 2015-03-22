(ns veto.core
  (:require [clojure.walk :as walk]))

(def ^{:private true} type-registry (atom {}))
(def ^{:dynamic true} *current-type* nil)

(defn needs-keys
  [& ks]
  (reduce (fn [acc k]
            (assoc acc k nil))
          {}
          ks))

(defn compute-namespace-lookup-key
  ([]
   (-> *ns* .getName keyword))
  ([^clojure.lang.Symbol type-name]
   (-> *ns* .getName (str "/" type-name) keyword)))

(defmacro make-type
  [^clojure.lang.Symbol type-name ^java.util.Map type-map]
  (swap! type-registry
         assoc
         (compute-namespace-lookup-key type-name)
         (eval type-map)))
