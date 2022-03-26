package com.opensource.local;

import com.google.common.collect.Sets;
import org.apache.curator.utils.ThreadUtils;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ChildThreadLocalTest {
    public static class LogRequest {
        private static final InheritableThreadLocal<LogRequest> threadLocal = new InheritableThreadLocal<>();

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
        LogRequest.getInstance();
        Set<LogRequest> requests= Sets.newConcurrentHashSet();
        for (int j = 0; j < 100; j++) {
            Thread.sleep(100);
            executor.execute(() -> {
                LogRequest request=LogRequest.getInstance();
                requests.add(request);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                executor.execute(() -> {
                    LogRequest b = LogRequest.getInstance();
                    requests.add(b);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                });
            });
        }
        assert requests.size()==1;
        executor.awaitTermination(1,TimeUnit.MINUTES);
    }
}
