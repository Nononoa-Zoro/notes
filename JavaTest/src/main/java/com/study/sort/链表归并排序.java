package com.study.sort;

public class 链表归并排序 {
    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        mergeSort(head);
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val + "\t");
            tmp = tmp.next;
        }
    }

    public static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node right = slow.next;
        slow.next = null;
        Node left = head;
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    public static Node merge(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node tmp = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                tmp.next = head1;
                head1 = head1.next;
            } else {
                tmp.next = head2;
                head2 = head2.next;
            }
            tmp = tmp.next;
        }

        tmp.next = head1 != null ? head1 : head2;
        return dummy.next;
    }
}

class Node {
    Node next;
    int val;

    public Node(int val) {
        this.val = val;
    }
}
