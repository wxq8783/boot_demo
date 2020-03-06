package com.wu.jmm;

public class VolatileVisibilityTest {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("wait .....");
            while (!flag){

            }
            System.out.println("end........");
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            updateFlag();
        }).start();

    }

    private static void updateFlag(){
        System.out.println("before update......");
        flag = true;
        System.out.println("after update......");
    }
}
