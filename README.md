Kafka Spark Cassandra
---------------------

A self-contained sample app that combines Kafka, Spark, and Cassandra.

Start Kafka:

    ./sbt startKafka

Start Cassandra:

    ./sbt startCassandra

Run `HelloKafka`:

    ./sbt helloKafka

Run `HelloCassandra`:

    ./sbt helloCassandra

Run `HelloSpark`:

    ./sbt helloSpark

Run `HelloSparkStreaming` (requires `HelloSpark` to be running):

    ./sbt helloSparkStreaming

Checkout the Spark UI: [http://localhost:4040](http://localhost:4040)

Run `HelloPlay`:

    ./sbt run

Check out the app: [http://localhost:8080](http://localhost:8080)


# Setup and running the piano app

* sbt setupPiano
* sbt pianoSparkStreaming

# Verifying kafka

Text is garrabled because it is being written using an Integer serializer

$KAFKA_HOME/bin/kafka-console-consumer.sh --from-beginning --zookeeper localhost:2181 --topic RandomNumbers
