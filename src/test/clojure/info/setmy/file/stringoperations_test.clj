(ns info.setmy.file.stringoperations-test
    (:require [clojure.test :refer :all]
              [info.setmy.file.stringoperations :as stringoperations]))

(deftest split-comma-separated-test
    (testing "Splitting comma separated text."
             (let [splittable-string "  one,  two,   three "]
                 (is
                  (= (stringoperations/split-by-separator splittable-string)
                     ["one" "two" "three"]))
                 (is
                  (= (stringoperations/split-by-separator splittable-string ",")
                     ["one" "two" "three"])))))
