package com.wu.thread.cpt3.cpt3_4.cpt3_4_1;

import com.wu.thread.cpt3.cpt3_4.ValueObject;
import org.springframework.util.StringUtils;

public class OneProduce {
    private Object lock;

    public OneProduce(Object lock) {
        this.lock = lock;
    }

    public void setValue(){
        try {
            synchronized (lock){
                if(!StringUtils.isEmpty(ValueObject.value)){
                    lock.wait();
                }
                String value = System.currentTimeMillis()+"_"+System.nanoTime();
                System.out.println("--set的值："+value);
                ValueObject.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
