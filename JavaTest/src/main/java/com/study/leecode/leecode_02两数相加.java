package com.study.leecode;

public class leecode_02两数相加 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        ListNode p1 = l1,p2=l2;
        int t,jinwei=0;
        while (p1!=null&&p2!=null){
            t=(p1.val+p2.val+jinwei)%10;
            tmp.next=new ListNode(t);
            tmp=tmp.next;
            jinwei=(p1.val+p2.val+jinwei)/10;
            p1=p1.next;
            p2=p2.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(2);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        head1.next=node1;
        node1.next=node2;


        ListNode head2 = new ListNode(5);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(4);
        head2.next=node3;
        node3.next=node4;

        ListNode res = addTwoNumbers(head1, head2);

        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }


    }
}

