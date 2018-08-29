package com.wu.thread.cpt4.cpt4_2;

public class ConditionThreadA extends Thread {
    private ConditionMyService service;

    public ConditionThreadA(ConditionMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethod();
    }
}
