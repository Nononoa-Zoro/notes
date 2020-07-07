package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 */
public class leecode_90求集合的子集二 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res = subsetsWithDup(nums);
        System.out.println(res);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(new Stack<>(), res, 0, nums);
        return res;
    }

    public static void dfs(Stack<Integer> stack, List<List<Integer>> res, int start, int[] nums) {
        res.add(new ArrayList<>(stack));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            stack.push(nums[i]);
            dfs(stack, res, i + 1, nums);
            stack.pop();
        }
    }

}
