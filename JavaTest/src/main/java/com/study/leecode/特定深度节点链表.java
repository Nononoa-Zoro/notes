package com.study.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 特定深度节点链表 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static TreeNode unserialByPreOrder(String s) {
        String[] arr = s.split(" ");
        Queue<String> queue = new LinkedList<>();
        for (String str : arr) {
            queue.add(str);
        }
        TreeNode root = dfs(queue);
        return root;
    }

    public static TreeNode dfs(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = dfs(queue);
        root.right = dfs(queue);
        return root;
    }

    public static void main(String[] args) {
        String s = "1 2 4 8 # # # 5 # # 3 # 7 # #";
        TreeNode tree = unserialByPreOrder(s);
        ListNode[] lists = listOfDepth(tree);
        ListNode node;
        for (ListNode head : lists) {
            node = head;
            while (node != null) {
                System.out.print(node.val + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    public static ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> res = new ArrayList<>();
        if (tree == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            ListNode head = null;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                ListNode tmp = new ListNode(node.val);
                if(head==null){
                    head=tmp;
                }else{
                    tmp.next=head;
                    head=tmp;
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }


            }
            res.add(head);

        }
        return res.toArray(new ListNode[count]);

    }
}
