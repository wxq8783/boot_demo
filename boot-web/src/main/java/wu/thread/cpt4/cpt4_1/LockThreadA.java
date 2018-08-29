package com.wu.thread.cpt4.cpt4_1;

public class LockThreadA extends Thread {
    private LockMyService service;

    public LockThreadA(LockMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
