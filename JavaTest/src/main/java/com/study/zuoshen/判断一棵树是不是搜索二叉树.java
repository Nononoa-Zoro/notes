package com.study.zuoshen;

import java.util.Stack;

/**
 * 搜索二叉树是指 当前节点的左边都比当前节点小 右边都比当前节点大的树
 * 中序遍历如果是从小到大的就是搜索二叉树
 */
public class 判断一棵树是不是搜索二叉树 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData{
        int data;
        boolean flag;
        ReturnData(int data,boolean flag){
            this.data=data;
            this.flag=flag;
        }
    }


    public static void main(String[] args) {
        Node root = new Node(5);
        Node node3 = new Node(3);
        Node node8 = new Node(8);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node7 = new Node(7);
        Node node9 = new Node(9);

        root.left=node3;
        root.right=node8;
        node3.left=node2;
        node3.right=node4;
        node8.left=node7;
        node8.right=node9;

        System.out.println(isSearchTree(root));
        System.out.println(isSearchTree1(root).flag);

    }


    //递归实现
    public static ReturnData isSearchTree1(Node root){
        if(root==null)return new ReturnData(0,true);

        ReturnData leftData = isSearchTree1(root.left);
        if(!leftData.flag)return new ReturnData(0,false);
        ReturnData rightData = isSearchTree1(root.right);
        if(!rightData.flag)return new ReturnData(0,false);

        if(root.value<leftData.data||root.value>rightData.data) new ReturnData(0,false);

        return new ReturnData(root.value,true);
    }

    //非递归实现，搜索二叉树的中序遍历是升序的
    public static boolean isSearchTree(Node node) {
        //之前出现的值
        int pre = Integer.MIN_VALUE;
        if (node != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    boolean flag = stack.peek().value >= pre;
                    pre=stack.peek().value;
                    node = stack.pop();
                    if (!flag) return false;
                    node = node.right;
                }
            }
        }
        return true;
    }

    public boolean helper(Node node, Integer lower, Integer upper) {
        if (node == null) return true;

        //val是当前节点值
        int val = node.value;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        //递归遍历右子树 当前节点的值val应该是最小的
        if (! helper(node.right, val, upper)) return false;
        //递归遍历左孩子 当前节点值应该是最大的
        if (! helper(node.left, lower, val)) return false;

        return true;
    }

}
