package com.study.zuoshen;

public class 判断一棵树是不是平衡二叉树 {

    class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
    }

    class ReturnData{
        boolean isB;
        int h;
        ReturnData(boolean isB,int h){
            this.isB=isB;
            this.h=h;
        }
    }

    //判断一棵树是不是平衡二叉树
    public boolean isB(TreeNode root){
        return process(root).isB;
    }

    public ReturnData process(TreeNode root){
        if(root==null){
            return new ReturnData(true,0);
        }

        ReturnData leftData = process(root.left);
        if(!leftData.isB){
            return new ReturnData(false,0);
        }
        ReturnData rightData = process(root.right);
        if(!rightData.isB){
            return new ReturnData(false,0);
        }
        if(Math.abs(leftData.h- rightData.h)>1){
            return new ReturnData(false,0);
        }

        return new ReturnData(true, Math.max(leftData.h,rightData.h)+1);
    }
}
