package com.study.leecode;

public class leecode_450删除二叉搜索树的节点 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //删除以root节点为根的树中的key值
    //返回删除之后的树
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    //如果有有孩子
    //求后继节点的值
    public int successor(TreeNode node) {
        node = node.right;
        while (node.left != null) node = node.left;
        return node.val;
    }

    //如果有左孩子
    //求前躯节点
    public int predecessor(TreeNode node) {
        node = node.left;
        while (node.right != null) node = node.right;
        return node.val;
    }


}
