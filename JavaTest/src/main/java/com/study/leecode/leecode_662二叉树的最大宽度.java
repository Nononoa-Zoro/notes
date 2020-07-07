package com.study.leecode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leecode_662二叉树的最大宽度 {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.data = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        root.left = t1;
        root.right = t2;
        t1.left = t5;
        t1.right = t6;
        t2.right = t9;

        int res = widthOfBinaryTree(root);
        System.out.println(res);

    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Deque<Integer> list = new LinkedList<>();
        queue.add(root);
        list.add(0);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //二叉树的一层
            while (size-- > 0) {
                TreeNode node = queue.poll();
                int parent = list.removeFirst();
                if (node.left != null) {
                    queue.add(node.left);
                    list.add(2 * parent + 1);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    list.add(2 * parent + 2);
                }
            }
            if (list.size() >= 2) {
                res = Math.max(res, list.getLast() - list.getFirst() + 1);
            }
        }
        return res;
    }
}
