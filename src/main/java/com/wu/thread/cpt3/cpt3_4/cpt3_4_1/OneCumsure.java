package com.wu.thread.cpt3.cpt3_4.cpt3_4_1;

import com.wu.thread.cpt3.cpt3_4.ValueObject;
import org.springframework.util.StringUtils;

public class OneCumsure {
    private Object lock;

    public OneCumsure(Object lock) {
        this.lock = lock;
    }

    public void getValue(){
        try {
            synchronized (lock) {
                if(StringUtils.isEmpty(ValueObject.value)){
                    lock.wait();
                }
                System.out.println("++get的值："+ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
