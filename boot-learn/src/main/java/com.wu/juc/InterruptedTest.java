package com.wu.juc;

import java.io.IOException;

public class InterruptedTest {
    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(()->{
            try {

                Thread.sleep(1000000);

            } catch (InterruptedException e) {

                System.out.println("线程被打断1");
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            System.out.println("线程被打断2");
        }
        System.out.println("-----");
        thread.interrupt();

        System.in.read();
    }
}
