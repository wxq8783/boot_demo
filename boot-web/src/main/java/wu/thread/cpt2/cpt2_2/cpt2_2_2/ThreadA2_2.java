package com.wu.thread.cpt2.cpt2_2.cpt2_2_2;

public class ThreadA2_2 extends Thread {
    private ObjectService2_2 objectService22;

    public ThreadA2_2(ObjectService2_2 objectService22) {
        super();
        this.objectService22 = objectService22;
    }

    @Override
    public void run() {
        super.run();
        objectService22.serviceMethodA();
    }
}
