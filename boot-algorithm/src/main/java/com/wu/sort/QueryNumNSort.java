package com.wu.sort;

/**
 *
 * 给定一组数据，查询第K个大的数据
 * 时间复杂度为O(n)
 */
public class QueryNumNSort {

    public static void main(String[] args) {
        int[] arr = {3,6,2,1,7,9,4};
        int num = partition(arr,0,arr.length-1);
        System.out.println(num);
    }

    public static void main1(String[] args) {

        int[] arr = new int[]{7,3,5,0,1,8,4,11,9,10,2,6};
        int k = 7;
        if(arr.length < k){
            return;
        }

        System.out.println( getNumK(arr,0,arr.length-1,k));

    }

    public static int getNumK(int[] arr ,int start,int end , int k){
        int smallNum = partition(arr,start,end);
        if(smallNum + 1 == k){
            return arr[smallNum];
        }
        if(smallNum+1 > k){
            return getNumK(arr,start,smallNum-1,k);
        }else{
            return getNumK(arr,smallNum+1,end,k);
        }
    }


    public static int partition(int[] arr,int start,int end){
        int pivot = end;
        int num = start -1;
        for(int i=start;i<= end;i++){
            if(arr[i] >= arr[pivot]){
                num++;
                if(num < i){
                    swap(arr,num,i);
                }
            }
        }
        return num;
    }

    private static void swap(int[] arr , int a , int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
