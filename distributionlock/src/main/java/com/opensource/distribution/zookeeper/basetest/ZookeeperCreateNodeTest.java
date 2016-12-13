package com.opensource.distribution.zookeeper.basetest;

import com.google.common.collect.Lists;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Aaron on 16/9/16.
 */
public class ZookeeperCreateNodeTest implements Watcher {
    private static CountDownLatch cdl = new CountDownLatch(1);

    public static void main(String args[]) throws Throwable {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:32770", 10000,
                new ZookeeperCreateNodeTest());
        cdl.await();
        System.out.println(zooKeeper.create("/test-","数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
                .PERSISTENT_SEQUENTIAL));

        zooKeeper.create("/test2-", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL, (rc, path, ctx, name) -> System.out.println(rc+"--"+path+"--"+ctx+"--"+name),"context");
        ThreadFactory threadFactory= r -> {
            Thread t=new Thread(r);
            t.setName("Study-"+System.currentTimeMillis());
            return t;
        };
        Runtime.getRuntime().addShutdownHook(threadFactory.newThread(() -> System.err.println(Thread.currentThread()
                .getName()+"--Shutdown")));
        Thread.sleep(1000);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            cdl.countDown();
        }

    }
}
