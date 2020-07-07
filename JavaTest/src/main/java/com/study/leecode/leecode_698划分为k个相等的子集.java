package com.study.leecode;

import java.util.Arrays;

public class leecode_698划分为k个相等的子集 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;
        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }

        return search(new int[k], nums, row, target);
    }

    public boolean search(int[] groups, int[] nums, int row, int target) {

        if (row < 0) return true;
        int value = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + value <= target) {
                groups[i] += value;
                if (search(groups, nums, row, target)) {
                    return true;
                }
                groups[i] -= value;
            }
        }

        return false;
    }
}
