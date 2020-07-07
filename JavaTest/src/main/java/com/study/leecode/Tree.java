//package com.study.leecode;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class Tree {
//
//    static TreeNode last;
//    static TreeNode nlast;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] s = scanner.nextLine().split(" ");
//        char[] value = new char[s.length];
//        for(int i=0;i<value.length;i++){
//            value[i]=s[i].charAt(0);
//        }
//        TreeNode tree = getTree(value);
//        printTree(tree);
//
//
//
////        TreeNode root = new TreeNode('1');
////        TreeNode t2 = new TreeNode('2');
////        TreeNode t3 = new TreeNode('3');
////        TreeNode t4 = new TreeNode('#');
////        TreeNode t5 = new TreeNode('#');
////        TreeNode t6 = new TreeNode('6');
////        TreeNode t7 = new TreeNode('7');
////
////        TreeNode t8 = new TreeNode('#');
////        TreeNode t9 = new TreeNode('#');
////        TreeNode t10 = new TreeNode('#');
////        TreeNode t11 = new TreeNode('#');
////        TreeNode t12 = new TreeNode('#');
////        TreeNode t13 = new TreeNode('#');
////        TreeNode t14 = new TreeNode('#');
////
////        TreeNode t15 = new TreeNode('8');
////        root.left=t2;
////        root.right=t3;
////        t2.left=t4;
////        t2.right=t5;
////        t3.left=t6;
////        t3.right=t7;
////        t4.left=t8;
////        t4.right=t9;
////        t5.left=t10;
////        t5.right=t11;
////        t6.left=t12;
////        t6.right=t13;
////        t7.left=t14;
////        t7.right=t15;
////
////        printTree(root);
//
//    }
//
////    1 2 3 # # 6 7 # # # # # # # 8
//    private static TreeNode getTree(char[] value){
//        TreeNode p = new TreeNode(value[0]);
//        TreeNode q = p;
//        Queue<TreeNode> queue = new LinkedList<>();
//        int i=0;
//        while (p!=null){
//            if(2*i+1<value.length){
//                p.left = new TreeNode(value[2*i+1]);
//                queue.add(p.left);
//            }
//            if (2*i+2<value.length){
//                p.right = new TreeNode(value[2*i+2]);
//                queue.add(p.right);
//            }
//            p = queue.poll();
//            i+=1;
//        }
//        return q;
//    }
//
//    public static void printTree(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        last = root;
//        nlast = root;
//        int flag = 1;
//        while (!queue.isEmpty()) {
//            TreeNode t = queue.peek();
//            if (flag == 1) {
//                System.out.print(queue.poll().val + " ");
//                flag = 0;
//            } else {
//                queue.poll();
//            }
//
//            if (t.left != null) {
//                queue.offer(t.left);
//                nlast = t.left;
//            }
//            if (t.right != null) {
//                queue.offer(t.right);
//                nlast = t.right;
//            }
//            // 如果当前输出结点是最右结点，那么换行
//            if (last == t) {
//                flag = 1;
//                last = nlast;
//            }
//        }
//
//    }
//
//    static class TreeNode {
//        public char val;
//        public TreeNode left;
//        public TreeNode right;
//
//        public TreeNode(char data, TreeNode left, TreeNode right) {
//            this.val = data;
//            this.left = left;
//            this.right = right;
//        }
//
//        public TreeNode(char val) {
//            this.val = val;
//        }
//
//    }
//}