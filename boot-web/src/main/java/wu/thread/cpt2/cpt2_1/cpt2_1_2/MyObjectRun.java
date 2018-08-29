package com.wu.thread.cpt2.cpt2_1.cpt2_1_2;

public class MyObjectRun {
    /**
     * A线程先持有object对象的Lock锁，B线程可以以异步的方法调用object对象中非synchronized类型的方法
     *
     * A线程先持有object对象的Lock锁，B线程如果这时调用object对象中其他synchronized类型的方法时，需要等待，也就是同步<也可以证明 synchronized是对象锁>
     *
     */
    public static void main(String[] args) {

        MyObject2_1 myObject21 = new MyObject2_1();
        MyObjectThreadA threadA = new MyObjectThreadA(myObject21);
        threadA.setName("A");
        MyObjectThreadB threadB = new MyObjectThreadB(myObject21);
        threadB.setName("B");
        threadA.start();
        threadB.start();


    }
}
