package com.opensource.local;

import com.google.common.collect.Sets;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ThreadLocalTest {
    public static class LogRequest {
        private static final ThreadLocal<LogRequest> threadLocal = new ThreadLocal<>();

        private LogRequest() {
        }

        public static LogRequest getInstance() {
            LogRequest logRequest = threadLocal.get();
            if (logRequest == null) {
                threadLocal.set(logRequest = new LogRequest());
            }
            System.out.println(Thread.currentThread().getName() + "---" + logRequest.hashCode());
            return logRequest;
        }

        public void remove() {
            threadLocal.remove();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicLong i = new AtomicLong();
        ExecutorService executor = Executors.newCachedThreadPool(r -> {
            String name = "ThreadLocal-" + i.getAndAdd(1);
            Thread t = new Thread(r);
            t.setName(name);
            return t;
        });
        Set<LogRequest> requests= Sets.newHashSet();
        for (int j = 0; j < 100; j++) {
            Thread.sleep(100);
            executor.execute(() -> {
                System.out.println("-------------start-----------------");
                LogRequest request = LogRequest.getInstance();
                Assert.that(requests.add(request),"有重复");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                request.remove();
                System.out.println("-------------end-----------------");
            });
        }
        executor.awaitTermination(10000, TimeUnit.SECONDS);
    }
}
