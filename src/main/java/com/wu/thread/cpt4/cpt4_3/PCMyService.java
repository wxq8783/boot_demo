package com.wu.thread.cpt4.cpt4_3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCMyService {
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private int hasValue = 0;

    public void setValue(){
        try {
            lock.lock();
            while(hasValue == 1){
                System.out.println("--------");
                condition.await();
            }
            System.out.println("打印--");
            hasValue = 1;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void getValue(){
        try {
            lock.lock();
            while (hasValue == 0){
                System.out.println("++++++++++++");
                condition.await();
            }
            System.out.println("打印++");
            hasValue = 0;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
