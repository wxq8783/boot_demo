package com.wu.hsp.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1,"aaa","1111");
        HeroNode node2 = new HeroNode(2,"bbb","2222");
        HeroNode node3 = new HeroNode(3,"ccc","3333");
        HeroNode node4 = new HeroNode(4,"ddd","4444");

        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(node1);
        linkedList.add(node4);
        linkedList.add(node2);
        linkedList.add(node3);
        linkedList.printList();

        System.out.println("--------------------------------");
        linkedList.printReverseLinkedList();

    }
}

class SingleLinkedList{
    private HeroNode heard = new HeroNode(0,"","");

    public void add(HeroNode node){
        HeroNode temp = heard;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }


    public void del(int no){

        HeroNode temp = heard;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            Integer tempNo = temp.next.no;
            if(tempNo == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }
    }

    public void printList(){
        if(heard.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = heard.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //1、获取单链表的有效节点数
    //2、查找单链表的倒数第K个节点
    //3、单链表的反转(重点)
    public void reverseLinkedList(){
        HeroNode reverseHeard = new HeroNode(0,"","");
        if(heard.next == null){
            return;
        }
        HeroNode curr = heard.next;
        while (curr != null){
            HeroNode temp = reverseHeard.next;
            reverseHeard.next = curr;
            curr = curr.next;
            reverseHeard.next.next = temp;
        }
        heard.next = reverseHeard.next;

    }
    //4、从尾到头打印单链表(1、反向遍历<会破坏之前的结构....不建议> 2、Stack栈)

    public void printReverseLinkedList(){
        if(heard.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode curr = heard.next;
        while (curr != null){
            stack.push(curr);
            curr = curr.next;
        }
        while (!stack.empty()){
            System.out.println(stack.pop());
        }


    }


}

class HeroNode{

    public Integer no;

    public String name;

    public String nickName;

    public HeroNode next;

    public HeroNode(Integer no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +"}";
    }
}