package com.wu.thread.cpt1;

public class Run_cpt1_7 {
    public static void main(String[] args) {
        try{
            MyThread_1_7 myThread = new MyThread_1_7();
            myThread.start();
            Thread.sleep(40);
            myThread.interrupt();


        }catch (InterruptedException e){
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("-----end");
    }
}
