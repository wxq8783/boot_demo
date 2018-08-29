package com.wu.thread.cpt4.cpt4_1;

public class LockMyServiceRun {
    /**
     * 线程按加锁的顺序执行
     * @param args
     */
    public static void main(String[] args) {
        LockMyService service = new LockMyService();
        LockThreadA threadA = new LockThreadA(service);
        threadA.setName("-线程A-");
        threadA.start();

        LockThreadAA threadAA = new LockThreadAA(service);
        threadAA.setName("+线程AA+");
        threadAA.start();

        LockThreadB threadB = new LockThreadB(service);
        threadB.setName("-线程B-");
        threadB.start();

        LockThreadBB threadBB = new LockThreadBB(service);
        threadBB.setName("+线程BB+");
        threadBB.start();
    }
}
