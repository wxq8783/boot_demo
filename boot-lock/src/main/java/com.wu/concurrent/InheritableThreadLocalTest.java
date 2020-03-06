package com.wu.concurrent;

public class InheritableThreadLocalTest {

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        System.out.println("==============");
        inheritableThreadLocal.set("InheritableThreadLocal main thread");
        threadLocal.set("ThreadLocal main thread");
        new MyThread().start();
        System.out.println("---------------");
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(inheritableThreadLocal.get());//InheritableThreadLocal main thread
            System.out.println(threadLocal.get());//null
        }
    }
}
