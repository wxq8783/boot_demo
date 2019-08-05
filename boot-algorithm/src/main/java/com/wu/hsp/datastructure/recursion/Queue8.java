package com.wu.hsp.datastructure.recursion;

/**
 * 八皇后问题
 * 8行8列的表格
 * 8个皇后不冲突 {每个皇后 不在同一行 不在同一列 不在同一斜线}
 *
 * n表示行
 * i表示列
 *
 */
public class Queue8 {

    static int max = 8;

    static int[] array = new int[8];

    /**
     * 解题思路:
     *    第一个皇后先放在第一行第一列
     *    第二个皇后放在第二行第一列，然后判断是否OK[是否冲突]，如果不OK，继续放在第二列、第三列、一次把所有列都放完，找到一个合适
     *    继续第三个皇后、还是第一列、第二列...直到第8个皇后也能放在一个不冲突的位置，算是找到一个正确的解
     *    当得到一个正确解时，在栈回退到上一个栈时，就开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到
     *    然后回头继续第一个皇后放到第二列，后面继续循环执行1、2、34、的步骤
     * @param args
     */
    public static void main(String[] args) {
        check(0);
    }

    /**
     * 检查
     * 特别注意 check是每一次递归式，进入到check中都有 for()循环，因此会有回溯
     * @param n
     */
    public static void check(int n){
        if(n == max){
            print();
            return;
        }
        for(int i=0 ; i < max ;i++){
            array[n] = i;
            if(judge(n)){
                check(n+1);
            }

        }
    }

    /**
     * 调整
     * @param n
     * @return
     */
    public static boolean judge(int n){
        for(int i=0;i<n;i++){
            if(array[n] == array[i] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    public static void print(){
        for(int i = 0;i<array.length;i++){
            System.out.print(" "+array[i]);
        }
        System.out.println();
    }
}
