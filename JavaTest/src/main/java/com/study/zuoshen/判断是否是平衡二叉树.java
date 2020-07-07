package com.study.zuoshen;

public class 判断是否是平衡二叉树 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //每个部分返回两个数据
    public static class ReturnData {
        //子树是否是平衡的
        public boolean isBalance;
        //子树的高度
        public int height;

        public ReturnData(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static ReturnData process(Node node) {
        if (node == null) {
            return new ReturnData(true, 0);
        }

        ReturnData leftData = process(node.left);
        if (!leftData.isBalance) {
            return new ReturnData(false, 0);
        }

        ReturnData rightData = process(node.right);
        if (!rightData.isBalance) {
            return new ReturnData(false, 0);
        }

        int a = Math.abs(leftData.height - rightData.height);
        if (a > 1) {
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);
    }

    public static boolean isBalance(Node node) {
        return process(node).isBalance;
    }


}

