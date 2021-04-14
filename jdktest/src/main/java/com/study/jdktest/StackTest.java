package com.study.jdktest;


import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/25
 * @creat_time: 10:52
 * @since 7.2.0
 * -Xss512k
 */
public class StackTest {

    public static final AtomicLong count = new AtomicLong();

    public void testStack() throws InterruptedException {
        count.incrementAndGet();
        testStack();
    }

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("shutdown ....")));
        Thread t = new Thread(() -> {
            try {
                new StackTest().testStack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setUncaughtExceptionHandler((t1, e) -> {
            System.out.println(count.get());
        });
        t.setDaemon(false);
        t.start();

      Thread.sleep(10000);


    }
}
