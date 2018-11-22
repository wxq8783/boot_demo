package com.wu.sort;

/**
 * 插入排序
 */
public class InsertionSort {
    /**
     * 从第一个元素开始，该元素可以认为已经被排序；
     *
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     *
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     *
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     *
     * 将新元素插入到该位置后；
     *
     * 重复步骤2~5
     */
    public static int[] insertionSort(int[] array){
        if(array.length == 0)
            return null;
        int current;
        for(int i=0;i<array.length-1;i++){
            current = array[i+1];
            int preIndex = i;
            while(preIndex >= 0 && current < array[preIndex]   ){
                array[preIndex+1] = array[preIndex];
                preIndex--;
            }
            array[preIndex+1] = current;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,1,5,4,10,4,13,8,7,6};
        array = insertionSort(array);
        for(int i : array){
            System.out.println(i);
        }
    }
}
