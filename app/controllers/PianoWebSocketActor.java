package controllers;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import play.libs.Json;
import services.KafkaHelper;

import java.util.Properties;


public class PianoWebSocketActor extends UntypedActor {



    public static Props props(ActorRef out) {
        return Props.create(PianoWebSocketActor.class, out);
    }

    private final ActorRef out;

    Properties kafkaStreamsConfig = KafkaHelper.getPianoKafkaStreamsConfig();
    Producer<String, Integer> producer = new KafkaProducer<>(kafkaStreamsConfig);

    public PianoWebSocketActor(ActorRef out) {
        this.out = out;
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            JsonNode parsedJson = Json.parse((String) message);
            String songId = parsedJson.findValue("songId").toString();
            //songId has "" around it so we take a substring to remove the quotes before storing in cassandra.
            String finalSongId = songId.substring(1,songId.length()-1);
            Integer keyCode = parsedJson.findValue("keyCode").asInt(Integer.MAX_VALUE);
            ProducerRecord<String, Integer> producerRecord = new ProducerRecord<>(KafkaHelper.TOPIC, finalSongId, keyCode);
            producer.send(producerRecord);
        }
    }
}
