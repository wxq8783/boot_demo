package com.wu.thread.cpt3.cpt3_2;

public class MyListThreadRun {
    /**
     * 说明 notify方法执行后 并不立即释放锁
     * @param args
     */
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            MyListWaitThread waitThread = new MyListWaitThread(lock);
            waitThread.start();
            Thread.sleep(100);
            MyListNotifyThread notifyThread = new MyListNotifyThread(lock);
            notifyThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
