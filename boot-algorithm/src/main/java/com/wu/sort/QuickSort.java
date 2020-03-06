package com.wu.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序
 */
public class QuickSort {


    public static int[] quickSort(int[] array , int start , int end){
        if(array.length < 2 || start < 0 || start >= array.length || end > array.length || start > end ){
            return null;
        }
        int smallIndex = position(array , start , end);
        if(smallIndex > start){
            quickSort(array , start , smallIndex-1);
        }
        if(smallIndex < end){
            quickSort(array,smallIndex+1,end);
        }
        return array;
    }


    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    //{9,3,12,1,10,4,13,8,7,6}
    public static int partition0(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex) {
                    swap(array, i, smallIndex);
                }
            }
        return smallIndex;
    }


    public static int partition(int[] array , int start , int end){
        int pivot = start;
        int i  = start;
        int j = end;
        boolean rf = true;
        boolean lf = false;
        while( i < j){
            if(rf && array[j] < array[pivot]){
                swap(array,j,pivot);
                pivot = j;
                i++;
                lf = true;
                rf = false;
                continue;
            }else if(rf){
                j--;
                continue;
            }

            if(lf && array[i] > array[pivot]){
                swap(array , i , pivot);
                pivot = i;
                j--;
                rf = true;
                lf = false;
                continue;
            }else if(lf){
                i++;
                continue;
            }

        }
        return i;
    }



    public static void swap(int[] array , int i , int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{9,3,12,1,10,4,13,8,7,6};
        array = quickSort(array,0 , array.length-1);
        System.out.println(JSON.toJSONString(array));
    }


    public static int position(int[] array , int start , int end){
        int num = start - 1;
        //取最后一个做 中间节点
        for(int i=start ;i <= end ;i++){
            if(array[i] <= array[end]){
                num++;
                if(i > num){
                    swap(array,i,num);
                }
            }
        }
        return num;
    }
}
