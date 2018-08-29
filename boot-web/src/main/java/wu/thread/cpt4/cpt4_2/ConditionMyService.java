package com.wu.thread.cpt4.cpt4_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionMyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod(){
        try {
            lock.lock();
            System.out.println("--wait start");
            for(int i=1;i<10;i++){
                System.out.println("--i="+i);
                Thread.sleep(1000);
                if(i%3 == 0){
                    condition.await();
                }
            }
            System.out.println("-- wait end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalMethod(){
        try {
            lock.lock();
            System.out.println("++ signal start");
            for(int i=1;i<10;i++){
                System.out.println("++j="+i);
                Thread.sleep(1000);
                if(i%3 == 0){
                    condition.signal();
                }
            }
            System.out.println("++signal end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
