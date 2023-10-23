# scalac-compat

*scalac-compat* is a <sub>micro</sub>library containing a set of lightweight tools designed to tackle mismatches between different Scala compiler versions in cross-build projects.

Currently it consists of a single module *scalac-compat-annotation*.

## scalac-compat-annotation

The module contains Scala version specific annotations that can help to tune a build in cases when different Scala compiler versions may treat same standard annotations differently.

### Usage

The module is published for Scala 2.12.x, 2.13.x and 3.2.x:
```scala
// sbt
"org.typelevel" %% "scalac-compat-annotation" % "{{MODULE_VERSION}}"

// mill
ivy"org.typelevel::scalac-compat-annotation:{{MODULE_VERSION}}"

// Scala CLI
//> using dep org.typelevel::scalac-compat-annotation:{{MODULE_VERSION}}
```
Then import the module annotations:
```scala
import org.typelevel.scalaccompat.annotation._
```

#### `@nowarn` annotation specializations

There are a set of specializations defined for `@scala.annotation.nowarn` that either redirect to the standard annotation or to a no-op annotation depending on a particular Scala version:

| Scala<br>version | `@nowarn2` | `@nowarn212` | `@nowarn213` | `@nowarn3` |
| ---- | ---------- | ------------ | ------------ | ---------- |
| 2.12 | `@nowarn`  | `@nowarn`    | no-op        | no-op      |
| 2.13 | `@nowarn`  | no-op        | `@nowarn`    | no-op      |
| 3    | no-op      | no-op        | no-op        | `@nowarn`  |

##### Example

Consider a case when someone has to deal with `scala.collection.Stream` in a cross-build project. `Stream` is deprecated since Scala v2.13. However, simply applying `@nowarn("cat=deprecation")` to methods that attempt to reference to `Stream` may lead to another warning on Scala version prior to v2.13:
```
@nowarn annotation does not suppress any warnings
```
Thus the specialized `@nowarn` annotations can come in handy here:
```scala
import org.typelevel.scalaccompat.annotation._

@nowarn213("cat=deprecation")
@nowarn3("cat=deprecation")
def reinventTheWheel: Stream[Int] = {
  Stream
    .iterate((1, 1)) { case (a2, a1) => (a1, a2 + a1) }
    .map(_._1)
}
```

#### `@unused` compatibility annotation

The `@scala.annotation.unused` annotation was introduced since Scala v2.13.x and does not exists in previous versions of Scala library. The compatibility `@org.typelevel.scalaccompat.annotation.unused` annotation simply redirects to the `@scala.annotation.unused` on versions where it exists while mimics the same behavior with `@scala.annotation.nowarn` for Scala v2.12.x:

| Scala<br>version | `@scalaccompat.annotation.unused`<br>redirects to |
| ---- | ---------------------------------------- |
| 2.12 | `@scala.annotation.nowarn("cat=unused")` |
| 2.13 | `@scala.annotation.unused`               |
| 3    | `@scala.annotation.unused`               |

##### Example

```scala
// Notice that there is no need to import `scala.annotation.unused`.
import org.typelevel.scalaccompat.annotation._

// Compiles on all supported Scala versions.
def testUnusedParam(name: String, @unused unusedParam: String): Unit = {
  // The `unusedParam` is not used withing the body.
  println(s"Hello, $name")
}
```

See more examples in **./annotation/src/test** 

## Conduct

Participants are expected to follow the [Scala Code of Conduct](https://www.scala-lang.org/conduct/) while discussing the project on GitHub and any other venues associated with the project. See the [organizational Code of Conduct](https://github.com/typelevel/.github/blob/main/CODE_OF_CONDUCT.md) for more details.

## License

All code in this repository is licensed under the Apache License, Version 2.0. See [LICENSE](./LICENSE).
