package com.wu.memery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FullDemo {
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,new ThreadPoolExecutor.DiscardOldestPolicy());
    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);
        for(int i=0;i<Integer.MAX_VALUE;i++){
            buildBar();
            Thread.sleep(10);
        }
    }

    private static void buildBar(){
        List<FutureContract> futureContractList = getAllFutureContract();
        futureContractList.forEach(contract ->{
            executor.scheduleWithFixedDelay(()->{
                try {
                    doFutureContract(contract);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },2,3,TimeUnit.SECONDS);
        });
    }

    private static void doFutureContract(FutureContract futureContract){

    }

    private static List<FutureContract> getAllFutureContract(){
        List<FutureContract> futureContractList = new ArrayList<>();
        for(int i=0;i<100;i++){
            FutureContract contract = new FutureContract(i);
            futureContractList.add(contract);
        }
        return futureContractList;
    }
}
