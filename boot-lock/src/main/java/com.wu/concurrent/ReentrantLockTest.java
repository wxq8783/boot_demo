package com.wu.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>>t1 running");
                test.logic();
            }
        });
        t1.setName("t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>>t2 running");
                test.logic();
            }
        });
        t2.setName("t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>>t3 running");
                test.logic();
            }
        });
        t3.setName("t3");

        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t3.start();
        t2.interrupt();
        //Thread.sleep(1000);

    }

    public  void logic(){
        String threadName = Thread.currentThread().getName();
        try {
            lock.lockInterruptibly();//.lock();
            System.out.println(">>>线程名称:"+threadName);
            Thread.sleep(1000000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
