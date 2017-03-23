package com.opensource;

import lombok.Data;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Aaron on 09/03/2017.
 */
public class TestThread {
    @Data
    static class A {
        private String abc;

        public A(String abc) {
            this.abc = abc;
        }

        public void execute() throws InterruptedException {
            System.out.println(Thread.currentThread().getId() + "---" + abc);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + "---" + abc);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        A a = new TestThread.A("" + i);
        Thread t = new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                try {
                    Thread.sleep(100);
                    System.out.println("12");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.setAbc(j + "");
            }
        });
                t.setDaemon(false);
        t.start();


    }
}
