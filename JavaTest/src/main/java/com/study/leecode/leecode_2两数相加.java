package com.study.leecode;

public class leecode_2两数相加 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p1=l1,p2=l2,cur =dummy;
        int jinwei = 0;
        while (p1!=null||p2!=null){
            int x = (p1!=null)?p1.val:0;
            int y = (p2!=null)?p2.val:0;
            cur.next=new ListNode((x+y+jinwei)%10);
            jinwei=(x+y+jinwei)/10;
            cur=cur.next;
            if(p1!=null)p1=p1.next;
            if(p2!=null)p2=p2.next;
        }
        if(jinwei>0){
            cur.next=new ListNode(jinwei);
        }
        return dummy.next;
    }

    public static void main(String[] args) {

    }
}

