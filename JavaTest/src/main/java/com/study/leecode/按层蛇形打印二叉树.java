package com.study.leecode;

import java.util.LinkedList;
import java.util.Queue;

public class 按层蛇形打印二叉树 {

     static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static String levelOrder(TreeNode root){
        StringBuilder res = new StringBuilder();
        if(root==null)return res.toString();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            StringBuilder s = new StringBuilder();

            while (size-->0){
                TreeNode node = queue.poll();
                s.append(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }

            if(flag){
                res.append(s);
                flag=!flag;
            }else{
                res.append(s.reverse());
                flag=!flag;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.left=node2;
        root.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;

        System.out.println(levelOrder(root));

    }
}
