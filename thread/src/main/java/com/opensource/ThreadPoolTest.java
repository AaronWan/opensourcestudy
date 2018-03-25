package com.opensource;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.collections.CollectionUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class ThreadPoolTest {
    static volatile boolean running=true;
    static List<Worker> workers=Lists.newArrayList();
    static LinkedBlockingQueue<Task> taskList= new LinkedBlockingQueue<>();
    public static class Task implements Runnable{
        static volatile int i=0;

        public Task() {
            i++;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId()+"-test---");
        }
    }

    public static class Worker extends Thread{
        public Worker() {
            this.setName("ThreadPoolTest-Worker");
        }

        @Override
        public void run() {
            while (true&&running){
                if(CollectionUtils.isNotEmpty(taskList)){
                    Task task=taskList.poll();
                    if(task!=null){
                        task.run();
                    }
                }
            }
        }
    }
    public void start(){
        workers=Lists.newArrayList(new Worker(),new Worker());
        workers.forEach(item->item.start());
    }
    public void shutdown(){
        ThreadPoolTest.running=false;
    }
    public void execute(Task task){
        taskList.add(task);
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTest poolTest=new ThreadPoolTest();
        for(int i=0;i<100;i++){
            poolTest.execute(new Task());
        }
        poolTest.start();
        System.out.println(getProcessID());
        Thread.sleep(3*1000*60);
        poolTest.shutdown();
    }
    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
    }
}
