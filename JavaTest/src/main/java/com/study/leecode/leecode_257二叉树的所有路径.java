package com.study.leecode;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leecode_257二叉树的所有路径 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();
        dfs(root,res, "");
        return res;

    }

    public void dfs(TreeNode root, List<String> list, String path) {
        if (root != null) {
            path += root.val;
            if (root.left == null && root.right == null) {
                list.add(path);
            } else {
                path += "->";
                dfs(root.left,list,path);
                dfs(root.right,list,path);
            }
        }
    }
}
