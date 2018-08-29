package com.wu.thread.cpt3.cpt3_4.cpt3_4_2;


public class MoreProductThread extends Thread {
    private MoreProduct product;

    public MoreProductThread(MoreProduct product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.setValue();
        }
    }
}
