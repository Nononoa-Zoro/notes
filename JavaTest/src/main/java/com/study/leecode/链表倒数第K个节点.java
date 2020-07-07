package com.study.leecode;

public class 链表倒数第K个节点 {
    static class ListNode {
        int data;
        ListNode next;
        public ListNode(int data){
            this.data=data;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;

        int res = find(head, 3);
        System.out.println(res);

    }
    public static int find(ListNode head, int k) {
        ListNode slow=head;

        ListNode fast = head;
        int count = k - 1;
        while (count-- > 0) {
            fast = fast.next;
        }

        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }

        return slow.data;
    }
}
