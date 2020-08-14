import sbt.Keys.libraryDependencies
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

name := "pg2bq"

scalaVersion := "2.12.10"

val sparkVersion: String = "2.4.6"

scalafmtOnCompile in ThisBuild := true
enablePlugins(JavaAppPackaging)
enablePlugins(LauncherJarPlugin)

// you shall not pass
scalacOptions in ThisBuild := Seq(
  "-unchecked",
  "-feature",
  "-deprecation",
  "-encoding",
  "utf8",
  "-unchecked",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-unused-import"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-avro" % sparkVersion
)

libraryDependencies += "com.google.cloud" % "google-cloud-bigquery" % "1.111.1"
libraryDependencies += "com.google.cloud" % "google-cloud-storage" % "1.107.0"
libraryDependencies += "com.google.cloud.bigdataoss" % "gcs-connector" % "1.9.4-hadoop3"
libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.8.0"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
