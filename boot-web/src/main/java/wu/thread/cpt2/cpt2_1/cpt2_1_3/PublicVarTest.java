package com.wu.thread.cpt2.cpt2_1.cpt2_1_3;

public class PublicVarTest {
    /**
     *脏读一定出现在操作实例变量的情况下，这就是不同线程“争抢”实例变量的结果
     */
    public static void main(String[] args) {

        try {
            PublicVar21 publicVar21 = new PublicVar21();
            PublicVarThreadA threadA = new PublicVarThreadA(publicVar21);
            threadA.start();
            Thread.sleep(1000);//getValue不加锁时，此时间可以影响结果
            publicVar21.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
