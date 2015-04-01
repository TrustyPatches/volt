name := """volt"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.10.5"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "ws.securesocial" %% "securesocial" % "2.1.4"
)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)


