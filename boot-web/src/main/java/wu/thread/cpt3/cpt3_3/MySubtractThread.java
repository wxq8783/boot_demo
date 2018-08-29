package com.wu.thread.cpt3.cpt3_3;

public class MySubtractThread extends Thread {
    private MySubtractNum mySubtractNum;

    public MySubtractThread(MySubtractNum mySubtractNum) {
        this.mySubtractNum = mySubtractNum;
    }

    @Override
    public void run() {
        mySubtractNum.substract();
    }
}
