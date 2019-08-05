package com.wu.hsp.datastructure.recursion;

/**
 * 递归测试
 */
public class RecursionTest {

    public static void main(String[] args) {
        test(5);
    }

    public static void test(int n){
        if(n > 2){
            test(n-1);
        }
        System.out.println(n);
    }
}
