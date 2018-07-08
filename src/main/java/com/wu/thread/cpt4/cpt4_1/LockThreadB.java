package com.wu.thread.cpt4.cpt4_1;

public class LockThreadB extends Thread {
    private LockMyService service;

    public LockThreadB(LockMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
