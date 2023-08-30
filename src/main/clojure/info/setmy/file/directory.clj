(ns info.setmy.file.directory
    "Directory and file related functionality."
    (:gen-class)
    (:require [clojure.java.io :as io]))

(defonce root-file-atom (atom nil))

(defonce os-path-separator (System/getProperty "file.separator"))

(defonce unix-os-path-separator "/")

(defn set-root-path [directory-path]
    (when (nil? @root-file-atom)
          (swap! root-file-atom (fn [_] (io/file directory-path)))))

(defn get-root-directory-file []
    @root-file-atom)

(defn get-root-directory-file-full-path []
    (.getAbsolutePath (get-root-directory-file)))

(defn remove-root-full-path [path]
    (let [cleaned (subs path (count (get-root-directory-file-full-path)))]
        cleaned))

(defn to-unix-path [path]
    (clojure.string/replace path "\\" unix-os-path-separator))
