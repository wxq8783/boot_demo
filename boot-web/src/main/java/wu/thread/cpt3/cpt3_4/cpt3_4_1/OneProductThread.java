package com.wu.thread.cpt3.cpt3_4.cpt3_4_1;

public class OneProductThread extends Thread {
    private OneProduce produce;

    public OneProductThread(OneProduce produce) {
        this.produce = produce;
    }

    @Override
    public void run() {
        while (true) {
            produce.setValue();
        }
    }
}
