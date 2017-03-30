libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.5.13" exclude("org.slf4j", "slf4j-simple"),
  "org.apache.kafka" % "kafka-streams" % "0.10.2.0" exclude("org.slf4j", "slf4j-simple"),
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0",
  "io.dropwizard.metrics" % "metrics-core" % "3.2.2"
)

dependencyOverrides ++= Set("com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5")