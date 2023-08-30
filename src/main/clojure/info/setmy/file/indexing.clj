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
    (let [full-path                (:full-path file-info)
          os-relative-path         (directory/remove-root-full-path full-path)
          unix-style-relative-path (directory/to-unix-path os-relative-path)
          new-file-info            (assoc file-info :relative-path unix-style-relative-path)]
        (comment (println new-file-info)
                 (println "Directory File:" (directory/get-root-directory-file))
                 (println "Directory full path:" (directory/get-root-directory-file-full-path))
                 (println "File relative path:" unix-style-relative-path)
                 (println "File:" (:file new-file-info))
                 (println "Full path:" (:full-path new-file-info))
                 (println "Name:" (:name new-file-info))
                 (println "Length:" (:length new-file-info))
                 (println "Extension:" (:extension new-file-info))
                 (println "Last modify date:" (:last-modified-date new-file-info)))
        (file-info-db/add-file-info new-file-info)))

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
