package com.wu.thread.cpt4.cpt4_1;

public class LockThreadBB extends Thread {
    private LockMyService service;

    public LockThreadBB(LockMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
