package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.PianoSong;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.WebSocket;
import services.CassandraHelper;
import views.html.index;

import java.util.List;
import java.util.Optional;

@Singleton
public class HelloPlay extends Controller {

    WebJarAssets webJarAssets;
    CassandraHelper cassandraHelper = CassandraHelper.getInstance();

    @Inject
    public HelloPlay(WebJarAssets webJarAssets) {
        this.webJarAssets = webJarAssets;
    }

    public Result index() {
        return ok(index.render(webJarAssets));
    }

    public Result songs() {
        List<String> allPianoSongs = cassandraHelper.getAllPianoSongs();
        JsonNode pianoSongJson = Json.toJson(allPianoSongs);
        pianoSongJson.toString();

        return ok(pianoSongJson);
    }

    public Result play(String id) {
        Optional<PianoSong> pianoSong = cassandraHelper.getPianoSong(id);
        if (pianoSong.isPresent()) {
            return ok(Json.toJson(pianoSong.get()));
        }
        else {
            return notFound();
        }
    }

    /**
     * This is the deprecated way of creating web sockets,
     * but is a much easier API to work with for small examples
     */
    public LegacyWebSocket<String> pianoSocket() {
        return WebSocket.withActor(PianoWebSocketActor::props);
    }

}
