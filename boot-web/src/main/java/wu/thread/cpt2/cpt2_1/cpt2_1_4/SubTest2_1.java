package com.wu.thread.cpt2.cpt2_1.cpt2_1_4;

public class SubTest2_1 {
    /**
     * synchronized修饰的方法不具备继承性
     * 在父类方法使用synchronized，子类不具备该功能
     * @param args
     */
    public static void main(String[] args) {
        Sub2_1 sub21 = new Sub2_1();
        SubMyThreadA threadA = new SubMyThreadA(sub21);
        threadA.setName("A");
        threadA.start();
        SubMyThreadB threadB = new SubMyThreadB(sub21);
        threadB.setName("B");
        threadB.start();
    }
}
