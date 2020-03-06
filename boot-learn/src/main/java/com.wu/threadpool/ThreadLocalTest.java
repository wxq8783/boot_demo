package com.wu.threadpool;

public class ThreadLocalTest {

    public static void main(String[] args) {
        MyThreadLocal<String> local = new MyThreadLocal<String>();
        System.out.println(local.get());

        local.set("hello");
        System.out.println(local.get());
        local.remove();
        System.out.println(local.get());
    }

   static class MyThreadLocal<T> extends ThreadLocal<T>{
        @Override
        protected T initialValue() {
            return (T)"word";
        }
    }
}
