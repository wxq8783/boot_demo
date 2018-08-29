package com.wu.thread.cpt4.cpt4_3;

public class CThread extends Thread {
    private PCMyService service;

    public CThread(PCMyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            try {
                service.getValue();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
