package com.wu.hsp.datastructure.tree;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = new int[]{13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        root.preOrder();
    }

    public static Node createHuffmanTree(int[] arr){
        if(arr == null || arr.length <= 2){
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        for(int value : arr){
            nodes.add(new Node(value));
        }
        while(nodes.size() > 1){
            //第一步 排序
            Collections.sort(nodes);
            //第二步 取出前两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建树的根节点
            Node parentNode = new Node(leftNode.value+rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            //删除节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //添加节点
            nodes.add(parentNode);

        }
        return nodes.get(0);
    }

    public static void preOrder(Node root){
        if(root == null){
            return;
        }else{
            root.preOrder();
        }
    }

}


class Node implements Comparable<Node> {

    public int value;

    public Node left;

    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }


    public void preOrder(){
        System.out.print(this.value+" ");
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
