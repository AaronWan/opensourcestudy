package common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-11-08
 * @creat_time: 22:39
 * @since 6.6
 */
@UtilityClass
public class QueueStorageUtil {
  /**
   * x 上一分钟触发次数
   *
   * @param fastQueueAllowCount  一分钟,快队列允许的最大次数
   * @param maxTotalTriggerCount 一分钟内,触发允许在快队列的最大触发次数
   * @param x                    当前和前一分钟 触发的次数
   * @return 快队列允许的最大的次数
   */
  public double getLimit(double fastQueueAllowCount, double maxTotalTriggerCount, double x) {
    double rst = fastQueueAllowCount * (-Math.sqrt(x) / Math.sqrt(maxTotalTriggerCount) + 1);
    return rst < 0 ? 0 : rst;
  }

  /**
   * x 上一分钟触发次数
   *
   * @param tenantId             企业id
   * @param fastQueueAllowCount  一分钟,快队列允许的最大次数
   * @param maxTotalTriggerCount 一分钟内,触发允许在快队列的最大触发次数
   * @return 快队列允许的最大的次数
   */
  public static double getLimit(String tenantId, double fastQueueAllowCount, double maxTotalTriggerCount) {
    LocalDateTime now = LocalDateTime.now();
    String currentKey = tenantId + "-" + format(now);
    String lastKey = tenantId + "-" + format(now.minusMinutes(1));
    try {
      int lastCount = recorder.get(lastKey).incrementAndGet();
      int count = recorder.get(currentKey).incrementAndGet();
      return getLimit(fastQueueAllowCount, maxTotalTriggerCount, Math.max(lastCount, count));
    } catch (ExecutionException e) {
    }
    return fastQueueAllowCount;
  }

  private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm");

  private static String format(LocalDateTime date) {
    return dateTimeFormatter.format(date);
  }

  public static LoadingCache<String, AtomicInteger> recorder = CacheBuilder.newBuilder()
    .expireAfterAccess(1, TimeUnit.MINUTES)
    .build(new CacheLoader<String, AtomicInteger>() {
      @Override
      public AtomicInteger load(String s) {
        return new AtomicInteger(0);
      }
    });

  static {
    new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("------------------");
        recorder.asMap().forEach((key, value) -> System.out.println("key:" + key + ",value:" + value));
        System.out.println("------------------");
      }
    }).start();
  }

  public static void main(String[] args) {
    for (double i = 0; ; i = i + 1) {
      if (i % 10000 == 0) {
        System.out.println(getLimit("1", 100, 100));
      }
    }
  }
}
