(ns fitness.upper-body-test
  (:require
   [fitness.upper-body :refer :all]
   [clojure.test       :refer :all]
   [fitness.warm-up    :as warm-up]
   [fitness.cool-down  :as cool-down]
   [fitness.pep        :as pep]))

(defn warm-up []
  (warm-up/jogging 60)
  (warm-up/cross-toe-touches 20))

(defn cool-down []
  (cool-down/hurdler-stretch 30)
  (cool-down/arm-stretch 30))

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

(deftest push-up-test
  (let [result (push-ups 50)]
    (is (= 50 (:actual-reps result)))
    (is (<= (:seconds result) (* 4 60)))))

(deftest pull-up-test
  (let [result (pull-ups 10)]
    (is (= 10 (:actual-reps result)))
    (is (<= (:seconds result) 60))))

(deftest bench-press-too-heavy-test
  (is (thrown? fitness.safety.UnsafeResistanceException
               (bench-presses 300 10))))

(deftest bench-presses-test
  (let [result (bench-presses 100 20)]
    (is (= 20 (:actual-reps result)))
    (is (<= (:seconds result) 60))))
