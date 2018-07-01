package com.wu.thread.cpt3.cpt3_4.cpt3_4_1;

public class OneCumsureThread extends Thread {
    private OneCumsure cumsure;

    public OneCumsureThread(OneCumsure cumsure) {
        this.cumsure = cumsure;
    }

    @Override
    public void run() {
        while(true){
            cumsure.getValue();
        }

    }
}
