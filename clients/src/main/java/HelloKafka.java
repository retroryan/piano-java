import kafka.server.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloKafka {

    private static final String bootstrapServer = "localhost:9092";
    private static final String topic = "RandomNumbers";
    private static final String applicationId = "hello-kafka-example";

    Random random = new Random();
    AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        new HelloKafka().runHelloKafka();
    }

    void runHelloKafka() {
        System.out.println("Running  Hello Kafka Streams");

        Properties kafkaStreamsConfig = getKafkaStreamsConfig();

        final Serde<Integer> intSerdes = Serdes.Integer();

        KStreamBuilder streamConsumer = new KStreamBuilder();

        KStream<Integer, Integer> stream = streamConsumer
                .stream(intSerdes, intSerdes, topic);

        stream.foreach((key, value) -> {
                    String formatedRecord = String.format("%d:%d", key, value);
                    System.out.println("RCRD:" + formatedRecord);
                });

        KafkaStreams streams = new KafkaStreams(streamConsumer, kafkaStreamsConfig);
        streams.start();

        Producer<Integer, Integer> producer = new KafkaProducer<>(kafkaStreamsConfig);
        Runnable task = () -> {
            producer.send(new ProducerRecord<>(topic, atomicInteger.getAndIncrement(), random.nextInt()));
        };

        Executors
                .newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(task, 0, 500, TimeUnit.MILLISECONDS);


        // When should the producer be closed?
        // producer.close();
    }

    private Properties getKafkaStreamsConfig() {
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
        streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
        streamsConfiguration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        streamsConfiguration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        return streamsConfiguration;
    }

}
