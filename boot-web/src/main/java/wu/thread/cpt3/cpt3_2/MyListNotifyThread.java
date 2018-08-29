package com.wu.thread.cpt3.cpt3_2;


public class MyListNotifyThread extends Thread {
    private Object lock;

    public MyListNotifyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            for(int i=0;i<10;i++){
                MyList.addItme();
                System.out.println("++++++++++++++++");
                if(MyList.size() == 5){
                    System.out.println("++notify begin , time="+System.currentTimeMillis());
                    lock.notify();
                    System.out.println("++已经发出通知 ,time="+System.currentTimeMillis());
                }
                System.out.println("++添加了"+(i+1)+"个元素");
            }

        }

    }
}
