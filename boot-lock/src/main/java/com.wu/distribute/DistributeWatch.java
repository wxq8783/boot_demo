package com.wu.distribute;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class DistributeWatch implements Watcher {
    private CountDownLatch downLatch;

    public DistributeWatch(CountDownLatch downLatch){
        this.downLatch = downLatch;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getType() == Event.EventType.NodeDeleted){
            System.out.println(Thread.currentThread().getName()+"-->收到回调"+watchedEvent.getPath());
            downLatch.countDown();
        }
    }
}
