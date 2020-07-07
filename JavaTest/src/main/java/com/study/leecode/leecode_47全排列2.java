package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

/*
 *给定一个可包含重复数字的序列，返回所有不重复的全排列。
 */
public class leecode_47全排列2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        return res;
    }

    public void dfs(int[] nums, int cur) {
        if (cur == nums.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (canSwap(nums, cur, i)) {
                swap(nums, cur, i);
                dfs(nums, cur + 1);
                swap(nums, cur, i);
            }
        }
    }

    private void swap(int[] nums, int cur, int i) {
        int temp = nums[cur];
        nums[cur] = nums[i];
        nums[i] = temp;
    }

    public boolean canSwap(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums[i] == nums[end]) {
                return false;
            }
        }
        return true;
    }


}
