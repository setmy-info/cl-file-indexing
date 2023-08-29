(ns info.setmy.file.directory
    "Command line parameters validation."
    (:gen-class)
    (:require [clojure.java.io :as io]))

(defonce root-file-atom (atom nil))

(defn set-root-path [directory-path]
    (when (nil? @root-file-atom)
          (swap! root-file-atom (fn [_] (io/file directory-path)))))

(defn get-root-directory-file []
    @root-file-atom)
