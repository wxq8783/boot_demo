package com.wu.thread.cpt4.cpt4_2;

public class ConditionThreadB extends Thread {
    private ConditionMyService service;

    public ConditionThreadB(ConditionMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.signalMethod();
    }
}
