package com.study.leecode;

public class leecode_112路径总和 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //当前树是否可以满足条件
    public boolean hasPathSum(TreeNode root, int sum) {
        //base case
        if(root==null)return false;
        sum-=root.val;
        boolean left = hasPathSum(root.left,sum);
        boolean right = hasPathSum(root.right,sum);

        //处理递归过程的一般情况 如果左右有一个满足条件就返回true
        if(left||right)return true;
        //如果当前节点是叶子节点且sum==0说明满足条件
        if(root.left==null&&root.right==null&&sum==0)return true;
        //否则返回false
        return false;
    }
    

}
