package com.wu.thread.cpt4.cpt4_1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockMyService {
    private Lock lock = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    public void methodA(){
        try {
            lock.lock();
            System.out.println("方法A执行开始，线程名："+Thread.currentThread().getName()+"   时间："+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("方法A执行结束，线程名："+Thread.currentThread().getName()+"   时间："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    public void methodB(){
        try {
            lock2.lock();
            System.out.println("方法B执行开始，线程名："+Thread.currentThread().getName()+"   时间："+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("方法B执行结束，线程名："+Thread.currentThread().getName()+"   时间："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }
}
