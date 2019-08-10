package com.wu.hsp.datastructure.tree;

/**
 * 顺序存储二叉树
 *
 * 堆排序使用
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.proOrder();
    }
}

class ArrBinaryTree{
    int[] arr = null;
    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void proOrder(){
        this.preOrder(0);
    }
    /**
     *
     * @param index 编号
     */
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        System.out.print(arr[index]);
        if((2*index+1) < arr.length){
            preOrder(2*index+1);
        }
        if((2*index+2) < arr.length){
            preOrder(2*index+2);
        }

    }

}
