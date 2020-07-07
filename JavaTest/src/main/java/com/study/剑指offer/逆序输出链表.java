package com.study.剑指offer;

import java.util.Stack;

public class 逆序输出链表 {

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

        System.out.println(slove_recursive(head));
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        System.out.println(solve(head));

    }

    public static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }
    }


    public static String solve(ListNode head) {
        StringBuilder res = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        ListNode tmp = head;

        while (tmp != null) {
            stack.push(tmp.data);
            tmp = tmp.next;
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.toString();
    }

    public static String slove_recursive(ListNode head) {
        StringBuilder res = new StringBuilder();
        if (head != null && head.next != null) {
            //已经是逆序的
            String s = slove_recursive(head.next);
            res.append(s);
        }
        res.append(head.data);
        return res.toString();
    }

}
