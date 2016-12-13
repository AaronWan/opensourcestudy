package com.opensource.lock;

import org.junit.Test;

/**
 * Created by Aaron on 22/11/2016.
 */
public class DeadLock {
    public static final String A="A";
    public static final String B="B";

    public static void main(String[] args) {
        deadLock();
    }
    @Test
    public static void deadLock(){
        Thread t1=new Thread(){
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println(1);
                    }
                }
            }
        };

        Thread t2=new Thread(){
            @Override
            public void run() {
                synchronized (B){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println(2);

                    }
                }
            }
        };
        t1.start();t2.start();
    }
    /**
     * Found one Java-level deadlock:
     =============================
     "Thread-1":
     waiting to lock monitor 0x00007f9892008aa8 (object 0x000000076ac286f0, a java.lang.String),
     which is held by "Thread-0"
     "Thread-0":
     waiting to lock monitor 0x00007f9892006218 (object 0x000000076ac28720, a java.lang.String),
     which is held by "Thread-1"

     Java stack information for the threads listed above:
     ===================================================
     "Thread-1":
     at com.opensource.lock.DeadLock$2.run(DeadLock.java:43)
     - waiting to lock <0x000000076ac286f0> (a java.lang.String)
     - locked <0x000000076ac28720> (a java.lang.String)
     "Thread-0":
     at com.opensource.lock.DeadLock$1.run(DeadLock.java:27)
     - waiting to lock <0x000000076ac28720> (a java.lang.String)
     - locked <0x000000076ac286f0> (a java.lang.String)

     Found 1 deadlock.

     */
}
