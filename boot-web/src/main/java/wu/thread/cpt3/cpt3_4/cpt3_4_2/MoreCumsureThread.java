package com.wu.thread.cpt3.cpt3_4.cpt3_4_2;



public class MoreCumsureThread extends Thread {
    private MoreCumsure cumsure;

    public MoreCumsureThread(MoreCumsure cumsure) {
        this.cumsure = cumsure;
    }

    @Override
    public void run() {
        while(true){
            cumsure.getValue();
        }

    }
}
