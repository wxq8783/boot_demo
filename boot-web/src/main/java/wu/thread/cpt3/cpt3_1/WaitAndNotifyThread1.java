package com.wu.thread.cpt3.cpt3_1;

public class WaitAndNotifyThread1 {
    /**
     * 验证 wait() 在代码出停止执行 notify()要执行完当期代码块，才停止执行，并释放锁
     * --开始 wait time =1530355254652
     * ++开始 notify time=1530355257655
     * ++结束 notify time=1530355257655
     * --结束 wait time=1530355257656
     * @param args
     */

    public static void main(String[] args) {
        try {
            Object lock = new Object();
            WaitThread1 thread1 = new WaitThread1(lock);
            thread1.start();
            Thread.sleep(3000);
            NotifyThread1 thread2 = new NotifyThread1(lock);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
