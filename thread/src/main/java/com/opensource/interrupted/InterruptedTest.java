package com.opensource.interrupted;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wansong
 * @date 2023/8/17
 * @apiNote
 **/
public class InterruptedTest {
    @Test
    public void testInterrupted(){
        CountDownLatch countDownLatch=new CountDownLatch(2);
        Thread t1=new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        });
        Thread t2=new Thread(() -> {
           t1.interrupt();
           countDownLatch.countDown();
        });

        t1.start();
        t2.start();
    }
}
