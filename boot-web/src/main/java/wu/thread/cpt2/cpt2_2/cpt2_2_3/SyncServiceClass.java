package com.wu.thread.cpt2.cpt2_2.cpt2_2_3;

public class SyncServiceClass {

    synchronized public static void printA(){
        try {
            System.out.println("----线程名称为："+Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+ " 进入A");
            Thread.sleep(3000);
            System.out.println("----线程名称为："+Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+ " 离开A");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    synchronized public static void printB(){
        try {
            System.out.println("++++线程名称为："+Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+ " 进入B");
            Thread.sleep(200);
            System.out.println("++++线程名称为："+Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+ " 离开B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
