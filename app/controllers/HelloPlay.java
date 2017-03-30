package controllers;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.api.mvc.Action;
import play.api.mvc.AnyContent;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.index;

import java.util.function.Function;

@Singleton
public class HelloPlay extends Controller {

    //using assets doesn't work because it returns a scala action
    private final Assets assets;

    WebJarAssets webJarAssets;

    @Inject
    public HelloPlay(Assets assets, WebJarAssets webJarAssets) {
        this.assets = assets;
        this.webJarAssets = webJarAssets;
    }

    public Result index() {
        //scala and not java content?
        // Action<AnyContent> contentAction = Assets.at("/public", "index.html", false);
        return ok(index.render(webJarAssets));
    }

    public LegacyWebSocket<String> pianoSocket() {
        Function<ActorRef, Props> props = PianoWebSocketActor::props;
        System.out.println("props = " + props);
        LegacyWebSocket<String> stringLegacyWebSocket = WebSocket.withActor(props);
        System.out.println("stringLegacyWebSocket = " + stringLegacyWebSocket);
        return stringLegacyWebSocket;
    }

}
