package study;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * @author 万松(Aaron)
 * @creat_date: 2022/6/28
 * @creat_time: 00:04
 * @since 7.5.0
 */
public class GuauaEventBusTest {

  private EventBus eventBus = new EventBus("TEST");
  @Test
  public void testProducer() {
    eventBus.register(new Event1Listener());
    eventBus.register(new Event2Listener());
    eventBus.post(new Event1("test1"));
  }

  interface  EventListener{
    String type();
  }

  class Event1Listener implements EventListener{
    @Override
    public String type() {
      return "event1";
    }
    @Subscribe
    public void listener(Event1 event1) {
      System.out.println(event1.name);
    }
  }


  class Event2Listener implements EventListener{
    @Override
    public String type() {
      return "event2";
    }
    @Subscribe
    public void listener(Event1 event1) {
      System.out.println(event1.name);
    }
  }

  @Data
  @AllArgsConstructor
  public class Event1 {
    String name;
  }
}
