package com.wu.distribute;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistributLock {
    private final  String ROOT_PATH = "/LOCKS";

    private ZooKeeper zooKeeper ;

    private int sessionTimeout ;

    private String lockID;//记录锁节点ID

    private CountDownLatch downLatch = new CountDownLatch(1);;

    private byte[] data = {0,1};

    public DistributLock() throws IOException, InterruptedException {
        this.zooKeeper = ZookeeperClient.newInstance();
        this.sessionTimeout = ZookeeperClient.sessionTimeout;

        try {
            Stat statt = zooKeeper.exists(ROOT_PATH,false);
            if(statt == null){
                zooKeeper.create(ROOT_PATH,new byte[]{1},ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public boolean lock() throws KeeperException, InterruptedException {
        lockID = zooKeeper.create(ROOT_PATH+"/",data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(Thread.currentThread().getName()+"-->创建锁【"+lockID+"】");

        List<String> childrenList =  zooKeeper.getChildren(ROOT_PATH,true);
        SortedSet<String> childSet = new TreeSet<>();
        for(String childLockId : childrenList){
            childSet.add(ROOT_PATH+"/"+childLockId);
        }
        String first = childSet.first();
        if(!StringUtils.isEmpty(first)){
            if(lockID.equals(first)){
                System.out.println(Thread.currentThread().getName()+"-->成功获得锁："+lockID);
                return true;
            }
            TreeSet<String> lastSet = (TreeSet<String>) childSet.headSet(lockID);
            String prevLockId = lastSet.last();
            zooKeeper.exists(prevLockId,new DistributeWatch(downLatch));
            downLatch.await(5000,TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName()+"-->成功获得锁："+lockID);
        }
        return true;
    }

    public boolean unlock(){
        try {
            System.out.println(Thread.currentThread().getName()+"-->开始释放锁【"+lockID+"】");
            zooKeeper.delete(lockID,-1);
            System.out.println(Thread.currentThread().getName()+"-->释放成功");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
       CountDownLatch countDownLatch = new CountDownLatch(10);
       final Random random = new Random();
       for (int i=0;i<10;i++){
           new Thread(new Runnable() {
               @Override
               public void run() {
                    DistributLock lock = null;

                   try {
                       lock = new DistributLock();
                       countDownLatch.countDown();
                       countDownLatch.await();
                       lock.lock();
                       Thread.sleep(random.nextInt(500));
                   } catch (IOException e) {
                       e.printStackTrace();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } catch (KeeperException e) {
                       e.printStackTrace();
                   }finally {
                       if(lock != null){
                           lock.unlock();
                       }
                   }
               }
           }).start();
       }
    }
}
