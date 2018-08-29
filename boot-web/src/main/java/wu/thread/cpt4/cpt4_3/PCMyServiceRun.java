package com.wu.thread.cpt4.cpt4_3;

public class PCMyServiceRun {
    public static void main(String[] args) {
        PCMyService service = new PCMyService();

        PThread pThread = new PThread(service);
        pThread.start();

        CThread cThread = new CThread(service);
        cThread.start();
    }
}
