package com.study.leecode;

public class leecode_1022从根到叶的二进制数之和 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int sum = 0;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root,0);
        return sum;
    }


    public void dfs(TreeNode root, int val) {
        if (root == null) return;
        //每一步的操作
        int newVal = (val << 1) | root.val;
        //结束条件
        if (root.left == null && root.right == null) {
            sum += newVal;
        }
        dfs(root.left, newVal);
        dfs(root.right, newVal);
    }
}
