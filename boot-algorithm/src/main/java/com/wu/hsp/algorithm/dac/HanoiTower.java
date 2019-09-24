package com.wu.hsp.algorithm.dac;

/**
 * 汉诺塔
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    public static void hanoiTower(int num , char a , char b ,char c){
        if(num == 1){
            System.out.println("第1个盘从 "+a+" --> "+c);
        }else{
            //1、把最上面的所有盘从A->B，移动过程中使用到C
            hanoiTower(num-1,a,c,b);
            //2、把最下面的盘A->C
            System.out.println("第"+num+"个盘从 "+a+" --> "+c);
            //3、把B塔所有的盘从B->C
            hanoiTower(num-1,b,a,c);

        }
    }
}
