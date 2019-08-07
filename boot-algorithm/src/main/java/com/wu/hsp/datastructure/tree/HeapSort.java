package com.wu.hsp.datastructure.tree;

import com.alibaba.fastjson.JSON;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4,6,8,5,9,7,3,10,-9,13};
        for(int i = arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            // 元素交换
            // 说是交换，其实质就是把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(arr, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(arr, 0, j);
        }
        System.out.println(JSON.toJSONString(arr));
    }




   public static void adjustHeap(int[] arr , int i , int length){
        int temp = arr[i];
        // 可以参照sort中的调用逻辑，在堆建成，且完成第一次交换之后，实质上i=0；也就是说，是从根所在的最小子树开始调整的
       // 接下来的讲解，都是按照i的初始值为0来讲述的
       // 这一段很好理解，如果i=0；则k=1；k+1=2
       // 实质上，就是根节点和其左右子节点记性比较，让k指向这个不超过三个节点的子树中最大的值
       // 这里，必须要说下为什么k值是跳跃性的。
       // 首先，举个例子，如果a[0] > a[1]&&a[0]>a[2],说明0,1,2这棵树不需要调整，那么，下一步该到哪个节点了呢？肯定是a[1]所在的子树了，
       // 也就是说，是以本节点的左子节点为根的那棵小的子树
       // 而如果a[0}<a[2]呢，那就调整a[0]和a[2]的位置，然后继续调整以a[2]为根节点的那棵子树，而且肯定是从左子树开始调整的
       // 所以，这里面的用意就在于，自上而下，自左向右一点点调整整棵树的部分，直到每一颗小子树都满足大根堆的规律为止

        while( i*2+1 < length ){
            int k = i*2+1;
            int maxIndex = k;
            if(k+1 < length && arr[k] < arr[k+1]){
                maxIndex = k+1;
            }
            if(temp < arr[maxIndex]){
                swap(arr,i,maxIndex);
                i = maxIndex;
            }else{
                break;
            }

        }
   }

    public static void swap(int[] arr , int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
