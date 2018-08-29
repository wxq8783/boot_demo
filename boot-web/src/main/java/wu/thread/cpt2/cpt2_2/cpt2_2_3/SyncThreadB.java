package com.wu.thread.cpt2.cpt2_2.cpt2_2_3;

public class SyncThreadB extends Thread {
    private SyncServiceClass syncServiceClass;

    public SyncThreadB(SyncServiceClass syncServiceClass) {
        super();
        this.syncServiceClass = syncServiceClass;
    }

    @Override
    public void run() {
        syncServiceClass.printB();
    }
}
