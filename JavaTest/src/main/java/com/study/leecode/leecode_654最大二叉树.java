package com.study.leecode;


public class leecode_654最大二叉树 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = construct(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode construct(int[] nums, int left, int right) {
        if (left == right)
            return null;
        int maxIndex = findMaxIndex(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = construct(nums, left, maxIndex);
        root.right = construct(nums, maxIndex + 1, right);
        return root;
    }


    public int findMaxIndex(int[] arr, int left, int right) {
        int index = left;
        for (int i = left; i < right; i++) {
            if (arr[i] > arr[index]) {
                index = i;
            }
        }
        return index;
    }
}
