name := """volt"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "ws.securesocial" %% "securesocial" % "master-SNAPSHOT"
)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)


