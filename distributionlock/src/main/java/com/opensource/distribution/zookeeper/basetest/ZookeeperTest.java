package com.opensource.distribution.zookeeper.basetest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Aaron on 16/9/16.
 */
public class ZookeeperTest implements Watcher {
    private static CountDownLatch cdl = new CountDownLatch(1);

    public static void main(String args[]) throws Throwable {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:32770", 10000,
                new ZookeeperTest());
        cdl.await();
        System.out.println(zooKeeper.getSessionId());
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            cdl.countDown();
        }

    }
}
