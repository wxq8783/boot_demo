package com.wu.queue;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {

    private static SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        putData("11111");
        Thread.sleep(120000);
        putData("22222");
//        Thread.sleep(3000);
//        putData("33333");
//        Thread.sleep(3000);
//        pollDate();
//        Thread.sleep(3000);
        //pollDate();
        System.in.read();
    }

    private static void putData(final String date) throws InterruptedException {
        queue.put(date);
//        new Thread(()->{
//            try {
//                System.out.println("----开始"+date);
//                queue.put(date); ;
//                System.out.println("----结束"+date);
//            } catch (Exception e) {
//                System.out.println("----异常----");
//                //e.printStackTrace();
//            }
//        }).start();
    }

    private static void pollDate(){
        new Thread(()->{
            try {
                System.out.println("----获取start");
                String date = queue.poll();
                System.out.println("----获取end"+date);
            } catch (Exception e) {
                System.out.println("----异常----");
                //e.printStackTrace();
            }
        }).start();
    }
}
