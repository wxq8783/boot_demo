package com.wu.jmm;

import sun.misc.Unsafe;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现两个线程交替打印奇数和偶数
 */
public class ThreadPrintData {

    private static volatile boolean flag = false;

    private static AtomicLong num = new AtomicLong(1);

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition oneCondition = reentrantLock.newCondition();
    private static Condition twoCondition = reentrantLock.newCondition();


    public static void main1(String[] args) throws InterruptedException, IOException {
        new Thread(()->{
            while(num.get() < 100){
                try {
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName()+":"+num.getAndIncrement());
                    oneCondition.await();
                    twoCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        }).start();

        new Thread(()->{
            while(num.get() < 100) {
                try {
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName()+":"+num.getAndIncrement());
                    oneCondition.signal();
                    twoCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        }).start();

        System.in.read();
    }

    static Integer data = 1;
    static Object synchObject = new Object();
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            while(data < 100){
                synchronized (synchObject){
                    if(data%2 != 0){
                        System.out.println(Thread.currentThread().getName()+"------奇数"+data);
                        data = data + 1;
                        synchObject.notifyAll();
                    }else{
                        try {
                            synchObject.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
        t1.setName("t1");
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(()->{
            while(data < 100){
                synchronized (synchObject){
                    if(data%2 == 0){
                        System.out.println(Thread.currentThread().getName()+"+++++++偶数"+data);
                        data = data + 1;
                        synchObject.notifyAll();
                    }else{
                        try {
                            synchObject.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t2.setName("t2");
        t2.start();
    }
}
