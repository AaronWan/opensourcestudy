package com.opensource.distribution.zookeeper.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron on 16/8/14.
 */
public class ZkLockManager {
    InterProcessMutex lock = null;

    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(500, 3);
        CuratorFramework curatorZookeeperClient;
        try {
            curatorZookeeperClient = CuratorFrameworkFactory.newClient("localhost:32770", retryPolicy);
            curatorZookeeperClient.start();
            lock = new InterProcessMutex(curatorZookeeperClient, "/lock/opensourcestudy/customerqueue/lock");
        } catch (Exception e) {

        }
    }

    public boolean access() {
        try {
            if (lock.acquire(1l, TimeUnit.MILLISECONDS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        } finally {
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        final ZkLockManager zkLockManager = new ZkLockManager();
        zkLockManager.init();
        new Thread() {
            @Override
            public void run() {
                while (true)
                    try {
                        if (zkLockManager.access()) {
                            System.out.println(currentThread().getId() + "--" + zkLockManager.access());
                            break;
                        }
                    } catch (Exception e) {

                    }
            }
        }.start();

    }
}
