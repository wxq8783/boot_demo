package com.wu.thread.cpt3.cpt3_3;

public class MyNumRun {
    public static void main(String[] args) {
        try {
            String lock = new String(" ");
            MyAddNum addNum = new MyAddNum(lock);
            MySubtractNum subtractNum = new MySubtractNum(lock);

            MySubtractThread subtractThread = new MySubtractThread(subtractNum);
            subtractThread.setName("sub111");
            subtractThread.start();

            MySubtractThread subtractThread2 = new MySubtractThread(subtractNum);
            subtractThread2.setName("sub222");
            subtractThread2.start();
            Thread.sleep(1000);
            MyAddThread addThread = new MyAddThread(addNum);
            addThread.setName("add000");
            addThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
