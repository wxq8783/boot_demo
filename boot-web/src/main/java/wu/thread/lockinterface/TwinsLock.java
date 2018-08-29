package com.wu.thread.lockinterface;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if(count < 0){
                throw new IllegalArgumentException("数字不能小于0");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            for(;;){
                int current = getState();
                int newAcquire = current - acquires;
                if(newAcquire < 0 && compareAndSetState(current,newAcquire)){
                    return newAcquire;
                }

            }
        }

        @Override
        protected boolean tryReleaseShared(int release) {
            for(;;){
                int current = getState();
                int newRelease = current + release;
                if(compareAndSetState(current,newRelease)){
                    return true;
                }
            }
        }
    }

    private Sync sync = new Sync(2);
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
