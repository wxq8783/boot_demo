package com.wu.thread.cpt3.cpt3_7;

public class JoinSleepThreadC extends Thread {
    private JoinSleepThreadB threadB;

    public JoinSleepThreadC(JoinSleepThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.bService();
    }
}
