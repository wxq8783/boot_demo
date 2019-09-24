package com.wu.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GuavaLimitDemo {

    /**
     * 我们只需要告诉RateLimiter系统限制的QPS是多少，
     * 那么RateLimiter将以这个速度往桶里面放入令牌，然后请求的时候，通过tryAcquire()方法向RateLimiter获取许可（令牌）。
     */
    public static ConcurrentHashMap<String,RateLimiter> resourceRateLimit = new ConcurrentHashMap<>();

    static {
        createResourceLimit("order",50);
    }

    public static void createResourceLimit(String resource , double qps){
        if(resourceRateLimit.contains(resource)){
            resourceRateLimit.get(resource).setRate(qps);
        }else{
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimit.putIfAbsent(resource,rateLimiter);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<500;i++){
            new Thread(()->{
                if(resourceRateLimit.get("order").tryAcquire(10,TimeUnit.MILLISECONDS)){
                    System.out.println("执行业务逻辑"+Thread.currentThread().getName());
                }else{
                    System.out.println("限流操作"+Thread.currentThread().getName());
                }
            }).start();

        }

    }
}
