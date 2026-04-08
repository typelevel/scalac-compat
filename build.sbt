name := "scalac-compat"

ThisBuild / tlBaseVersion := "0.1"

ThisBuild / organization     := "org.typelevel"
ThisBuild / organizationName := "Typelevel"
ThisBuild / startYear        := Some(2022)
ThisBuild / licenses         := Seq(License.Apache2)
ThisBuild / developers       := List(
  tlGitHubDev("satorg", "Sergey Torgashov"),
  tlGitHubDev("bpholt", "Brian Holt")
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

lazy val root = tlCrossRootProject.aggregate(
  annotation,
  `scala2-notgiven-compat`
)

lazy val munitVersion = "1.2.4"

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

lazy val `scala2-notgiven-compat` = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("scala2-notgiven-compat"))
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-collection-compat" % "2.14.0"
    ),
    tlVersionIntroduced := Map("2.12" -> "0.1.5", "2.13" -> "0.1.5", "3" -> "0.1.5")
  )
