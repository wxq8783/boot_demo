package com.wu.juc;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch downLatch = new CountDownLatch(3);

        for(int i = 0;i<3;i++){
            final int num = i;
            System.out.println("---"+i);

            new Thread(()->{
                try {
                    Thread.sleep(10000);
                    System.out.println("+++"+num);
                   downLatch.countDown();
                    System.out.println("==="+num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        downLatch.await();
        System.out.println(2);
        System.in.read();
    }
}
