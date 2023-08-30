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

1. Check relative-name doesn't exist before insert
2. db file should not be added to table. DB name from CLI.
3. CLI option parser doesn't validate
4. Creation, modify date times into DB.
5. Refactor layering: more DB abstraction (db.clj without concrete URLs, "service" layer should handle names, conf etc)
