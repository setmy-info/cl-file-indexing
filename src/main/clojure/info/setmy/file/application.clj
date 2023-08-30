(ns info.setmy.file.application
    "Application yaml/properties/toml representing functionality."
    (:require [java.util Properties]))

(def application-properties-file-name "application.properties")
(def application-yaml-file-name "application.yaml")

(defonce parsed-application-properties-atom (atom nil))

(defn get-application-properties []
    @parsed-application-properties-atom)

(defn set-application-properties [properties]
    (when (nil? @parsed-application-properties-atom)
          (swap! parsed-application-properties-atom properties))
    @parsed-application-properties-atom)

(defn load-properties-from-classpath [filename]
    (let [classloader (Thread/currentThread)
          .getContextClassLoader
          properties
          (Properties.)]
        (with-open [stream (.getResourceAsStream classloader filename)]
            (if stream
                (do
                    (.load properties stream)
                    properties)
                (throw (Exception. (str "Faili " filename " ei leitud CLASSPATH-ilt")))))))


(defn init []
    (load-properties-from-classpath application-properties-file-name))
