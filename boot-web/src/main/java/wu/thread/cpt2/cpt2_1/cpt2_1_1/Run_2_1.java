package com.wu.thread.cpt2.cpt2_1.cpt2_1_1;

public class Run_2_1 {
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        HasSelfPrivateNum hasSelfPrivateNum2 = new HasSelfPrivateNum();
        ThreadA_2_1 threadA21 = new ThreadA_2_1(hasSelfPrivateNum);
        threadA21.start();
        ThreadB_2_1 threadB21 = new ThreadB_2_1(hasSelfPrivateNum);
        threadB21.start();
//        ThreadB_2_1 threadB21 = new ThreadB_2_1(hasSelfPrivateNum2);
//        threadB21.start();
    }
}
