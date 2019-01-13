package com.wu.linkedlist;

public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node head){
            this.e = e;
            this.next = head;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }
    }

    public Node dummyHead;
    public int size;

    public LinkedList(){
        this.dummyHead = new Node();
        this.size = 0;
    }

    public void addFirstNode(E e){
        dummyHead.next = new Node(e,dummyHead.next);
        size++;
    }

    public void addNode(E e , int index){
        if(size < 0 || index > size){
            throw new IllegalArgumentException("列表不存在");
        }
        Node pre = dummyHead;
        for(int i = 0;i < index;i++){
            pre = pre.next;
        }
//        Node node = new Node(e);
//        node.next = pre.next;
//        pre.next = node;

        pre.next = new Node(e,pre.next);
        size++;
    }


    public void addLastNode(E e){
        Node cur = dummyHead;
        for(int i = 0;i<size;i++){
            cur = cur.next;
        }
        cur.next = new Node(e);
        size++;
    }

    public Node removeFirstNode(){
        if(size < 0 ){
            throw new IllegalArgumentException("参数错误");
        }
        Node cur = dummyHead.next;
        dummyHead.next = cur.next;
        cur.next = null;
        size--;
        return cur;
    }

    public Node removeNode(int index){
        if(size < 0 || size < index){
            throw new IllegalArgumentException("参数错误");
        }

        Node pre = dummyHead;
        for(int i = 0;i <index-1;i++){
            pre = pre.next;
        }
        Node cur = pre.next;
        pre.next = cur.next;
        cur.next = null;
        size--;
        return cur;

    }

    public Node removeLastNode(){
        if(size < 0 ){
            throw new IllegalArgumentException("参数错误");
        }
        return removeNode(size);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur.e + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

}