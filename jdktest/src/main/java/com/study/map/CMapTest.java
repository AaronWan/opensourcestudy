package com.study.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 万松(Aaron)
 * @creat_date: 2022/4/23
 * @creat_time: 10:46
 * @since 7.5.0
 */
public class CMapTest {
  /**
   * 由于concurrentHashMap中使用的是cas操作，因此在出现cas嵌套的情况下，就会形成一种『死锁』。举例来说，一个值原来是 1， 我想把它修改成2，正常的cas操作，会比较在修改的那一刻，值是否仍然为1。这种比较，在cas只有一层的情况下，是没有问题的。但是，假如有两层cas，这个值原来是1，第一层把 1 -> 2，在cas还没有生效时，继续进入第二层cas操作，把 2 -> 3，当最终提交时，第二层cas比较当前值是否是2，但由于当前指仍然是1，因此修改无效。最终反复进入循环，形成死锁。
   */
  public static void main(String[] args) {
    Map<String,Integer> data=new ConcurrentHashMap<>();
    data.computeIfAbsent("AaAa",key-> data.computeIfAbsent("BBBB", key2->42));
    System.out.println("---end---");
  }
}
