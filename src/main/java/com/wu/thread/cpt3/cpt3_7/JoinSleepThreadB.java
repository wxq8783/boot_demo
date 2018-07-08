package com.wu.thread.cpt3.cpt3_7;

public class JoinSleepThreadB extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(" -- b run begin time="+System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(" -- b run end time="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized public void bService(){
        System.out.println(" --打印了bService time="+System.currentTimeMillis());
    }
}
