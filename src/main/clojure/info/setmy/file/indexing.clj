(ns info.setmy.file.indexing
    "Functionality for file indexing - adding file info to DB."
    (:gen-class)
    (:require [clojure.java.io :as io]
              [info.setmy.traversal :as traversal]
              [info.setmy.file.cli :as cli]
              [info.setmy.file.directory :as directory]
              [info.setmy.file.validation :as validation]
              [info.setmy.file.file-info-db :as file-info-db]))

(defn file-processor
    "Prints file information from the provided file information map.

    **Parameters:**

    * **file-info** (map): A map containing file information."
    [file-info]
    (println "Full path:" (:full-path file-info))
    (println "Name:" (:name file-info))
    (println "Length:" (:length file-info))
    (println "Extension:" (:extension file-info))
    (println "Last modify date:" (:last-modified-date file-info))
    ; TODO : remove (:full-path file-info) prefix part - root folder part. C:\sources\cl-file-indexing\.\src\test\resources\root\.no_extension_file -> C:\sources\cl-file-indexing\.\src\test\resources\root\ should be removed
    ; TODO : turn to unix stule folder separator before adding
    (file-info-db/add-file-info file-info))

(defn -main
    "Entry point of the application. Traverses files starting from the specified root path.

    **Parameters:**

    * **args** (string): Command-line arguments."
    [& args]
    (cli/parse-arguments args)
    (try
        (let [options                  (validation/validate-options (cli/get-options))
              directory-path           (:directory options)]
            (validation/validate-directory-path directory-path)
            (println "Directory to be recursivelly handled:" directory-path)
            (directory/set-root-path directory-path)
            (file-info-db/init)
            (traversal/traverse-files (io/file directory-path) file-processor))
        (catch RuntimeException e (println "Error:" (.getMessage e)))))
