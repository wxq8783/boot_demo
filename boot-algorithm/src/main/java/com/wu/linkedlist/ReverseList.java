package com.wu.linkedlist;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ReverseList {

    public class Node{
        public Integer vla;
        public Node next;

        public Node(Integer vla, Node next) {
            this.vla = vla;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vla=" + vla +
                    '}';
        }
    }

    public Node head ;
    public Node lastNode = null;
    public int size = 0;
    public void add(Integer val){
        size++;
        if(head == null){
            lastNode = new Node(val,null);
            head = lastNode;
        }else{
            Node node = new Node(val,null);
            lastNode.next = node;
            lastNode = node;
        }
    }

    /**
     * 单链表反转
     * @param headNode
     * @return
     */
    public ReverseList reverse(Node headNode){
        ReverseList newList = new ReverseList();
        Node node = headNode;
        while(node != null){
            Node temp = newList.head;
            Node temp2 = node;
            node = node.next;
            newList.head = temp2;
            newList.head.next = temp;

        }
        return newList;
    }

    public Node middareNode(Node headNode){
        if(headNode == null ){
            return null;
        }
        if(headNode.next == null){
           return headNode;
        }
        Node sn = headNode;
        Node fn = headNode;
        while (fn != null && fn.next != null){
            sn = sn.next;
            fn = fn.next.next;
        }
        return sn;
    }

    /**
     * 添加环形的链表
     * @param val
     */
    public void addCycleNode(int val){
        //Node lastNode = new Node(val,null);
        if(head == null){
            lastNode = new Node(val,null);
            head = lastNode;
        }
        Node sameNode = null;
        Node tempNode = head;
        while(tempNode.next != null){
            if(val == tempNode.vla){
                sameNode = tempNode;
                break;
            }
            tempNode = tempNode.next;
        }
        Node node = null;
        if(sameNode != null){
            node = new Node(val,sameNode.next);
        }else{
            node= new Node(val,null);
        }
        lastNode.next = node;
        lastNode = node;
    }

    /**
     * 判断链表是否有环
     * @param headNode
     * @return
     */
    public boolean hasCycleNode(Node headNode){
        if(headNode == null || headNode.next == null){
            return false;
        }
        Node sn = headNode;
        Node fn = headNode;
        while(fn != null && fn.next != null){
            sn = sn.next;
            fn = fn.next.next;
            if(sn.vla == fn.vla){
                return true;
            }
        }
        return false;

    }

    /**
     * 合并两个有序单链表
     * @param firstHeadNode
     * @param secondHeadNode
     * @return
     */
    public static Node mergeTwoList(Node firstHeadNode ,Node secondHeadNode){
        if(firstHeadNode == null){
            return secondHeadNode;
        }
        if(secondHeadNode == null){
            return firstHeadNode;
        }
        Node headNode = null;
        if(firstHeadNode.vla <= secondHeadNode.vla){
            headNode = firstHeadNode;
            firstHeadNode = firstHeadNode.next;
            headNode.next = mergeTwoList(firstHeadNode,secondHeadNode);
        }else{
            headNode = secondHeadNode;
            secondHeadNode = secondHeadNode.next;
            headNode.next = mergeTwoList(firstHeadNode,secondHeadNode);
        }
        return headNode;
    }

    /**
     * 查询出倒数第n个节点并返回
     * @param headNode
     * @param n
     * @return
     */
    public Node queryLastNumNode(Node headNode , int n){
        Node fn = headNode;
        Node ln = headNode;
        for(int i=0;i<n;i++){
            fn = fn.next;
        }
        if(fn == null){
            return ln;
        }
        while(fn.next != null){
            fn = fn.next;
            ln = ln.next;
        }
        return ln.next;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
        for(int i = 0;i< 103;i++){
            if(!queue.offer("A"+i)){
                Object[] ss = queue.toArray();
                queue.clear();
                queue.offer("A"+i);
            }
        }
        //int i = 2147483647;
        //System.out.println(i);
//        ReverseList list1 = new ReverseList();
//        list1.add(1);
//        list1.add(2);
//        list1.add(4);
//        list1.add(5);
//        list1.add(6);
//        list1.add(7);
//        list1.add(11);
//        System.out.println(list1.queryLastNumNode(list1.head,7));
//        ReverseList list2 = new ReverseList();
//        list2.add(1);
//        list2.add(3);
//        list2.add(4);
//        list2.add(6);
//        list.add(8);
 //       System.out.println(list.hasCycleNode(list.head));
//        Node node = list.middareNode(list.head);
//        System.out.println(node);
//        Node node = mergeTwoList(list1.head,list2.head);
//        while (node != null){
//            System.out.println(node);
//            node = node.next;
//        }
    }

}
