package com.wu.thread.cpt2.cpt2_2.cpt2_2_3;

public class SyncServiceClassRun {
    /**
     * Class锁可以对类的所有对象实例起到加锁的作用
     * @param args
     */
    public static void main(String[] args) {
       SyncServiceClass syncServiceClass1 = new SyncServiceClass();

       SyncServiceClass syncServiceClass2 = new SyncServiceClass();

       SyncThreadA threadA = new SyncThreadA(syncServiceClass1);
       threadA.setName(" A ");
       threadA.start();

       SyncThreadB threadB = new SyncThreadB(syncServiceClass2);
       threadB.setName(" B ");
       threadB.start();;
    }
}
