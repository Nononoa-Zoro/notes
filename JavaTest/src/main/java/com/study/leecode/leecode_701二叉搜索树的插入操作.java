package com.study.leecode;

public class leecode_701二叉搜索树的插入操作 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }

        if (val <= root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
//    public TreeNode insertIntoBST(TreeNode root, int val) {
//        dfs(root,val);
//        return root;
//    }
//
//    public void dfs(TreeNode root, int val) {
//
//        if (root.left == null && root.right == null) {
//            TreeNode node = new TreeNode(val);
//            if (val <= root.val) {
//                root.left = node;
//            } else {
//                root.right=node;
//            }
//            return;
//        }
//
//        if(root.left==null){
//            TreeNode node = new TreeNode(val);
//            root.left=node;
//            return;
//        }
//
//        if(root.right==null){
//            TreeNode node = new TreeNode(val);
//            root.right=node;
//            return;
//        }
//
//
//        if (val <= root.val) {
//            dfs(root.left, val);
//        } else {
//            dfs(root.right, val);
//        }
//    }
}
