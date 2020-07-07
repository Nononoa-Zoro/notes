package com.study.zuoshen;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的层次遍历过程中，如果遇到一个节点，它只有左孩子，那么它后面的节点都是叶子节点
 */
public class 判断是否是完全二叉树 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCompleteTree(Node head) {
        if (head == null) return true;
        //层次遍历二叉树
        //是否遇到了只有左孩子的节点
        boolean flag = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node left;
        Node right;

        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;
            //只有右孩子返回false
            //开启了阶段 但是左孩子或者右孩子不为空 返回false
            if ((left == null && right != null) && (flag && (left != null || right != null))) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            } else {
                //左不为空有为空
                flag = true;
            }
        }
        return true;
    }
    
}
