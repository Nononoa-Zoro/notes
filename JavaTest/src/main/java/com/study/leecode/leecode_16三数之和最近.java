package com.study.leecode;

import java.util.Arrays;

public class leecode_16三数之和最近 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 0};
        int target = -100;
        int res = threeSumClosest(arr, target);
        System.out.println(res);
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int start = i + 1, end = len - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum > target)
                    end--;
                else if (sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
}

