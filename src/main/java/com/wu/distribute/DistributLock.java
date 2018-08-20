package com.wu.distribute;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
        System.out.println(Thread.currentThread().getName()+"-->创建锁【"+lockID+"】");
        List<String> childrenList =  zooKeeper.getChildren(ROOT_PATH,new DistributeWatch(downLatch));
        SortedSet<String> childSet = new TreeSet<>();
        for(String childLockId : childrenList){
            childSet.add(childLockId);
        }
        String first = childSet.first();
        if(!StringUtils.isEmpty(first)){
            if(lockID.equals(first)){
                System.out.println(Thread.currentThread().getName()+"-->第一次成功获得锁："+lockID);
                return true;
            }
            TreeSet<String> lastSet = (TreeSet<String>) childSet.headSet(first);
            System.out.println("lastSet:"+JSON.toJSONString(lastSet));
            String prevLockId = lastSet.last();
            zooKeeper.exists(ROOT_PATH+"/"+prevLockId,new DistributeWatch(downLatch));
            downLatch.await(sessionTimeout,TimeUnit.MICROSECONDS);
            System.out.println(Thread.currentThread().getName()+"-->Wacther成功获得锁："+lockID);
        }
        return true;
    }

    public boolean unlock(){
        try {
            System.out.println(Thread.currentThread().getName()+"-->开始是否锁【"+lockID+"】");
            zooKeeper.delete(lockID,-1);
            System.out.println("是否成功");
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
