package com.wu.thread.cpt2.cpt2_1.cpt2_1_3;

public class PublicVarThreadA extends Thread {
    private PublicVar21 publicVar21;

    public PublicVarThreadA(PublicVar21 publicVar21) {
        super();
        this.publicVar21 = publicVar21;
    }

    @Override
    public void run() {
        super.run();
        publicVar21.setValue("B","BB");
    }
}
