package com.wu.thread.cpt2.cpt2_1.cpt2_1_1;

public class ThreadA_2_1 extends Thread{
    private HasSelfPrivateNum hasSelfPrivateNum;

    public ThreadA_2_1(HasSelfPrivateNum hasSelfPrivateNum){
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("a");
    }
}
