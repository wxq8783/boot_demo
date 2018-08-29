package com.wu.lock;

import com.wu.lock.zookeeperlock.AbstractDistributeLock;

import java.util.concurrent.TimeUnit;

public class LockTest {

    public static void main(String[] args) throws Exception {

        Runnable runnable = new Runnable() {
            public void run() {
                AbstractDistributeLock distributeLock = null ;
                try {
                    distributeLock = new AbstractDistributeLock(Thread.currentThread().getName());
                    distributeLock.lock();
                    //secskill();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (distributeLock != null) {
                        distributeLock.unLock();
                    }
                }
            }
        };

        for (int i = 0; i <2; i++) {
            Thread t = new Thread(runnable);
            t.start();
            //TimeUnit.SECONDS.sleep(1L);
        }


    }
}
