package com.study.leecode;

import java.util.LinkedList;
import java.util.Queue;

public class leecode_1361验证二叉树 {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            if (leftChild[i] != -1) arr[leftChild[i]]++;
            if (rightChild[i] != -1) arr[rightChild[i]]++;
        }
        int count = 0;
        int root = -2;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                root = i;
                count++;
            }
            if (count > 1) return false;
        }
        if (root == -2) return false;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(root);
        visited[root] = true;
        while (!queue.isEmpty()) {
            Integer x = queue.poll();
            int[] childs = {leftChild[x], rightChild[x]};
            for (int s : childs) {
                if (s != -1) {
                    if (visited[s]) return false;
                    queue.add(s);
                    visited[s] = true;
                }
            }
        }

        for (boolean state : visited) {
            if (!state) {
                return false;
            }
        }

        return true;

    }


}
