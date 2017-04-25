(ns fitness.lower-body-test
  (:require [fitness.lower-body :refer :all]
            [clojure.test       :refer :all]
            [fitness.warm-up    :as warm-up]
            [fitness.cool-down  :as cool-down]
            [fitness.pep        :as pep]))

(defn warm-up []
  (warm-up/jumping-jacks 50)
  (warm-up/knee-raises 30))

(defn cool-down []
  (cool-down/middle-split 30)
  (cool-down/forward-bend 30))

(use-fixtures :once
  (fn [tests]
    (println "===============")
    (warm-up)
    (tests)
    (cool-down)
    (println "===============")))


(use-fixtures :each
  (fn [test]
    (println "_________________")
    (pep/ready-set-go)
    (test)
    (pep/high-five)
    (println "-----------------")))

(deftest squats-test
  (let [result (squats 50)]
    (is (= 50 (:actual-reps result)))
    (is (<= (:seconds result) 60))))

(deftest frog-leap-test
  (let [results (frog-leaps 5)]
    (is (= 5 (:actual-reps results)))
    (is (<= (:seconds results) 30))))

(deftest weighted-squats-too-heavy-test
  (is (thrown? fitness.safety.UnsafeResistanceException
               (weighted-squats 500 10))))

(deftest weight-squats-test
  (let [result (weighted-squats 100 20)]
    (is (= 20 (:actual-reps result)))
    (is (<= (:seconds result) 120))))
