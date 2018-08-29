package com.wu.thread.cpt2.cpt2_2.cpt2_2_1;

public class Task2_2 {
    private String getData1;
    private String getDate2;

//    public synchronized void doLongTimeTask(){
//        try {
//            System.out.println("begin task");
//            Thread.sleep(3000);
//            getData1 = "任务1 threadName="+Thread.currentThread().getName();
//            System.out.println(getData1+" time:"+System.currentTimeMillis());
//            Thread.sleep(1000);
//            getDate2 = "任务2 threadName="+Thread.currentThread().getName();
//            System.out.println(getDate2 +" time:"+System.currentTimeMillis());
//
//            System.out.println("end task");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    public  void doLongTimeTask(){
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            synchronized (this) {
                getData1 = "任务1 threadName="+Thread.currentThread().getName();
                System.out.println(getData1+" time:"+System.currentTimeMillis());
                Thread.sleep(1000);
                getDate2 = "任务2 threadName="+Thread.currentThread().getName();
                System.out.println(getDate2 +" time:"+System.currentTimeMillis());
            }

            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
