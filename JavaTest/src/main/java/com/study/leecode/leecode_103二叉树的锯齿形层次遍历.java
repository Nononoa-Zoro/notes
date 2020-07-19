package com.study.leecode;

import java.util.*;

//两个栈可以实现一个队列

public class leecode_103二叉树的锯齿形层次遍历 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.val = data;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;
        node20.left=node15;
        node20.right=node7;
        List<List<Integer>> lists = zigzagLevelOrder(root);
        System.out.println(lists);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            //两个栈一个先压左 一个先压右
            if (!stack1.isEmpty()) {
                int size = stack1.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode node = stack1.pop();
                    list.add(node.val);
                    if (node.left != null) stack2.push(node.left);
                    if (node.right != null) stack2.push(node.right);
                }
                res.add(list);
            }

            if (!stack2.isEmpty()) {
                int size = stack2.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode node = stack2.pop();
                    list.add(node.val);
                    if (node.right != null) stack1.push(node.right);
                    if (node.left != null) stack1.push(node.left);
                }
                res.add(list);
            }
        }
        return res;

    }

    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        boolean flag = true;
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null)return res;
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> row = new ArrayList<>();
            while (size-->0){
                TreeNode node = queue.poll();
                row.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            if (flag){
                res.add(row);
                flag=!flag;
            }else{
                Collections.reverse(row);
                res.add(row);
                flag=!flag;
            }
        }
        return res;
    }
}
