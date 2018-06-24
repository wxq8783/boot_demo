package com.wu.lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ClientBase {
    static final String CONNECT_ADDR = "47.98.195.145:2181";
    static final int SESSION_TIMEOUT = 5000;
    public ZooKeeper createClient() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_ADDR, SESSION_TIMEOUT, null);
        return zooKeeper;
    }
}
