package com.wu.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergerSort {



    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        return merge(MergeSort(left), MergeSort(right));
    }
    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {

        int[] result = new int[left.length + right.length];
        System.out.println("left---"+JSON.toJSONString(left));
        System.out.println("right---"+JSON.toJSONString(right));
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {

            if (i >= left.length) {
                result[index] = right[j];
                j++;
            } else if (j >= right.length) {
                result[index] = left[i];
                i++;
            }
            else if (left[i] > right[j]) {
                result[index] = right[j];
                j++;
            }else {
                result[index] = left[i];
                i++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,4,10,4,13,8,7,6};
        array = sortProcess(array);
        System.out.println(JSON.toJSONString(array));
    }


    public static int[] sortProcess(int[] array){
        if(array.length < 2){
            return array;
        }
        int mid = array.length / 2 ;
        int[] leftArr = Arrays.copyOfRange(array,0,mid);
        int[] rightArr = Arrays.copyOfRange(array,mid,array.length);
//        sortProcess(leftArr);
//        sortProcess(rightArr);
        return mergeSort(sortProcess(leftArr),  sortProcess(rightArr));
    }

    public static int[] mergeSort(int[] leftArr , int[] rightArr){
        int[] result = new int[leftArr.length+rightArr.length];
        int i=0;
        int j = 0;
        for(int index = 0;index < result.length;index++){
            if(i >= leftArr.length){
                result[index] = rightArr[j];
                j++;
            }else if(j >= rightArr.length){
                result[index] = leftArr[i];
                i++;
            }else if(leftArr[i] > rightArr[j]){
                result[index] = rightArr[j];
                j++;
            }else if(leftArr[i] <= rightArr[j]){
                result[index] = leftArr[i];
                i++;
            }
        }
        return result;
    }
}
