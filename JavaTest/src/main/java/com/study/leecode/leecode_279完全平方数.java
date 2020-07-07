package com.study.leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class leecode_279完全平方数 {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(numSquares(i));
            System.out.println(dfs(i, new HashMap<>()));
        }
    }

    //BFS广度优先搜索
    public static int numSquares(int n) {
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(n);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                Integer cur = queue.poll();
                for (int j = 1; j * j <= cur; j++) {
                    int next = cur - j * j;
                    if (next == 0) {
                        return level;
                    }
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }

    //DFS
    public static int dfs(int n, HashMap<Integer, Integer> map) {

        if (map.containsKey(n)) {
            return map.get(n);
        }

        //base case
        if (n == 0) {
            return 0;
        }

        int count = Integer.MAX_VALUE;

        for (int i = 1; i * i <= n; i++) {
            count = Math.min(dfs(n - i * i, map) + 1, count);
        }

        map.put(n, count);

        return count;
    }

}
