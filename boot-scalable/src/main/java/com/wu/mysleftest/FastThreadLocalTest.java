package com.wu.mysleftest;//package io.netty.example.mysleftest;
//
//import io.netty.util.concurrent.FastThreadLocal;
//
//public class FastThreadLocalTest {
//
//
//
//    public static void main(String[] args) throws InterruptedException {
//        new Thread(() -> {
//             FastThreadLocal<Object> fastThreadLocal = new FastThreadLocal<Object>(){
//                @Override
//                protected Object initialValue() throws Exception {
//                    return new Object();
//                }
//            };
//            fastThreadLocal.get();
//        }).start();
//
//        new Thread(()->{
//            FastThreadLocal<Object> fastThreadLocal = new FastThreadLocal<Object>(){
//                @Override
//                protected Object initialValue() throws Exception {
//                    return new Object();
//                }
//            };
//            fastThreadLocal.get();
//        }).start();
//        Thread.sleep(10000000);
//    }
//}
