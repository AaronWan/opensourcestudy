import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;

import java.io.IOException;

/**
 * Created by Aaron on 28/05/2017.
 */
public class AkkaMasterTest {
    public static void main(String[] args){
        Config config;
        final ActorSystem system = ActorSystem.create("localhost");
        ActorRef teacher = system.actorOf(Teacher.props(), "Teacher");
        ActorRef student = system.actorOf(Teacher.props(), "Student");
        teacher.tell("heihei",student);
        student.tell("heihei",teacher);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }

    }

    private static class Teacher extends AbstractActor {
        //#printer-messages
        static public Props props() {
            return Props.create(Teacher.class, () -> new Teacher());
        }
        //#printer-messages
        static public class Greeting {
            public final String message;

            public Greeting(String message) {
                this.message = message;
            }
        }
        //#printer-messages

        private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

        public Teacher() {
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("heihei", greeting -> {
                        System.out.println(Thread.currentThread().getId()+"---"+this.sender().path()+"---"+greeting);
                        this.getSender().tell("heihei",this.getSelf());
                    })
                    .build();        }
    }
}
