package com.opensource.gcstudy;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class YongGcTest {

    public static void createObj(String name){
        new GCBean(name);
    }

    public static void main(String[] args) {
        AtomicLong count=new AtomicLong();
        Executor e= Executors.newCachedThreadPool(new ThreadFactory(){
            private final AtomicInteger index = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                Thread t=new Thread(r);
                t.setName("YongGCTestThread-"+index.incrementAndGet());
                return t;
            }
        });
        for (int i = 0; i < 10; i++)

        e.execute(new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
                        Thread.sleep(10) ;
                        createObj(count.incrementAndGet()+"");
                        if(count.get()%10000==0){
                            System.out.println(count.get());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
