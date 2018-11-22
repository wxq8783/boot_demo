package com.wu.sort;

/**
 * 选择排序
 */
public class SelectSort {
    /**
     * 初始状态：无序区为R[1..n]，有序区为空；
     *
     * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
     * 该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
     * 使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     *
     * n-1趟结束，数组有序化了。
     */
    public static int[] selectSort(int[] array){
        if(array.length ==0)
            return null;
        for(int i=0;i<array.length;i++){
            int minIndex = i;
            for(int j=i+1;j<array.length;j++){
                if(array[minIndex]>array[j]){
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,4,10,4,13,8,7,6};
        array = selectSort(array);
        for(int i : array){
            System.out.println(i);
        }
    }
}
