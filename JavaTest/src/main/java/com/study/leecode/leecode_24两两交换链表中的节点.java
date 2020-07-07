package com.study.leecode;

public class leecode_24两两交换链表中的节点 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        //如果只有头节点或者只有一个节点
        if(head==null||head.next==null)return head;

        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next=swapPairs(secondNode.next);
        secondNode.next=firstNode;

        return secondNode;

    }


}



