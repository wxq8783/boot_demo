package com.wu.lock.zookeeperlock;

import com.alibaba.fastjson.JSON;
import com.wu.lock.ClientBase;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public  class AbstractDistributeLock extends ClientBase implements DistributeLock {


    public ZooKeeper zooKeeper;
    private static String rootPath = "/distribute";
    private String lockNamePre = "lock-";
    private String currentLockPath;
    private static final int MAX_RETRY_COUNT = 10;
    private String threadName;

    static final String CONNECT_ADDR = "47.98.195.145:2181";
    static final int SESSION_TIMEOUT = 60000;


    public AbstractDistributeLock(String threadName){
        try {
            zooKeeper = createClient();
            this.threadName = threadName ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化根目录
     *
     */
    public void init() throws IOException {
        try {
            Stat stat = zooKeeper.exists(rootPath,false);
            if(stat == null){
                zooKeeper.create(rootPath,null,ZooDefs.Ids.OPEN_ACL_UNSAFE ,CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建锁节点
     * @param lockPath
     */
    public String createLockNode(String lockPath)throws Exception{
        init();
        try {
            String path = zooKeeper.create(lockPath,null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(threadName+"----------创建节点的路径："+path);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<String> getSortedChildren() throws Exception {
        List<String> list = zooKeeper.getChildren(rootPath,false);
        if(!CollectionUtils.isEmpty(list)){
            System.out.println(threadName+"----------子节点排序前的列表："+ JSON.toJSONString(list));
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return getLockNodeNumber(o1).compareTo(getLockNodeNumber(o2));
                }
            });
        }
        System.out.println(threadName+"----------子节点排序后的列表："+JSON.toJSONString(list));
        return list;

    }

    public String getLockNodeNumber(String nodePath){
        int index = nodePath.lastIndexOf(lockNamePre);
        if(index > 0){
            index += lockNamePre.length();
            return index <= nodePath.length() ? nodePath.substring(index) : "";
        }
        return nodePath;
    }
    /**
     * 判断是否获取到锁
     * 自己创建的顺序节点是否最小
     * 如果不是最小，等待其他客户端释放锁
     * @param startMillis
     * @param millisToWait
     * @return
     */
    public boolean waitToLock(long startMillis , Long millisToWait){
        boolean haveTheLock = false ;
        boolean isDelete = false;

        try{
            while (!haveTheLock){
                List<String> children = getSortedChildren();
                String sequenceNodeName = currentLockPath.substring(rootPath.length()+1);
                System.out.println(threadName+"----------获取到节点的路径："+sequenceNodeName);
                int ourIndex = children.indexOf(sequenceNodeName);
                if(ourIndex < 0){
                    System.out.println(threadName+"----------"+sequenceNodeName);
                    throw new Exception(threadName+"----------节点没有找到"+sequenceNodeName);
                }
                boolean isGetTheLock = ourIndex == 0;
                String pathToWatcher = isGetTheLock ? null : children.get(ourIndex -1);
                if(isGetTheLock){
                    System.out.println(threadName+"----------已经获取到锁");
                    haveTheLock = true;
                }else{
                    System.out.println(threadName+"----------没有获取的到锁");
                    String previousSequeencePath = rootPath.concat("/").concat(pathToWatcher);
                    final CountDownLatch latch = new CountDownLatch(1);
                    final Watcher previousListener = new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            System.out.println(threadName+"----------监听节点:"+watchedEvent.getPath());
                            if(watchedEvent.getType() == Event.EventType.NodeDeleted){
                                System.out.println(threadName+"----------节点已删除");
                                latch.countDown();
                            }
                        }
                    };
                    zooKeeper.exists(previousSequeencePath,previousListener);
                    if(millisToWait != null){
                        millisToWait -= (System.currentTimeMillis() - startMillis);
                        startMillis = System.currentTimeMillis();
                        if (millisToWait < 0){
                            isDelete = true;
                            break;
                        }
                        latch.await(millisToWait,TimeUnit.MICROSECONDS);
                    }else{
                        System.out.println(threadName+"----------等待下次获取");
                        latch.await();
                    }
                }
            }


        }catch (Exception e){
            isDelete = true;
            e.printStackTrace();
        }finally {
            if(isDelete){
                System.out.println(threadName+"----------释放锁");
                unLock();
            }
        }
        return haveTheLock;
    }

    /**
     * 获取锁
     * @param time
     * @param unit
     * @return
     * @throws Exception
     */
    public Boolean attemptLock(long time ,TimeUnit unit) throws Exception{
        final long startMillis = System.currentTimeMillis();
        final Long millisToWait = unit == null ? null : unit.toMillis(time);
        boolean hasTheLock = false;
        boolean isDone = false;
        int retryTime = 0;
        while (!isDone){
            isDone = true;
            try{
                currentLockPath = createLockNode(rootPath.concat("/").concat(lockNamePre));
                hasTheLock = waitToLock(startMillis ,millisToWait);
            }catch (Exception e){
                if(retryTime++ < MAX_RETRY_COUNT){
                    isDone = false;
                }else {
                    e.fillInStackTrace();
                }
            }

        }
        return hasTheLock;
    }


    @Override
    public void lock() {
        try {
            attemptLock(-1,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean tryLock() {
        try {
            List<String> children = getSortedChildren();
            String sequenceLockPath = currentLockPath.substring(rootPath.length()+1);
            int ourIndex = children.indexOf(sequenceLockPath);
            if(ourIndex < 0){
                throw new RuntimeException("没有找到节点"+sequenceLockPath);

            }
            return ourIndex == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean lock(long time, TimeUnit unit) {
        if(time < 0)
            throw new RuntimeException("time < 0");
        if(unit == null)
            throw new RuntimeException("unit is null");
        try {
            return attemptLock(time,unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unLock() {
        try {
            System.out.println(threadName+"---------释放锁:"+currentLockPath);
            zooKeeper.delete(currentLockPath,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getLockNamePre() {
        return lockNamePre;
    }

    public void setLockNamePre(String lockNamePre) {
        this.lockNamePre = lockNamePre;
    }

    public String getCurrentLockPath() {
        return currentLockPath;
    }

    public void setCurrentLockPath(String currentLockPath) {
        this.currentLockPath = currentLockPath;
    }
}
