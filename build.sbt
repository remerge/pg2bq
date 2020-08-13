import ReleaseTransformations._
import sbt.Keys.libraryDependencies

name := "pg2bq"

scalaVersion := "2.12.10"
val SparkVersion = "2.4.6"

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
  "org.apache.spark" %% "spark-core" % SparkVersion,
  "org.apache.spark" %% "spark-sql" % SparkVersion,
  "org.apache.spark" %% "spark-avro" % SparkVersion
)

libraryDependencies += "com.google.cloud" % "google-cloud-bigquery" % "1.111.1"
libraryDependencies += "com.google.cloud" % "google-cloud-storage" % "1.107.0"
//libraryDependencies += "com.google.cloud.bigdataoss" % "gcs-connector" % "hadoop2-2.1.2"
//libraryDependencies += "com.google.cloud.bigdataoss" % "gcs-connector" % "hadoop2-1.9.17"
libraryDependencies += "com.google.cloud.bigdataoss" % "gcs-connector" % "1.9.4-hadoop3"

//libraryDependencies += "com.spotify" % "spark-bigquery_2.11" % "0.2.1"
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
  //publishArtifacts,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
