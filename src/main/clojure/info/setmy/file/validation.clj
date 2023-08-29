(ns info.setmy.file.validation
    "Command line parameters validation."
    (:gen-class)
    (:require
        [clojure.tools.cli :refer [parse-opts]]
        [clojure.java.io :as io]))

(defn validate-options [options]
    (if (nil? (:directory options))
        (throw (RuntimeException. "No directory passed")))
    options)

(defn validate-directory-path [directory-path]
    (if (not (nil? directory-path))
        (if (not (.isDirectory (io/file directory-path)))
            (throw (RuntimeException. "Is not direcotry")))
        (throw (RuntimeException. "No direcorty passed")))
    directory-path)
