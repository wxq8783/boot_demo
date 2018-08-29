package com.wu.thread.cpt3.cpt3_1;

public class NotifyThread1 extends Thread {
    private Object lock;

    public NotifyThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (lock) {
                System.out.println("++开始 notify time=" + System.currentTimeMillis());
                lock.notify();
                System.out.println("++结束 notify time="+System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
