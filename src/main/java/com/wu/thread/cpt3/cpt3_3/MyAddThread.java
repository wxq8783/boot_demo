package com.wu.thread.cpt3.cpt3_3;

public class MyAddThread extends Thread {
    private MyAddNum myAddNum;

    public MyAddThread(MyAddNum myAddNum) {
        this.myAddNum = myAddNum;
    }

    @Override
    public void run() {
        myAddNum.add();
    }
}
