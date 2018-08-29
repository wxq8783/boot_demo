package com.wu.thread.cpt3.cpt3_7;

public class JoinSleepRun {
    public static void main(String[] args) {
        try {
            JoinSleepThreadB threadB = new JoinSleepThreadB();

            JoinSleepThreadA threadA = new JoinSleepThreadA(threadB);
            threadA.start();

            Thread.sleep(1000);

            JoinSleepThreadC threadC = new JoinSleepThreadC(threadB);
            threadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
