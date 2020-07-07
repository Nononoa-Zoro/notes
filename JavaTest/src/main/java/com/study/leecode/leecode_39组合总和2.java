package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class leecode_39组合总和2 {

    public static void main(String[] args) {
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> res = combinationSum2(arr, target);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> pre = new Stack<>();
        if (candidates.length == 0) return res;
        dfs(target, 0, pre, candidates, res);
        return res;
    }

    /**
     * @param residue    剩下的总和
     * @param start      下一次递归的开始位置
     * @param pre        已经减过的数
     * @param candidates 输入数组
     * @param res        输出：二维数组
     */
    public static void dfs(int residue, int start, Stack<Integer> pre, int[] candidates, List<List<Integer>> res) {
        //剩下的总和递归结束
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
            return;
        }

        for (int i = start; i < candidates.length && residue >= candidates[i]; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            pre.push(candidates[i]);
            //这里candidates中的值不能被重复选取 所以传入的是i+1
            dfs(residue - candidates[i], i+1 , pre, candidates, res);
            pre.pop();
        }
    }


}