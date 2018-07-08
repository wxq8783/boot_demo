package com.wu.thread.cpt3.cpt3_6.cpt3_6_2;

public class JoinExceptionThreadA extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"---start");
            for(int i=0;i<30;i++){
                System.out.println(Thread.currentThread().getName()+"---i="+i);
                Thread.sleep(1000);
            }
            System.out.println(Thread.currentThread().getName()+"---end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
