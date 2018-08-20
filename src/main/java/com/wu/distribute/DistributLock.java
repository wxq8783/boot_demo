package com.wu.distribute;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class DistributLock {
    private final static String ROOT_PATH = "/LOCKS";

    private ZooKeeper zooKeeper ;

    private int sessionTimeout ;

    private String lockID;//记录锁节点ID

    private CountDownLatch downLatch;

    private byte[] data = {0,1};

    public DistributLock() throws IOException, InterruptedException {
        zooKeeper = ZookeeperClient.newInstance();
    }

    public boolean lock() throws KeeperException, InterruptedException {
        lockID = zooKeeper.create(ROOT_PATH+"/",data,null, CreateMode.EPHEMERAL_SEQUENTIAL);

        List<String> childrenList =  zooKeeper.getChildren(ROOT_PATH,new DistributeWatch(downLatch));
        Set<String> childSet = new TreeSet<>(childrenList);
        if(lockID.equals(((TreeSet<String>) childSet).first())){
            System.out.println(Thread.currentThread().getName()+"-->获得锁："+lockID);
            return true;
        }
        TreeSet<String> ddSet = (TreeSet<String>) ((TreeSet<String>) childSet).headSet(lockID);
        String lastLockId = ((SortedSet<String>) ddSet).last();
        return false;
    }

    public boolean unlock(){
        return false;
    }
}
