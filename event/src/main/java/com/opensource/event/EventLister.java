package com.opensource.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aaron on 03/02/2017.
 */
public class EventLister {
    @Subscribe
    public void lister(Integer integer) {
        System.out.printf("%d from int%n", integer);
    }
    @Subscribe
    public void listerDouble(Double integer) {
        System.out.printf("%d from int%n", integer);
    }
    static int i=0;
    @Subscribe
    public void lister(String integer) {
        System.out.println(integer);
        try {
            Thread.sleep(1000-(i+=10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int i=0;
        EventBus eventBus=new EventBus();
        eventBus.register(new EventLister());
        eventBus.post("sync\t"+new Integer(i++));
        eventBus.post("sync\t"+new Integer(i++));
        eventBus.post("sync\t"+new Integer(i++));
        eventBus.post("sync\t"+new Integer(i++));
        ExecutorService executor=Executors.newCachedThreadPool();
        AsyncEventBus asyncEventBus=new AsyncEventBus(executor);
        asyncEventBus.register(new EventLister());
        asyncEventBus.post("async\t"+new Integer(i++));
        asyncEventBus.post("async\t"+new Integer(i++));
        asyncEventBus.post("async\t"+new Integer(i++));
        asyncEventBus.post("async\t"+new Integer(i++));
        asyncEventBus.post("async\t"+new Integer(i++));
        asyncEventBus.post("async\t"+new Integer(i++));
        executor.shutdown();

    }
}
