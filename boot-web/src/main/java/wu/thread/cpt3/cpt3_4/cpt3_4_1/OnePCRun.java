package com.wu.thread.cpt3.cpt3_4.cpt3_4_1;

public class OnePCRun {
    /**
     * 一个的生产者和消费者
     * @param args
     */
    public static void main(String[] args) {
        Object lock = new Object();

        OneProduce produce = new OneProduce(lock);

        OneCumsure cumsure = new OneCumsure(lock);

        OneProductThread productThread = new OneProductThread(produce);
        productThread.start();

        OneCumsureThread cumsureThread = new OneCumsureThread(cumsure);
        cumsureThread.start();
    }
}
