package com.wu.thread.cpt3.cpt3_4.cpt3_4_2;

public class MorePCRun {
    public static void main(String[] args) {
        try {
            Object lock = new Object();

            MoreProduct produce = new MoreProduct(lock);

            MoreCumsure cumsure = new MoreCumsure(lock);

            MoreProductThread[] productThread = new MoreProductThread[2];
            MoreCumsureThread[] cumsureThread = new MoreCumsureThread[2];

            for(int i=0;i<2;i++){
                productThread[i] = new MoreProductThread(produce);
                productThread[i].setName("<生产线程"+(i+1)+">");


                cumsureThread[i] = new MoreCumsureThread(cumsure);
                cumsureThread[i].setName("<消费线程"+(i+1)+">");

                productThread[i].start();
                cumsureThread[i].start();
            }
            Thread.sleep(5000);
            Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
            Thread.currentThread().getThreadGroup().enumerate(threadArray);
            for(int i=0 ;i<threadArray.length;i++){
                System.out.println(threadArray[i].getName()+"  "+threadArray[i].getState());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
