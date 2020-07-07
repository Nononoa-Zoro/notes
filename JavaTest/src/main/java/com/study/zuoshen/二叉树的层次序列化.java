package com.study.zuoshen;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的层次序列化 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //二叉树的层次遍历序列化
    public static String serialBycengci(Node root) {
        if(root==null)return "# ";
        Queue<Node> queue = new LinkedList<>();
        //先把根节点的值存进去
        StringBuilder res = new StringBuilder(root.value + " ");
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node.left!=null){
                queue.add(node.left);
                res.append(node.left.value+" ");
            }else{
                res.append("# ");
            }
            if(node.right!=null){
                queue.add(node.right);
                res.append(node.right.value+" ");
            }else{
                res.append("# ");
            }
        }
        return res.toString();
    }

    //二叉树层次反序列化
    public static Node unSerialBycengci(String s) {
//        if (s == null || s.length() == 0) {
//            return null;
//        }
//        String[] str = s.split(" ");
//        int index = 0;
//        Queue<Node> queue = new LinkedList<>();
//
//        Node root = generateNode(str[index++]);
//        if (root == null) return null;
//        queue.offer(root);
//        Node node;
//        while (!queue.isEmpty()) {
//            node = queue.poll();
//            node.left = generateNode(str[index++]);
//            node.right = generateNode(str[index++]);
//            if (node.left != null) queue.offer(node.left);
//            if (node.right != null) queue.offer(node.right);
//        }
//        return root;
        if(s==null||s.length()==0)return null;
        String[] str = s.split(" ");
        Node root = generateNode(str[0]);
        if(root==null)return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node node;
        int index = 1;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left=generateNode(str[index++]);
            node.right=generateNode(str[index++]);
            if(node.left!=null)queue.add(node.left);
            if(node.right!=null)queue.add(node.right);
        }
        return root;
    }

    //根据字符串构造一个节点
    public static Node generateNode(String s) {
        if (s.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(s));
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        root.left = node2;
        root.right = node3;
        node3.left = node4;

        String s = serialBycengci(root);
        System.out.println(s);

        Node node = unSerialBycengci(s);
        String s1 = serialBycengci(node);
        System.out.println(s1);

    }
}
