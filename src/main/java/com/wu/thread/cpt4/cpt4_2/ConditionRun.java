package com.wu.thread.cpt4.cpt4_2;

public class ConditionRun {
    /**
     * ReentrantLock中Condition类中方法await()和对象的wait() 功能是差不多的 就是让线程等待
     * Condition类中方法signal()和对象的notify()功能是差不多 都是唤醒 待定的线程
     * @param args
     */
    public static void main(String[] args) {
        ConditionMyService service = new ConditionMyService();

        ConditionThreadA threadA = new ConditionThreadA(service);
        threadA.start();

        ConditionThreadB threadB = new ConditionThreadB(service);
        threadB.start();
    }
}
