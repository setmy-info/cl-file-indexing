(ns info.setmy.file.config
    "Configuration getting (from CLI, env, yaml) functionality. Central pint to store configuration values for other software parts."
    (:gen-class)
    (:require [info.setmy.file.application :as application]
              [info.setmy.file.cli :as cli]
              [info.setmy.file.env :as env]))

(defn init []
    (application/init))
