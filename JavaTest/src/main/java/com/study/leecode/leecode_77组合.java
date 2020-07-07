package com.study.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * n=4 k=2
 * 表示从1，2，3，4中随机选择两个数
 * 求一共有多少种输出
 */
public class leecode_77组合 {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTrack(1, n, k, new Stack<>());
        return res;
    }

    public void backTrack(int start, int n, int k, Stack<Integer> stack) {

        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i <= n - (k - stack.size()) + 1; i++) {
            stack.push(i);
            backTrack(i + 1, n, k, stack);
            stack.pop();
        }
    }
}


