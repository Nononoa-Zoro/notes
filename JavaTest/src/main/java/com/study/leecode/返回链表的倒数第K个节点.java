package com.study.leecode;

/**
 * 快慢指针的方法
 * fast一开始指向第k个位置
 * slow一开始指向0位置
 * 当fast移动到链表尾的时候，slow正好指向倒数第K个位置
 */
public class 返回链表的倒数第K个节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        int k = kthToLast(head, 2);
        System.out.println(k);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    public static int kthToLast(ListNode head, int k) {
        if (head.next == null) return head.val;
        ListNode fast = head, slow = head;
        //相当于开始的时候slow 和 fast节点相差k个位置
        while (k-- > 0) {
            fast = fast.next;
        }
        //最后fast到达最后一个位置的时候slow恰好到了倒数第K个位置
        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }
}
