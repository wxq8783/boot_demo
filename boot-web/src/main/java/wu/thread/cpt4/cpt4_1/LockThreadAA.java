package com.wu.thread.cpt4.cpt4_1;

public class LockThreadAA extends Thread {
    private LockMyService service;

    public LockThreadAA(LockMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
