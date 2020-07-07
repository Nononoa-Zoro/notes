package com.study.leecode;

/**
 * 在删除链表节点的时候通常会构造一个哑节点指向链表的头，防止删除了头节点。
 */
public class leecode_82删除排序链表中的重复元素2 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode tmp = cur.next;
                while (tmp != null && tmp.next != null && tmp.val == tmp.next.val) {
                    tmp = tmp.next;
                }
                cur.next = tmp.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
