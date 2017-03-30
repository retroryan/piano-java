
# Running the samples

sbt tasks exist for running all the demos:

* sbt startCassandra
* sbt startKafka
* sbt helloCassandra
* sbt helloKafka
* sbt setupPiano
* sbt pianoSparkStreaming

# Verifying kafka

Text is garrabled because it is being written using an Integer serializer

$KAFKA_HOME/bin/kafka-console-consumer.sh --from-beginning --zookeeper localhost:2181 --topic RandomNumbers
