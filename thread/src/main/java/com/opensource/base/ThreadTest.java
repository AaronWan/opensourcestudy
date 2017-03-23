package com.opensource.base;

/**
 * Created by Aaron on 23/03/2017.
 */
public class ThreadTest {
    public static void main(String[] args) {
        testBaseThread();
    }
    public static void testBaseThread(){
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("...");
            }
        });
        t.setDaemon(true);
     t.start();
    }
}
