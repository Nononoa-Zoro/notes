package com.study.zuoshen;

/**
 * 两个单链表可能有环可能无环
 * 找到两个链表相交的第一个节点
 */
public class Test10 {
    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static Node getLoopNode(Node node) {
        //不使用map的方式获得单链表的第一个入环节点
        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }
        Node n1 = node;//慢指针
        Node n2 = node;//快指针
        while (n1 != n2) {
            if (n2.next == null || n1.next == null) return null;
            n2 = n2.next.next;
            n1 = n1.next;
        }
        //如果快指针追上了慢指针 快指针回到链表头 每次只走一步
        n2 = node;
        while (n1 != n2) {
            n1=n1.next;
            n2=n2.next;
        }
        return n1;
    }
}
