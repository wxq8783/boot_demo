package com.wu.thread.cpt3.cpt3_7;

public class JoinSleepThreadA extends Thread {
    private JoinSleepThreadB threadB;

    public JoinSleepThreadA(JoinSleepThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        try {
            synchronized (threadB){
                threadB.start();
                //Thread.sleep(6000);
                threadB.join();
                for(int i=0;i<Integer.MAX_VALUE;i++){
                    System.out.println("---i="+i);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
