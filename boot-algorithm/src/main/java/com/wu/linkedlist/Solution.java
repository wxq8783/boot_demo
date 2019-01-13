package com.wu.linkedlist;

public class Solution {
    public ListNode removeListNode(ListNode head , int val){
        if(head == null){
            return null;
        }
        System.out.println("====");
        head.next = removeListNode(head.next,val);
        System.out.println("---------");
        System.out.println(head);
        if(head.val == val){
            return head.next;
        }else{
            return head;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeListNode(head, 6);
        System.out.println(res);

    }
}
