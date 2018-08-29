package com.wu.thread.cpt2.cpt2_1.cpt2_1_4;

public class Sub2_1 extends Main2_1 {
    @Override
    public void serviceMethod() {
        try {
            System.out.println(" int sub 下一步 sleep beigin threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(" int sub 下一步 sleep end threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            super.serviceMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
