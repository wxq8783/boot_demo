package com.wu.thread.cpt3.cpt3_6.cpt3_6_2;

public class JoinExceptionThreadB extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"---run start");
            JoinExceptionThreadA threadA = new JoinExceptionThreadA();
            threadA.setName("线程A");
            threadA.start();
            threadA.join();
            System.out.println(Thread.currentThread().getName()+"---run end");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"---catch  end");
            e.printStackTrace();
        }
    }
}
