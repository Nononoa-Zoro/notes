package com.study.zuoshen;

/**
 * 判断一个链表是不是回文结构
 * 1->2>3->2->1
 * 思路：
 * 快慢指针找到中间节点 反转后半段
 * 比较两端的数据
 */
public class Test9 {
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
    }

    /**
     * 反转单链表
     * @param head 链表头
     * @return 反转的链表
     */
    private static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //前一个节点
        Node preNode = null;
        //当前节点
        Node curNode = head;
        //下一个节点
        Node nextNode;

        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    private static Node findMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fast = head;
        Node slow = head;
        //快慢指针找中点 记得这里先保证fast.next!=null
        while (fast.next!=null&&fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node mid = findMid(node1);
        Node right = reverseList(mid);
        Node left=node1;
        while (right.next!=null&&left!=mid){
            if(left.val!=right.val) System.out.println(false);
            right=right.next;
            left=left.next;
        }
        System.out.println(true);
    }
}
