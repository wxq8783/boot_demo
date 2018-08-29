package com.wu.thread.cpt3.cpt3_6.cpt3_6_2;

public class JoinExceptionThreadC extends Thread {
    private JoinExceptionThreadB threadB;

    public JoinExceptionThreadC(JoinExceptionThreadB threadB) {
        this.threadB = threadB;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"===startC");
        threadB.setName("线程B");
        //threadB.start();
        threadB.interrupt();
        System.out.println(Thread.currentThread().getName()+"===endC");
    }
}
