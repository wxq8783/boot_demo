package com.wu.thread.lockinterface;

import org.joda.time.DateTimeUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService executor = (ScheduledExecutorService) Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(DateTimeUtils.currentTimeMillis()+"--------");
            }
        },0,1,TimeUnit.MINUTES);
    }

}
