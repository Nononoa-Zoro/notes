package com.study.leecode;

//https://leetcode-cn.com/problems/the-masseuse-lcci/
public class 按摩师 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};
        System.out.println(solve(arr));
    }

    //dp[i][0] 第i天不接客
    //dp[i][1] 第i天接客
    public static int solve(int[] nums) {

        int len = nums.length;
        if (len == 1) return nums[0];
        int[][] dp = new int[len][2];
        dp[0][0] = 0;//第一天不接客
        dp[0][1] = nums[0];//第一天接客
        for (int i = 1; i < nums.length; i++) {
            //第i天不接客的话 i-1天可以接 也可以不接
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            //第i天接客的话 第i-1天不接客
            dp[i][1] = nums[i] + dp[i - 1][0];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}
