package com.study.leecode;

public class leecode_198打家劫舍 {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];

        int[][] dp = new int[len][2];

        for (int i = 1; i < len; i++) {

            //不偷第i家人 那么第i-1家人可偷可不偷
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            //偷第i家人   那么i-1家就不能偷
            dp[i][1] = nums[i] + dp[i - 1][0];

        }

        //只需要返回最后一个点选或者不选的最大值
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}
