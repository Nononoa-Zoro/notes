package com.study.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class leecode_337打家劫舍 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }


    public int rob(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        //当前节点只有
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        //当前节点不偷，当前节点的钱就是左孩子偷和右孩子偷钱的和
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //当前节点偷，左不偷右不偷加自身的钱
        result[1] = left[0] + right[0] + root.val;

        return result;
    }

}
