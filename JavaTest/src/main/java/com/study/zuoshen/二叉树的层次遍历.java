package com.study.zuoshen;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的层次遍历 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value=data;
        }
    }

    public static String levelOrder(Node root){
        StringBuilder res = new StringBuilder();
        if(root==null)return " ";
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                Node node = queue.poll();
                res.append(node.value).append(" ");
                if(node.left!=null)queue.add(node.left);
                if(node.right!=null)queue.add(node.right);
            }
        }
        return res.toString();

    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        root.left=node2;
        root.right=node3;
        node3.left=node4;
        String s = levelOrder(root);
        System.out.println(s);
    }
}
