package com.wu.linkedlist;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for(int i = 0;i<10;i++){
            linkedList.addFirstNode(i);
        }
        linkedList.removeLastNode();
        System.out.println(linkedList.toString());
    }
}
