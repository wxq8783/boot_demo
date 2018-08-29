package com.wu.thread.cpt3.cpt3_3;

import com.wu.thread.cpt3.cpt3_2.MyList;

public class MySubtractNum {
    private Object lock;

    public MySubtractNum(Object lock) {
        this.lock = lock;
    }

    public void substract(){
        try {
            synchronized (lock){
                while(MyList.list.size() == 0){
                    System.out.println("--wait begin ThreadName="+Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("--wait end ThreadName="+Thread.currentThread().getName());
                }
                MyList.list.remove(0);
                System.out.println("--list size="+MyList.list.size()+" ThreadName="+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
