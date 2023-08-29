# cl-file-indexing

## Development

Test

```shell
lein test
```

Build documentation

```shell
lein codox
# Or
lein doc
```

Build

```shell
lein uberjar
```

Deploy to Clojars repository

```shell
lein deploy clojars
```

Run

```shell
lein run -d ./src/test/resources/root
```

With built uberjar:

```shell
java -jar ./target/uberjar/cl-file-indexing-0.1.0-SNAPSHOT-standalone.jar -d ./src/test/resources/root

```

## TODO

1. Before inserting change key value

```clojure
(def my-map {:a 1 :b 2 :c 3})

(def key-to-change :b)
(def new-value 45)

(def updated-map (assoc my-map key-to-change new-value))

(println updated-map)

; Output: {:a 1, :b 45, :c 3}
```

2. Remove prefix part

```clojure
(def original-string "C:\\sources\\cl-file-indexing\\.\\sdf\\sdf\\sdf")
(def prefix-to-remove "C:\\sources\\cl-file-indexing\\.\\")

(def trimmed-string (subs original-string (count prefix-to-remove)))

(println trimmed-string)
```

3. Check full-name doesn't exist before insert
4. ...
