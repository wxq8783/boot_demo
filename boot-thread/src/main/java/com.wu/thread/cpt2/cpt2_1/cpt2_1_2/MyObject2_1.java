package com.wu.thread.cpt2.cpt2_1.cpt2_1_2;

public class MyObject2_1 {

    //public void methodA(){
    synchronized public void methodA(){
        try {
            System.out.println("begin methodA threadName="+Thread.currentThread().getName());
            Thread.sleep(4000);
            System.out.println("end endTime="+System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void methodB(){
        try {
            System.out.println("begin methodB threadName="+Thread.currentThread().getName());
            Thread.sleep(4000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
