package com.study.leecode;

import java.util.HashMap;

public class leecode_105根据前序和中序构造一棵二叉树 {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;

        }
    }

    // 先序开始的索引位置
    int pre_idx = 0;

    int[] preorder;
    int[] inorder;
    /**
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     */
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length);
    }



    public TreeNode helper(int left, int right) {
        if (left == right)
            return null;

        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        int index = idx_map.get(root_val);

        pre_idx++;

        root.left = helper(left, index);
        root.right = helper(index + 1, right);
        return root;
    }

}

