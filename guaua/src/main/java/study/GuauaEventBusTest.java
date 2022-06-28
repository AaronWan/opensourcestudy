package study;

import com.google.common.eventbus.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @creat_date: 2022/6/28
 * @creat_time: 00:04
 * @since 7.5.0
 */
public class GuauaEventBusTest {

  private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(
    1,
    1, 0,
    TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
    r -> new Thread(r, "EngineEventBus-" + System.currentTimeMillis()));


  private EventBus eventBus = new AsyncEventBus(executorService,
    (throwable, subscriberExceptionContext) -> System.out.println(throwable.getMessage()+","+subscriberExceptionContext.getEvent()));
  @Test
  public void testProducer() {
    eventBus.register(new Event1Listener());
    eventBus.register(new Event2Listener());
    for (int i = 0; i <10000 ; i++) {
      eventBus.post(new Event1("test1"));
      eventBus.post(new Event2("test2"));
      System.out.println(executorService.getQueue().size());
    }
    while (!executorService.getQueue().isEmpty()){

    }
  }

  interface  EventListener{
    String type();
  }

  class Event1Listener implements EventListener{
    @Override
    public String type() {
      return "event1";
    }
    @SneakyThrows
    @Subscribe
    public void listener(Event1 event1) {
      System.out.println(Thread.currentThread().getName()+","+event1.name);
      Thread.sleep(1000);
      eventBus.post(new Event1("test1"));
      throw new RuntimeException("First Exception");
    }
  }


  class Event2Listener implements EventListener{
    @Override
    public String type() {
      return "event2";
    }
    @SneakyThrows
    @Subscribe
    public void listener(Event2 event1) {
      System.out.println(Thread.currentThread().getName()+","+event1.name);
      Thread.sleep(1000);
    }
  }

  @Data
  @AllArgsConstructor
  public class Event1 {
    String name;
  }

  @Data
  @AllArgsConstructor
  public class Event2 {
    String name;
  }
}
