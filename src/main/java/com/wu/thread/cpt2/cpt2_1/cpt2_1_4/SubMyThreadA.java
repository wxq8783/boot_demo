package com.wu.thread.cpt2.cpt2_1.cpt2_1_4;

public class SubMyThreadA extends Thread {
    private Sub2_1 sub21;

    public SubMyThreadA(Sub2_1 sub21) {
        super();
        this.sub21 = sub21;
    }

    @Override
    public void run() {
        super.run();
        sub21.serviceMethod();
    }
}
