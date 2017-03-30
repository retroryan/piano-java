package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.api.mvc.Action;
import play.api.mvc.AnyContent;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.index;

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

    /**
     * This is the deprecated way of creating web sockets,
     * but is a much easier API to work with for small examples
     */
    public LegacyWebSocket<String> pianoSocket() {
        return WebSocket.withActor(PianoWebSocketActor::props);
    }

}
