package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。解集不能包含重复的组合
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class leecode_39组合总和 {

    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        int target = 7;
        List<List<Integer>> res = combinationSum(arr, target);
        for(List<Integer> list:res){
            System.out.println(list);
        }
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        //剩下的总和=0 递归结束
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
            return;
        }

        //residue-candidates[i]<0则终止
        for (int i = start; i < candidates.length && residue - candidates[i] >= 0; i++) {
            pre.push(candidates[i]);
            //candidates 中的数字可以无限制重复被选取。所以这里传入的是i
            dfs(residue - candidates[i], i, pre, candidates, res);
            pre.pop();
        }
    }


}