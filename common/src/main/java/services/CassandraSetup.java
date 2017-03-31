package services;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.util.List;

public class CassandraSetup {

    public static void main(String[] args) {
        //CassandraHelper.getInstance().createPianoKeyspace();

        List<String> allPianoSongs = CassandraHelper.getInstance().getAllPianoSongs();
        JsonNode pianoSongJson = Json.toJson(allPianoSongs);
        String s = pianoSongJson.toString();
        System.out.println("s = " + s);

    }
}
