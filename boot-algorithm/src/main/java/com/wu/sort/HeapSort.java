package com.wu.sort;

import com.alibaba.fastjson.JSON;

/**
 * 堆排序
 */
public class HeapSort {
    /**
     * 大顶堆:每个结点的值都大于或等于其左右孩子结点的值
     * arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
     *
     * 小顶堆:每个结点的值都小于或等于其左右孩子结点的值
     * arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
     *
     *  当前节点：arr[i]
     *  左节点：arr[2i+1]
     *  右节点：arr[2i+2]
     *
     *  父节点 arr[(i-1)/2]
     */

    /**
     * 完全二叉树
     */


    public static void main(String[] args) {
        int[] array = new int[]{9,3,12,1,10,4,13,8,7,6};
        array = heapSort(array);
        System.out.println(JSON.toJSONString(array));
    }

    public static int[] heapSort(int[] array){
        if(array.length < 2){
            return array;
        }
        //创建大顶堆
        buildMaxHeap(array);
        //交换最后一个值
        swap(array,0,array.length-1);
        int size = array.length-1;
        while(size > 0){
            heapify(array,0,size);
            swap(array,0,size);
            size = size -1;
        }
        System.out.println(JSON.toJSONString(array));
        return array;
    }

    public static void heapify(int[] array ,int index, int heapSize){
        int left = 2*index+1;
        int right = 2*index+2;
        while(left < heapSize || right < heapSize){
            int newIndex = 0 ;
            if(array[index] < array[left] || array[index] < array[right]) {
                 newIndex = array[left] > array[right] ? left :right;
                swap(array,index,newIndex);
            }else {
                break;
            }
            index = newIndex;
            left = 2*index+1;
            right = 2*index+2;
        }
    }


    /**
     * 建立大根堆
     * @param array
     */
    public static void buildMaxHeap(int[] array){
        for(int i = 0 ;i< array.length;i++){
            int index = i ;
            int parent = (index-1)/2 ;
            while(array[index] > array[parent]) {
                    swap(array, index, parent);
                    index = parent;
            }

        }
    }

    public static void swap(int[] array , int i , int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
