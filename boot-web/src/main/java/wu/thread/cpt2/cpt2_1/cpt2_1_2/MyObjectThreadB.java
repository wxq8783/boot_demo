package com.wu.thread.cpt2.cpt2_1.cpt2_1_2;

public class MyObjectThreadB extends Thread{
    private MyObject2_1 myObject21;

    public MyObjectThreadB(MyObject2_1 myObject21) {
        super();
        this.myObject21 = myObject21;
    }

    @Override
    public void run() {
        super.run();
        myObject21.methodA();
    }
}
