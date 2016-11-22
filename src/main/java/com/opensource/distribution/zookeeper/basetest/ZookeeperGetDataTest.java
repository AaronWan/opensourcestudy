package com.opensource.distribution.zookeeper.basetest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Aaron on 16/9/16.
 */
public class ZookeeperGetDataTest implements Watcher {
    private static CountDownLatch cdl = new CountDownLatch(1);
    private ZooKeeper zooKeeper =null;
    public ZookeeperGetDataTest() throws IOException {
        zooKeeper=new ZooKeeper("localhost:32770", 10000,
                this);
    }
    public static void main(String args[]) throws Throwable {
        cdl.await();

//        System.out.println(new String(zooKeeper.getData(path,true,new Stat())));
//        zooKeeper.getData(path,new ZookeeperGetDataTest(),new Stat());
//        zooKeeper.setData(path,(System.currentTimeMillis()+"Data").getBytes(),-1);
        Thread.sleep(1000);
    }

    public String setData(String path,byte[] data) throws KeeperException, InterruptedException {
        path=zooKeeper.create("/test-","数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
                .PERSISTENT_SEQUENTIAL);
        return path;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            event.getPath();
            cdl.countDown();
        }

    }
}
