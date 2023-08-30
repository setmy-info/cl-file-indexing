(ns info.setmy.file.application
    "Application yaml/properties/toml representing functionality.")

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
    (comment
        (let [classloader (Thread/currentThread)
              .getContextClassLoader
              properties
              (Properties.)]
            (with-open [stream (.getResourceAsStream classloader filename)]
                (if stream
                    (do
                        (.load properties stream)
                        (set-application-properties properties)
                        properties)
                    (throw (Exception. (str "File " filename " not found on CLASSPATH"))))))))

(defn init []
    (load-properties-from-classpath application-properties-file-name))
