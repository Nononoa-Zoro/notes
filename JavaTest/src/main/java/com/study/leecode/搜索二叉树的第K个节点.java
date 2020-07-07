package com.study.leecode;

import java.util.Stack;

public class 搜索二叉树的第K个节点 {

    class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    //非递归版本使用中序遍历 二叉树的中序遍历是上升的序列
    public TreeNode KthNode(TreeNode root, int k) {

        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();

        int count = 0;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                count++;
                if (count == k) return root;
                root = root.right;
            }

        }
        return null;

    }

    int index = 0;

    public TreeNode search(TreeNode root, int k) {
        if (root.left != null) {
            TreeNode left = search(root.left, k);
            if (left != null) {
                return left;
            }
        }


        if (++index == k) {
            return root;
        }

        if (root.right != null) {
            TreeNode right = search(root.right, k);
            if (right != null) {
                return right;
            }
        }

        return null;
    }
}
