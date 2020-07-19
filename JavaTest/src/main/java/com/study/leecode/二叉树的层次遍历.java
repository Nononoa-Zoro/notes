package com.study.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的层次遍历 {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        char c;

        public TreeNode(char c) {
            this.c = c;
        }
    }

    //二叉树的层次遍历
    public static List<List<Character>> levelOrder(TreeNode root) {
        List<List<Character>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //存每一行的数据
            ArrayList<Character> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.c);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(list);
        }
        return res;
    }

    //1 2 3 # # 6 7 # # # # # # # 8
    private static TreeNode getTree(char[] value) {
        TreeNode tmp = new TreeNode(value[0]);
        TreeNode root = tmp;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tmp);
        int i = 0;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (2 * i + 1 < value.length) {
                tmp.left = new TreeNode(value[2 * i + 1]);
                queue.add(tmp.left);
            }
            if (2 * i + 2 < value.length) {
                tmp.right = new TreeNode(value[2 * i + 2]);
                queue.add(tmp.right);
            }
            i += 1;
        }
        return root;
    }

    public static void main(String[] args) {
        String s = "1 2 3 # # 6 7 # # # # # # # 8";
        String[] s1 = s.split(" ");
        char[] value = new char[s1.length];
        for (int i = 0; i < s1.length; i++) {
            value[i] = s1[i].charAt(0);
        }
        TreeNode root = getTree(value);
        List<List<Character>> cur = levelOrder(root);
        System.out.println(cur);

        List<Character> res = new ArrayList<>();

        boolean flag = true;

        for (List<Character> list : cur) {
            for (Character c : list) {
                if (c != '#' && flag) {
                    res.add(c);
                    flag = false;
                }
            }
            flag = true;
        }

        System.out.println(res);
    }

}
