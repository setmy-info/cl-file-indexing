(ns info.setmy.file.env
    "Environment variables functionality."
    (:gen-class)
    (:require [info.setmy.file.stringoperations :as stringoperations]
              [clojure.string :as str]))

(defn getenv [key]
    (str/trim (System/getenv key)))

;;; (getenv-splitted "ENV_VARIABLE_NAME" "," #(Integer. %))
;;; (getenv-splitted "ENV_VARIABLE_NAME" "," (fn [s] (Integer. s)))
(defn getenv-splitted
    ([key separator]
     (stringoperations/split-by-separator (getenv key) separator))
    ([key separator fun]
     (map fun (getenv-splitted key separator))))

(defn getenv-short [key]
    (Short. (getenv key)))

(defn getenv-int [key]
    (Integer. (getenv key)))

(defn getenv-long [key]
    (Long. (getenv key)))

(defn getenv-float [key]
    (Float. (getenv key)))

(defn getenv-double [key]
    (Double. (getenv key)))

(defn getenv-boolean [key]
    (Boolean. (getenv key)))
