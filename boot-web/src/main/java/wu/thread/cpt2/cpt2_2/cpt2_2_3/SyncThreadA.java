package com.wu.thread.cpt2.cpt2_2.cpt2_2_3;

public class SyncThreadA extends Thread {
    private SyncServiceClass syncServiceClass;

    public SyncThreadA(SyncServiceClass syncServiceClass) {
        super();
        this.syncServiceClass = syncServiceClass;
    }

    @Override
    public void run() {
        syncServiceClass.printA();
    }
}
