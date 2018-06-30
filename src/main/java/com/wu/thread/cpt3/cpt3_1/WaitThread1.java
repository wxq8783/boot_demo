package com.wu.thread.cpt3.cpt3_1;

public class WaitThread1 extends Thread {
    private Object lock;


    public WaitThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (lock){
                System.out.println("--开始 wait time ="+System.currentTimeMillis());
                lock.wait();
                System.out.println("--结束 wait time="+System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
