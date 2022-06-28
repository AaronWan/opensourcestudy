package sudy.queue;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/5/25
 * @creat_time: 14:02
 * @since 7.5.0
 */
public class QueueTest {
  @Test
  public void testBlockQueue(){
    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(0xfff8);
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    System.out.println(queue);
    queue.offer(3);
    System.out.println(queue);
    queue.offer(2);
    System.out.println(queue);
    ArrayList<Object> rst = Lists.newArrayList();
    queue.drainTo(rst);
    System.out.println(rst);
    System.out.println(queue);
  }
}
