package com.wu.thread.lockinterface;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁
 */
public class MutexLock implements Lock {

    /**
     * 静态内部类 自定义同步器
     */
    private static class FairSync extends AbstractQueuedSynchronizer {
        /**
         * 获取独占锁
         * @param acquires
         * @return
         */
        @Override
        protected boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int state = getState();
            if(state == 0){
                if(compareAndSetState(0,acquires)){
                    //获取到锁
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }else if(current == getExclusiveOwnerThread()){
                //如果是获取的锁是同一线程
                int totleState = state + acquires;
                if(totleState < 0){
                    throw  new Error("Maximum lock count exceeded");
                }
                setState(totleState);
                return true;
            }

            return false;
        }

        /**
         * 释放独占锁
         * @param release
         * @return
         */
        @Override
        protected boolean tryRelease(int release) {
            int state = getState() - release;
            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            //释放成功
            if(state == 0){
                free = true ;
                setExclusiveOwnerThread(null);
            }
            setState(state);
            return free;
        }

        /**
         * 是否处于占用状态
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        final ConditionObject newCondition(){
            return new ConditionObject();
        }
    }

    private final FairSync sync = new FairSync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
