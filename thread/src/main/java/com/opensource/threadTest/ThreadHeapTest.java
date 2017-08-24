package com.opensource.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @since 5.7
 * -Xss64m -Xmx64m
 */
public class ThreadHeapTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(1000,1000,0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
            int i=0;
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("ThreadHeapTest-"+i++);
                return t;
            }
        });
        for (int i = 0; i < 1000; i++) {
            executor.submit(new HeapBean(10000));
        }
    }
    static class HeapBean implements Runnable{

        private List<String> list= new ArrayList<>();
        private int size=0;

        public HeapBean(int size) {
            this.size = size;
        }

        @Override
        public void run() {
            do{
                list.add(new String(size+""));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }while (size>0);
        }
    }
}
