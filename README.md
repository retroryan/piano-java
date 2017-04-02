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

Run `HelloPlay`:

    ./sbt run

Check out the app: [http://localhost:8080](http://localhost:8080)

# Setup and running the piano app

Run `setupPiano` to set up the Cassandra Schema

    sbt setupPiano

Run `pianoSparkStreaming` to start Spark Streaming

    sbt pianoSparkStreaming

Then checkout the Spark UI: [http://localhost:4040](http://localhost:4040)

# Overview of main directories and files

### Play Project Directories

**app**   ->   The root of the Play Java project.  It contains the controllers and view.
  
**conf**  ->   The Play Project configuration directory.
  
**conf/routes** ->  The Play Project routes file that routes requests to controllers and views. 

**clients**  ->  This has all of the sample applications for the workshop

**clients/piano**  -> The Sample Piano Code

**clients/piano/CassandraSetup** -> This creates the sample Cassandra schema for the Piano app.

**clients/piano/SparkStreaming** -> This is the Spark Streaming example for the Piano app.

**cassandra-server**  ->  This is an app that starts an embedded cassandra server 

**kafka-server**  ->  This is an app that starts an embedded kafka server

# Examples of Spark and Spark Streaming in Java

[Spark Demos contains examples in Java of Spark and Spark Streaming](https://github.com/retroryan/DataStaxSparkDemos)

These samples can be easily run with OSS Spark and Cassandra by changing the setup of the spark context to use the following:
 
 ```
        SparkConf sparkConf = new SparkConf()
                .setMaster("local[1]")
                .setAppName("piano-spark-streaming")
                .set("spark.cassandra.connection.host", "localhost")
                .set("spark.sql.warehouse.dir", "spark-warehouse");

```

# Verifying kafka

Text is garrabled because it is being written using an Integer serializer

$KAFKA_HOME/bin/kafka-console-consumer.sh --from-beginning --zookeeper localhost:2181 --topic RandomNumbers
