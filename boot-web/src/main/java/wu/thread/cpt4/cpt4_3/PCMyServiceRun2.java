package com.wu.thread.cpt4.cpt4_3;

public class PCMyServiceRun2 {
    public static void main(String[] args) {
        PCMyService service = new PCMyService();
        PThread[] pThreads = new PThread[10];
        CThread[] cThreads = new CThread[10];
        for(int i=0;i<10;i++){
            pThreads[i] = new PThread(service);
            cThreads[i] = new CThread(service);
            pThreads[i].start();
            cThreads[i].start();
        }
    }
}
