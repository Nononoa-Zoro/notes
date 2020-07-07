package com.study.zuoshen;

/**
 * 后继节点：中序遍历的后面一个节点
 * 前驱节点：中序遍历的前面一个节点
 */
public class 找二叉树的后继节点 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    //找到某棵树的最左边的节点
    public static Node getMostLeftNode(Node node) {
        if (node == null) return node;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //找后继节点
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        //如果当前节点的右子树不为空，那么后继节点就是当前节点的右子树的最左节点
        if (node.right != null) {
            return getMostLeftNode(node.right);
        } else {
            Node parent = node.parent;
            //最终的停止条件
            //父节点的左孩子是当前节点
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
