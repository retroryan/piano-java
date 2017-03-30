package controllers;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;


public class PianoWebSocketActor extends UntypedActor {

    public static Props props(ActorRef out) {
        return Props.create(PianoWebSocketActor.class, out);
    }

    private final ActorRef out;

    public PianoWebSocketActor(ActorRef out) {
        this.out = out;
    }

    public void onReceive(Object message) throws Exception {
        System.out.println("message = " + message);

        if (message instanceof String) {
            System.out.println(" str message = " + message);
        }
    }
}
