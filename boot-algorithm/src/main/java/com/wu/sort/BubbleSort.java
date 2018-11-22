package com.wu.sort;

/**
 * 冒泡排序
 * https://mp.weixin.qq.com/s?__biz=MzAxMjEwMzQ5MA==&mid=2448886426&idx=1&sn=cdb10fbc7ebf4c49ca39613c612d366d&chksm=8fb550b7b8c2d9a176536c73cd9fdea90e94a48bf3990a29ad5004f1762ef53b8768412fe362&token=472294853&lang=zh_CN#rd
 */
public class BubbleSort {

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     *
     * 针对所有的元素重复以上的步骤，除了最后一个；
     *
     * 重复步骤1~3，直到排序完成。
     *
     * 因为要循环两次 使用时间复杂度是O(n2)
     */
    public static int[] bubbleSort(int[] array){
        if(array.length == 0)
            return null;
        for(int i=0;i<array.length;i++){
            for(int j=0;j < array.length-1-i;j++){
                //使用j进行互相比较，最大的存最后；然后从新来一次
                if(array[j+1] < array[j]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,4,8,4,13,8,7,6};
        array = bubbleSort(array);
        for(int i : array){
            System.out.println(i);
        }
    }

}
