package com.wu.lock.zookeeperlock;

import java.util.concurrent.TimeUnit;

public interface DistributeLock {
    public void lock();

    public boolean tryLock();

    public boolean lock(long time, TimeUnit unit);

    public void unLock();
}
