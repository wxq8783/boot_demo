package com.wu.thread.cpt2.cpt2_2.cpt2_2_1;

public class TaskRun2_2 {
    /**
     * 一个线程访问object对象的一个synchronized(this)同步代码块，
     * 另一个线程可以访问该object对象的其他非synchronized(this)代码块
     * @param args
     */
    public static void main(String[] args) {
        Task2_2 task =new Task2_2();
        MyTreadA2_2 myTreadA = new MyTreadA2_2(task);
        myTreadA.setName("A");
        myTreadA.start();

        MyTreadB2_2 myTreadB = new MyTreadB2_2(task);
        myTreadB.setName("B");
        myTreadB.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long beginTime = CommonUtils.beginTime1;
        if(CommonUtils.beginTime2 < CommonUtils.beginTime1){
            beginTime = CommonUtils.beginTime2;
        }

        long endTime = CommonUtils.endTime1;
        if(CommonUtils.endTime2 > CommonUtils.endTime1){
            endTime = CommonUtils.endTime2;
        }
        System.out.println(" 耗时："+(endTime-beginTime)/1000);
    }
}
