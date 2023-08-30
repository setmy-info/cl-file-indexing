(ns info.setmy.file.application-test
    (:require [clojure.test :refer :all]
              [info.setmy.file.application :as application]))

(deftest init-test
    (testing "Xyz."
             (let [abc "def"]
                 (is (= 1 1))
                 (comment (application/init)))))
