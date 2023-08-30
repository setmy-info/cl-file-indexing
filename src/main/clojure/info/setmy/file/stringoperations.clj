(ns info.setmy.file.stringoperations
    "Environment variables functionality."
    (:gen-class)
    (:require [clojure.string :as str]))

(defn to-reg-exp[regexp]
    (if (string? regexp)
        (re-pattern regexp)
        regexp))

(defn split-by-separator
    ([str regexp]
     (let [pieces (str/split str (to-reg-exp regexp))]
         (mapv str/trim pieces)))
    ([str]
     (let [pieces (split-by-separator str #",")]
         (mapv str/trim pieces))))
