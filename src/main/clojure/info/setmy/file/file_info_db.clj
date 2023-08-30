(ns info.setmy.file.file-info-db
    "Functionality handling DB."
    (:gen-class)
    (:require [info.setmy.file.db :as db]
              [clojure.data.json :as json]))

(def table-keyword :fileinfo)

(defn init []
    (db/create-table table-keyword
                     [[:id "BIGINT AUTO_INCREMENT PRIMARY KEY"]
                      [:fullpath "VARCHAR(2048)"]
                      [:relativepath "VARCHAR(2048)" "NOT NULL" "UNIQUE"]
                      [:name "VARCHAR(1024)"]
                      [:length "BIGINT"]
                      [:extension "VARCHAR(512)"]
                      [:metadata "JSON"]]))

(defn get-meta-data [file-info]
    (json/write-str
     {:tags         ["tag1" "tag2"]
      :fullpath     (:full-path file-info)
      :relativepath (:relative-path file-info)
      :name         (:name file-info)
      :length       (:length file-info)
      :extension    (:extension file-info)}))

(defn add-file-info [file-info]
    (db/insert! table-keyword
                {:fullpath     (:full-path file-info)
                 :relativepath (:relative-path file-info)
                 :name         (:name file-info)
                 :length       (:length file-info)
                 :extension    (:extension file-info)
                 :metadata     (get-meta-data file-info)}))

(defn get-all-files [file-info]
    (db/query "SELECT * FROM fileinfo"))
