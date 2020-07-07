package com.study.zuoshen;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 二叉树的先序序列化 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //序列化
    //通过构造先序遍历字符串的方式序列化二叉树
    public static String serialByPreOrder(Node node) {
        StringBuilder res = new StringBuilder();
        if (node == null) return "# ";
        res.append(node.value + " ");
        res.append(serialByPreOrder(node.left));
        res.append(serialByPreOrder(node.right));
        return res.toString();
    }


    //通过先序 反序列化 生成一个二叉树
    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split(" ");
        Queue<String> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer(value);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if(value.equals("#"))return null;
        Node root = new Node(Integer.parseInt(value));
        root.left=reconPreOrder(queue);
        root.right=reconPreOrder(queue);
        return root;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;

        String s = serialByPreOrder(node1);
        System.out.println(s);
    }
}
