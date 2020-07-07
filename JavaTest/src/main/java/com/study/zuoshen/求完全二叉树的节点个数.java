package com.study.zuoshen;

/**
 * 一棵完全二叉树 除了最后一层 每一层的节点都是满的
 * 要求复杂度小于o(n)
 */
public class 求完全二叉树的节点个数 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * @param node  当前节点
     * @param level 当前节点在第几层
     * @return 左子树的叶节点在的层数
     */
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    //递归函数求完全二叉树的节点个数

    /**
     * @param head  头节点
     * @param level 节点在第几层
     * @param h     树的高度
     * @return 完全二叉树的节点个数
     */
    public static int bs(Node head, int level, int h) {
        //如果节点所在的层数=数的高度 说明这棵树只有一个节点
        if (level == h) return 1;

        if (mostLeftLevel(head.right, level + 1) == h) {
            //如果根节点的右子树的左边界可以到达整个完全二叉树的最后一层
            //那么根节点的左子树的节点个数就是  1 << (h - level)
            //右子树递归求解
            return 1 << (h - level) + bs(head.right, level + 1, h);
        } else {
            //否则右子树是一棵完全二叉树 节点个数为 1 << (h - level - 1)
            //递归求解左子树的节点个数
            return 1 << (h - level - 1) + bs(head.left, level + 1, h);
        }
    }

    //主函数
    public int nodeNum(Node head) {
        if (head == null) return 0;
        return bs(head, 1, mostLeftLevel(head, 1));
    }
}
