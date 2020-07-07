package com.study.leecode;

public class leecode_61旋转链表 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        //异常处理
        if (head == null) return null;
        if (head.next == null) return head;
        int len = 1;
        ListNode node = head;
        while (node.next != null) {
            len++;
            node = node.next;
        }
        node.next = head;
        int x = len - k % len - 1;
        ListNode tail = head;
        while (x-- > 0) {
            tail=tail.next;
        }
        ListNode newHead;
        newHead=tail.next;
        tail.next=null;
        return newHead;
    }
}
