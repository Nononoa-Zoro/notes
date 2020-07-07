package com.study.leecode;

public class leecode_83删除排序链表中的重复元素 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode cur =dummy;
        while (cur.next!=null&&cur.next.next!=null){
            if(cur.next.val==cur.next.next.val){
                ListNode tmp = cur.next;
                while(tmp!=null&&tmp.next!=null&&tmp.val==tmp.next.val){
                    tmp=tmp.next;
                }
                cur.next=tmp;
            }else {
                cur=cur.next;
            }
        }
        return dummy.next;
    }
}
