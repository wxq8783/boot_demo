package com.wu.thread.cpt1;

public class MyThread_1_7 extends Thread {

//    @Override
//    public void run() {
//        super.run();
//        for(int i=0;i<10000;i++){
//            if(this.interrupted()){
//                System.out.println("已经是停止状态了，我要退出");
//                break;
//            }
//            System.out.println("i="+(i+1));
//        }
//        System.out.println("for 之后继续运行");
//    }


    @Override
    public void run() {
        super.run();
        try{
            for(int i=0;i<10000;i++){
                if(this.interrupted()){
                    System.out.println("已经是停止状态了，我要退出");
                    throw new InterruptedException();
                }
                System.out.println("i="+(i+1));
            }
            System.out.println("for 之后继续运行");
        }catch (InterruptedException e){
            System.out.println("被catch掉了");
            e.fillInStackTrace();
        }

    }
}
