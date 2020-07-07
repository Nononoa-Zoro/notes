package com.study.leecode;

import java.util.List;
import java.util.Stack;

public class 二叉搜索树第K小的元素 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int kthSmallest(TreeNode root, int k) {

        if (root == null) return -1;
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while (root != null || !stack.isEmpty()) {
            if(root!=null){
                stack.push(root);
                root=root.left;
            }else{
                root=stack.pop();
                count++;
                if(count==k)return root.val;
                root=root.right;
            }
        }
        return -1;
    }

    int x=0;
    public List<Integer> kthSmallest_Recursive(TreeNode root, List<Integer> list) {
        if(root==null)return list;
        kthSmallest_Recursive(root.left,list);
        list.add(root.val);
        kthSmallest_Recursive(root.right,list);
        return list;
    }

}
