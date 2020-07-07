package com.study.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class 图的直径 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        while (caseNum-- > 0) {
            int nodes = scanner.nextInt();
            int[][] array = new int[nodes + 1][nodes + 1];
            scanner.nextLine();
            for (int i = 0; i < nodes; i++) {
                String[] s = scanner.nextLine().split(" ");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                array[x][y] = 1;
                array[y][x] = 1;
            }

            List<Integer> start = findStart(array);
            System.out.println(start);

        }
    }

    private static List<Integer> findStart(int[][] array) {
        ArrayList<Integer> res = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 1) count++;
            }
            if (count == 1) res.add(i);
            count=0;
        }
        return res;
    }

    private static int DFS(int[][] edges, ArrayList<Character> list, char startChar) {
        StringBuilder res = new StringBuilder();
        int startIndex = list.indexOf(startChar);
        boolean[] visited = new boolean[list.size()];
        Stack<Integer> stack = new Stack<>();
        stack.push(startIndex);
        visited[startIndex] = true;
        int step = 0;
        int max = step;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (stack.size() == 0) {
                max = Math.max(step, max);
                step = 0;
            } else {
                step++;
            }
            for (int i = 0; i < list.size(); i++) {
                if (edges[v][i] == 1 && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        return max;
    }

}
