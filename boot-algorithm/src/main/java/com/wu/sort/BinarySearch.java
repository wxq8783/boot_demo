package com.wu.sort;

/**
 * 二分查找法
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,8,9,12,14,17,21,25,34,56,58,81};
        int searchNum = 17;
        System.out.println(binSearch(arr,searchNum));
    }

    public static int binSearch(int[] arr , int searchNum){
        int min = 0;
        int max = arr.length-1;
        while (min <= max){
            int mid = min + ((max-min)>>1);
            if(arr[mid] < searchNum){
                min = mid+1;
            }else if(arr[mid] > searchNum){
                max = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

}
