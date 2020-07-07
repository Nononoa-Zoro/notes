package com.study.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//[[1,2], [3], [3], []]
public class leecode_797所有可能的路径 {

    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };
        List<List<Integer>> lists = allPathsSourceTarget(graph);
        System.out.println(lists);
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Stack<Integer> path = new Stack<>();
        path.push(0);
        dfs(0, path, graph);
        return res;
    }

    public static void dfs(int cur, Stack<Integer> path, int[][] graph) {

        if (graph[cur].length == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i : graph[cur]) {
            path.push(i);
            dfs(i, path, graph);
            path.pop();

        }

    }


}
