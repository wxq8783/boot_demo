package com.wu.sort;

import com.alibaba.fastjson.JSON;

public class ShellSort {

    public static int[] ShellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                System.out.println("i="+i+"    gap="+gap+"     preIndex="+preIndex);
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
                System.out.println(JSON.toJSONString(array));
                System.out.println("=====================");
            }
            gap /= 2;

        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{12,1,5,4,10,3,13,8,7,6,11};
        array = shellSort2(array);
        System.out.println("-------------------------");
        System.out.println(JSON.toJSONString(array));
    }


    public static int[] shellSort2(int[] array){
        int len = array.length;
        int temp;
        int gap = len / 2;
        while(gap > 0){
            for(int i = gap;i<array.length;i++){
                temp = array[i];
                int preIndex = i-gap;
                while (preIndex >= 0 && array[preIndex] > temp){
                    array[i] = array[preIndex];
                    preIndex -=gap;
                }
                array[preIndex+gap] = temp;
            }
            gap = gap / 2;
        }
        return array;
    }

}
