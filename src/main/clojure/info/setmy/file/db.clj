(ns info.setmy.file.db
    "Functionality handling DB."
    (:gen-class)
    (:require [clojure.java.jdbc :as jdbc]))

;;;
;;; https://clojure-doc.org/articles/ecosystem/java_jdbc/home/
;;;

(defonce db-url "./indexing")

;;; TODO : from command line.
(def db-spec
    "URL : jdbc:h2:./indexing"
    {:classname   "org.h2.Driver"
     :subprotocol "h2:file"
     :subname     db-url
     :user        "sa"
     :password    ""})

(defn create-table [table-keyword column-vector]
    (jdbc/db-do-commands db-spec
                         ;https://clojure-doc.org/articles/ecosystem/java_jdbc/using_ddl/
                         [(jdbc/create-table-ddl table-keyword column-vector
                                                 {:entities     clojure.string/upper-case
                                                  :conditional? true})]))

(defn insert! [table-keyword data-map]
    (jdbc/insert! db-spec table-keyword data-map))

(defn query [query-string]
    (jdbc/query db-spec [query-string]))

(defn update! [table-keyword data-map where-vector]
    (jdbc/update! db-spec table-keyword data-map where-vector))

(defn delete! [table-keyword where-vector]
    (jdbc/delete! db-spec table-keyword where-vector))
