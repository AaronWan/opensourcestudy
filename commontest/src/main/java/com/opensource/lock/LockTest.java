package com.opensource.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Aaron on 16/8/12.
 */
public class LockTest {
    static int i=0;
    public synchronized void write(){
        i++;
    }
    public synchronized int read(){
        return i;
    }

    public static void main(String[] args)throws Exception {
        final LockTest lockTest=new LockTest();
        final CountDownLatch countDownLatch=new CountDownLatch(100);
       Thread thread= new Thread(){
            @Override
            public void run() {
                while (true){
                lockTest.write();
                    countDownLatch.countDown();
                    System.out.println("write----------"+countDownLatch.getCount());
            }}
        };
        thread.start();
        thread= new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("read-------"+lockTest.read());
                    countDownLatch.countDown();
                }
            }
        };
        thread.start();
        countDownLatch.await();
    }
}
