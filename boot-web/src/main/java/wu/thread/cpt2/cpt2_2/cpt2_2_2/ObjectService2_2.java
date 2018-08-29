package com.wu.thread.cpt2.cpt2_2.cpt2_2_2;

public class ObjectService2_2 {
    public void serviceMethodA(){
        synchronized (this){
            try {
                System.out.println("----A begin time ="+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("----A end time ="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void serviceMethodB(){
        synchronized (this){
            try {
                System.out.println("++++B begin time = "+System.currentTimeMillis());
                Thread.sleep(100);
                System.out.println("++++B begin time = "+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
