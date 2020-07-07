package com.study.leecode;

public class 判断一个单链表是否有环 {

    public static class ListNode {
        ListNode next;
        int data;

        ListNode(int data) {
            this.data = data;
        }
    }


    public static boolean hasLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static ListNode findLoopEntrance(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        ListNode cur = head;
        ListNode tmp = null;//slow和fast第一次相遇的位置

        while (slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                tmp = slow;
                break;
            }
        }

        //如果没有找到 即上面的while条件不满足才结束循环
        if (slow == null || fast.next == null) return null;

        //tmp表示slow和fast第一次相遇的位置
        //cur是链表的头节点
        //链表头节点到入环口的距离等于相遇位置到入环口的位置
        while (cur != tmp) {
            cur = cur.next;
            tmp = tmp.next;
        }

        return cur;


    }
}
