package com.wu.thread.cpt3.cpt3_4.cpt3_4_2;

import com.wu.thread.cpt3.cpt3_4.ValueObject;
import org.springframework.util.StringUtils;

public class MoreProduct {
    private Object lock;

    public MoreProduct(Object lock) {
        this.lock = lock;
    }

    public void setValue(){
        try {
            synchronized (lock){
                if(!StringUtils.isEmpty(ValueObject.value)){
                    System.out.println("--生产者："+Thread.currentThread().getName()+ " WAITING**");
                    lock.wait();
                }
                System.out.println("--生产者："+Thread.currentThread().getName()+" RUNNING");
                String value = System.currentTimeMillis()+"_"+System.nanoTime();
                ValueObject.value = value;
                lock.notify();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
