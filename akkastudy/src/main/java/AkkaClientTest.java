import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.UUID;

/**
 * Created by Aaron on 28/05/2017.
 */
public class AkkaClientTest {
    static class PrintMyActorRefActor extends AbstractActor {
        public PrintMyActorRefActor() {
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("printit", p -> {
                        ActorRef secondRef = getContext().actorOf(Props.empty(), "second-actor-"+ UUID.randomUUID().toString().replaceAll("-",""));
                        System.out.println("Second: " + secondRef);
                    })
                    .build();
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef firstRef = system.actorOf(Props.create(PrintMyActorRefActor.class), "first-actor");
        System.out.println("First : " + firstRef);
        firstRef.tell("printit", ActorRef.noSender());
    }

}
