package com.study.leecode;

public class leecode_300最长上升子序列 {

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(arr));
    }
    public static int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(dp[i], res);
        }
        return res;
    }


}
