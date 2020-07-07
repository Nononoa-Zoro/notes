package com.study.leecode;

/**
 * 单链表的每一个节点 保存了指向下一个节点的指针和一个随机指针
 * 在空间复杂度为o(1)的情况下，实现该链表的深复制
 */
public class 深复制一个单链表 {

    public class ListNode {
        int data;
        //指向下一个节点的指针
        ListNode next;
        //指向一个随机节点的指针
        ListNode random = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    //实现深复制
    public ListNode clone(ListNode head) {

        if (head == null) return null;
        ListNode ohead = head;
        ListNode nhead = null;
        while (ohead != null) {
            nhead = new ListNode(ohead.data);
            nhead.next = ohead.next;
            ohead.next = nhead;
            ohead = nhead.next;
        }
        ohead = head;
        while (ohead != null) {
            nhead = ohead.next;
            nhead.random = ohead.random == null ? null : ohead.random.next;
            ohead=ohead.next.next;
        }
        ohead=head;
        head=ohead.next;
        while (true){
            nhead=ohead.next;
            ohead.next=nhead.next;
            ohead=nhead.next;
            if(ohead==null){
                break;
            }else{
                nhead.next=ohead.next;
            }
        }
        return head;

    }
}
