package com.study.zuoshen;

import java.util.Stack;

public class 二叉树遍历 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //递归先序遍历
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    //非递归先序遍历
    public static void preOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");
                //入栈和出栈的顺序是相反的
                //先序遍历是 中 左 右 所以right先入栈 left后入栈
                if (head.right != null) stack.push(head.right);
                if (head.left != null) stack.push(head.left);
            }
        }
    }


    //递归中序遍历
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    //中序遍历非递归
    //当前节点为空 从栈中拿出一个节点打印 并将右孩子节点压栈
    public static void inOrderUnRecur(Node root) {
//        if (head != null) {
//            Stack<Node> stack = new Stack<>();
//            while (!stack.isEmpty() || head != null) {
//                if (head != null) {
//                    stack.push(head);
//                    head = head.left;
//                } else {
//                    head = stack.pop();
//                    System.out.println(head.value);
//                    head = head.right;
//                }
//            }
//        }
        if(root!=null){
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()||root!=null){
                if(root!=null){
                    stack.push(root);
                    root=root.left;
                }else{
                    root = stack.pop();
                    System.out.println(root.value);
                    root=root.right;
                }
            }
        }
    }

    //递归后序遍历
    public static void postOrderRecur(Node head) {
        if (head == null) return;
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.println(head.value);
    }

    //非递归后序遍历
    //后序遍历 左 右 中 使用辅助栈存储 中 右 左 的顺序 即可
    public static void posOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) s1.push(head);
                if (head.right != null) s1.push(head);
            }
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");
            }
        }
    }

}
