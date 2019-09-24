package com.wu.hsp.algorithm.binarysearchnorecursion;

/**
 * 二分查找(非递归)
 */
public class BinarySearchNoRecu {


    public static void main(String[] args) {
        int[] arr = {3,6,9,11,14,17,23,46,78,121,232,434,564};
        System.out.println(binarySearch(arr,78));
    }

    /**
     * 二分查找 非递归方法
     * @param arr 正序的数组
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr , int target){
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = (right+left)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid -1;
            }else{
                left = mid+1;
            }
        }
        return -1;

    }

}
