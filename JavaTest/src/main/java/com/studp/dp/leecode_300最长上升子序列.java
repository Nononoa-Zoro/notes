package com.studp.dp;

public class leecode_300最长上升子序列 {

    //dp[i] 以i为结尾的最大的上升子序列的长度
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int len = nums.length;
        if(len==0)return 0;
        int[] dp = new int[len];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
