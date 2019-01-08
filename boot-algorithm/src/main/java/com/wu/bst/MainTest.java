package com.wu.bst;

public class MainTest {
    public static void main(String[] args) throws IllegalAccessException {
        BSTree<Integer> bsTree = new BSTree<>();
        Integer[] treeArr = new Integer[]{5,3,6,8,4,2};
        for(Integer num : treeArr){
            bsTree.add(num);
        }
//        System.out.println(bsTree.contains(7));
//        System.out.println("========前序递归==========");
//        bsTree.preOrder();
//        System.out.println("========前序非递归==========");
//        bsTree.preOrderNR();
//        System.out.println("========中序递归==========");
//        bsTree.inOrder();
//        System.out.println("========后序递归==========");
//        bsTree.postOrder();
//        System.out.println("========层序遍历==========");
//        bsTree.levelOrder();
//        System.out.println("========删除最小值后，遍历=========");
//        bsTree.removeMin();
//        bsTree.inOrder();
        bsTree.inOrder();
        System.out.println("============");
        bsTree.removeNode(4);
        bsTree.inOrder();;
    }
}
