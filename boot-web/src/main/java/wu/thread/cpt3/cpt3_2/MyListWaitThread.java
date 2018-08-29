package com.wu.thread.cpt3.cpt3_2;

public class MyListWaitThread extends Thread {
    private Object lock;

    public MyListWaitThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                System.out.println("0----------------------0");
                if(MyList.size() != 5){
                    System.out.println("--wait begin ,time="+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("--wait end ,time="+System.currentTimeMillis());
                }
                System.out.println("1----------------------1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
