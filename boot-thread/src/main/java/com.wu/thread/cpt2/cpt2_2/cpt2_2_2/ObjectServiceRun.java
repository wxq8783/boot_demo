package com.wu.thread.cpt2.cpt2_2.cpt2_2_2;

public class ObjectServiceRun {
    /**
     * 当一个线程访问object对象的一个synchronized(this)同步代码块时，
     * 其他线程对同一个object中使用其他synchronized(this)的同步代码块 将被阻塞 -->synchronized(this)使用的是一个 “对象监视器”
     * @param args
     */
    public static void main(String[] args) {
        ObjectService2_2 objectService22 = new ObjectService2_2();

        ThreadA2_2 threadA22 = new ThreadA2_2(objectService22);
        threadA22.start();;

        ThreadB2_2 threadB22 = new ThreadB2_2(objectService22);
        threadB22.start();
    }
}
