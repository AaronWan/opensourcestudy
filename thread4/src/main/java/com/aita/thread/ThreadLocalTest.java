package com.aita.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ThreadLocalTest {

    private static ThreadLocal<ThreadLocalTest> threadLocal=new ThreadLocal();
    private String name;

    public static ThreadLocalTest getInstance(){

        if(threadLocal.get()==null){
            threadLocal.set(new ThreadLocalTest(Thread.currentThread().getName()));
        }

        return threadLocal.get();
    }

    public ThreadLocalTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ThreadLocalTest{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final AtomicLong count=new AtomicLong();
        for (; ; ) {
            threadPool.submit(()->{
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
                if(!ThreadLocalTest.getInstance().getName().equals(Thread.currentThread().getName())){
                    System.out.println(ThreadLocalTest.getInstance().getName()+"---"+Thread.currentThread().getName());
                }
                if(count.incrementAndGet()%10000==0){
                    System.out.printf("activeCount:%d,taskCount:%d,completedCount:%d,currentTaskCount:%d\n"
                            ,threadPool.getActiveCount(),threadPool.getTaskCount()+threadPool.getCompletedTaskCount(),
                            threadPool.getCompletedTaskCount(),threadPool.getTaskCount());
                }
            });
        }
    }
}
