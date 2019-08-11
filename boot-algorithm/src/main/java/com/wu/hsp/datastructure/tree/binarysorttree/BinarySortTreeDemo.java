package com.wu.hsp.datastructure.tree.binarysorttree;

public class BinarySortTreeDemo {
}

class Node{

    public int value;

    public Node left;

    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node){

    }

    public Node search(int value){
        return null;
    }

    public Node searchParent(int value){
        return null;
    }

    public int delRightTreeMin(Node node){
        return 0;
    }

    public Node delNode(int value){
        //考虑3种情况
        //1、叶子节点的删除
        //2、只有一个子节点的删除
        //3、有两个子节点的的删除
        return null;
    }

    //中序变量
    public void infixOrder(){

        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.print(this+" ");

        if(this.right != null){
            this.right.infixOrder();
        }
    }
}
