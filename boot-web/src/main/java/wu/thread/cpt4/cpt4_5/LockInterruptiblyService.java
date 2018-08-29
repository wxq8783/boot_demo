package com.wu.thread.cpt4.cpt4_5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void waitMethod(){
        try {
            lock.lockInterruptibly();
            System.out.println("lock begin ;name="+Thread.currentThread().getName());
            for(int i=0;i<Integer.MAX_VALUE / 10;i++){
                String newString = new String();
                Math.random();
            }
            System.out.println("lock end ; name="+Thread.currentThread().getName());
        }catch (Exception e){
            System.out.println("catch end");
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        try {
            final LockInterruptiblyService service = new LockInterruptiblyService();
            Runnable runnableRef = new Runnable() {
                @Override
                public void run() {
                    service.waitMethod();
                }
            };
            Thread threadA = new Thread(runnableRef);
            threadA.setName(" A ");
            threadA.start();
            Thread.sleep(500);

            Thread threadB = new Thread(runnableRef);
            threadB.setName(" B ");
            threadB.start();
            threadB.interrupt();
            System.out.println(" main end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
