(ns info.setmy.file.cli
    "Command line parameters and values parser."
    (:gen-class)
    (:require
        [clojure.tools.cli :refer [parse-opts]]))

(def argument-parser-config
    [["-d"
      "--directory DIRECTORY"
      "Directory to index into DB."
      :validate
      [(fn [value] (not (nil? value))) "Path must be set"]]
     ["-i"
      "--indexing DB_NAME"
      "DB name."
      :default
      "indexing"]
     ["-p"
      "--profiles PROFILE_ONE,PROFILE_TWO"
      "Profiles."]
     ["-v"
      nil
      "Verbosity level"
      :id        :verbosity
      :default   0
      :update-fn inc]
     ["-h" "--help"]])

(defonce parsed-options-atom (atom nil))

(defn get-all-options []
    @parsed-options-atom)

(defn get-options []
    (:options (get-all-options)))

(defn parse-arguments [args]
    (when (nil? @parsed-options-atom)
          (swap! parsed-options-atom (fn [_] (parse-opts args argument-parser-config))))
    @parsed-options-atom)
