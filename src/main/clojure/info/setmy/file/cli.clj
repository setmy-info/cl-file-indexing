(ns info.setmy.file.cli
    "Command line parameters and values parser."
    (:gen-class)
    (:require
        [clojure.tools.cli :refer [parse-opts]]))

(def cli-options
    [["-d"
      "--directory DIRECTORY"
      "Directory to index into DB."]
     ["-v"
      nil
      "Verbosity level"
      :id        :verbosity
      :default   0
      :update-fn inc]
     ["-h" "--help"]])

(defonce parsed-opts-atom (atom nil))

(defn get-opts [] @parsed-opts-atom)

(defn parse-arguments
    [args]
    (when (nil? @parsed-opts-atom)
          (swap! parsed-opts-atom (fn [_] (parse-opts args cli-options))))
    @parsed-opts-atom)
