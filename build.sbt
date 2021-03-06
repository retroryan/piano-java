
name := """piano-java"""

version := "0.15"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8"
)

lazy val common = (project in file("common")).settings(commonSettings: _*)

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "org.webjars" %% "webjars-play" % "2.5.0",
    "org.webjars" % "bootstrap" % "3.3.4",
    "org.webjars" % "javascript-piano" % "74c90339ad1d55a72a99fe6e33b35752c15d71c7",
    "io.dropwizard.metrics" % "metrics-core" % "3.2.2"
  ))
  .dependsOn(common)

lazy val clients = (project in file("clients"))
  .settings(commonSettings: _*)
  .dependsOn(common)

lazy val kafkaServer = (project in file("kafka-server"))
  .settings(commonSettings: _*)

lazy val cassandraServer = (project in file("cassandra-server"))
  .settings(commonSettings: _*)


libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

enablePlugins(AtomPlugin)

TaskKey[Unit]("startCassandra") := (runMain in Compile in cassandraServer).toTask(" CassandraServer").value
TaskKey[Unit]("startKafka") := (runMain in Compile in kafkaServer).toTask(" KafkaServer").value
TaskKey[Unit]("helloCassandra") := (runMain in Compile in clients).toTask(" HelloCassandra").value
TaskKey[Unit]("helloKafka") := (runMain in Compile in clients).toTask(" HelloKafka").value
TaskKey[Unit]("setupPiano") := (runMain in Compile in clients).toTask(" piano.CassandraSetup").value
TaskKey[Unit]("pianoSparkStreaming") := (runMain in Compile in clients).toTask(" piano.SparkStreaming").value
