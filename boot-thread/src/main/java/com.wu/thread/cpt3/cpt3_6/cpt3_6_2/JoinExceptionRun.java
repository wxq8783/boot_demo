package com.wu.thread.cpt3.cpt3_6.cpt3_6_2;

public class JoinExceptionRun {
    /**
     * B线程异常，不影响A线程的运行
     * @param args
     */
    public static void main(String[] args) {
        try {
            JoinExceptionThreadB threadB = new JoinExceptionThreadB();
            threadB.setName("线程BB+");
            threadB.start();
            Thread.sleep(2000);
            JoinExceptionThreadC threadC = new JoinExceptionThreadC(threadB);
            threadC.setName("线程C");
            threadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
