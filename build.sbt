name := "scalac-compat"

ThisBuild / tlBaseVersion := "0.1"

ThisBuild / organization     := "org.typelevel"
ThisBuild / organizationName := "Typelevel"
ThisBuild / startYear        := Some(2022)
ThisBuild / licenses         := Seq(License.Apache2)
ThisBuild / developers       := List(
  tlGitHubDev("satorg", "Sergey Torgashov")
)

val Scala212 = "2.12.21"
val Scala213 = "2.13.18"
val Scala3   = "3.3.7"

ThisBuild / scalaVersion := Scala213

ThisBuild / crossScalaVersions := Seq(
  Scala212,
  Scala213,
  Scala3
)

lazy val root = tlCrossRootProject.aggregate(annotation)

lazy val munitVersion = "1.2.1"

lazy val annotation = crossProject(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("annotation"))
  .settings(
    name := "scalac-compat-annotation",
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit" % munitVersion % Test
    ),
    // Required for tests but also good for the main code.
    tlFatalWarnings := true,
    scalacOptions   := {
      scalacOptions.value
        .filterNot { opt =>
          // Remove all partially defined options like '-Xlint:-unused'.
          opt.startsWith("-Xlint") ||
          opt.startsWith("-Ywarn-unused") ||
          opt.startsWith("-Wunused")
        } ++
        CrossVersion.partialVersion(scalaVersion.value).fold(Seq.empty[String]) {
          case (2, 12) => Seq("-Xlint", "-Ywarn-unused")
          case (2, 13) => Seq("-Xlint", "-Wunused")
          case (3, _)  => Seq("-Wunused:all")
        }
    }
  )
