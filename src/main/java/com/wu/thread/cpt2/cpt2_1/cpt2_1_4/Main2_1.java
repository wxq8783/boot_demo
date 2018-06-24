package com.wu.thread.cpt2.cpt2_1.cpt2_1_4;

public class Main2_1 {
    synchronized public void serviceMethod(){
        try {
            System.out.println(" int main 下一步 sleep beigin threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(" int main 下一步 sleep end threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
