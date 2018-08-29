package com.wu.thread.cpt3.cpt3_4.cpt3_4_2;

import com.wu.thread.cpt3.cpt3_4.ValueObject;
import org.springframework.util.StringUtils;

public class MoreCumsure {
    private Object lock;

    public MoreCumsure(Object lock) {
        this.lock = lock;
    }

    public void getValue(){
        try {
            synchronized (lock){
                if(StringUtils.isEmpty(ValueObject.value)){
                    System.out.println("++消费者："+Thread.currentThread().getName()+" WAITING**");
                    lock.wait();
                }
                System.out.println("++消费者："+Thread.currentThread().getName()+" RUNNING@@ ");
                ValueObject.value= "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
