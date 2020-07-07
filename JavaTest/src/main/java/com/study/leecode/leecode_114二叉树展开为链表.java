package com.study.leecode;

public class leecode_114二叉树展开为链表 {

    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.data = x;
        }
    }

    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode node = root.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

}
