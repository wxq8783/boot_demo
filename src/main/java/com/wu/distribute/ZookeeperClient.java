package com.wu.distribute;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperClient {
    private final static String CONNECTIONsTRING="47.98.195.145:2181";

    public static int sessionTimeout = 5000;

    public static ZooKeeper newInstance() throws InterruptedException, IOException {
        final CountDownLatch downLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTIONsTRING, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    downLatch.countDown();
                }
            }
        });
        downLatch.await();
        return zooKeeper;
    }
}
