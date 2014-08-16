name := """horta-web"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0-M2",
  "org.webjars" % "angularjs" % "1.2.22",
  "org.webjars" % "angular-ui-router" % "0.2.10-1"
)
