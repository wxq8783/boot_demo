package com.wu.thread.cpt2.cpt2_2.cpt2_2_1;

public class MyTreadA2_2 extends Thread{
    private Task2_2 task22;

    public MyTreadA2_2(Task2_2 task22) {
        super();
        this.task22 = task22;
    }

    @Override
    public void run() {
        super.run();

        CommonUtils.beginTime1 = System.currentTimeMillis();
        task22.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}
