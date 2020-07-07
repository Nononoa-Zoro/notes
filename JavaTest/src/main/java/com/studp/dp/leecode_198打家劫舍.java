package com.studp.dp;

public class leecode_198打家劫舍 {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int[][] dp = new int[len][2];
        //第一家不偷
        dp[0][0] = 0;
        //第一家要偷
        dp[0][1] = nums[0];

        for (int i = 1; i < len; i++) {
            //第i家不偷
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            //第i家偷 第i-1家不偷
            dp[i][1] = nums[i] + dp[i - 1][0];
        }
        //返回最后一家偷不偷的最大值
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}
