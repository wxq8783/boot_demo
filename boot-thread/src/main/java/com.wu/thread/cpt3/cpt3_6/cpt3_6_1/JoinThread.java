package com.wu.thread.cpt3.cpt3_6.cpt3_6_1;

public class JoinThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println(Thread.currentThread().getName()+"---start");
            for(int i=0;i< 3;i++){
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"---i="+i);
            }
            System.out.println(Thread.currentThread().getName()+"---end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * join()方法是joinThread线程结束后 才运行main线程后面的语句
     * @param args
     */
    public static void main(String[] args) {
        try {
            JoinThread thread = new JoinThread();
            thread.setName("joinThread");
            thread.start();
            thread.join();//观察 join() 加和没有加的区别
            System.out.println(Thread.currentThread().getName()+"+++打印的前or后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
