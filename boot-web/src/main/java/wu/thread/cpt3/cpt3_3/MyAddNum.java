package com.wu.thread.cpt3.cpt3_3;



import com.wu.thread.cpt3.cpt3_2.MyList;

public class MyAddNum {
    private String lock;

    public MyAddNum(String lock){
        this.lock = lock ;
    }

    public void add(){
        synchronized (lock){
            MyList.list.add("anything");
            lock.notifyAll();
        }
    }

}
