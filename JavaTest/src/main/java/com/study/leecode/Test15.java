package com.study.leecode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 */
public class Test15 {
    public static int maxSubArray(int[] nums) {
       int max = nums[0];
       int[] dp =new int[nums.length];
       dp[0]=max;
       for(int i=1;i<nums.length;i++){
           //nums[i]要么在连续数组里面 要么不在里面
           //dp[i]表示前面i个元素的最大和的连续子数组
           dp[i]= Math.max(dp[i-1]+nums[i],nums[i]);
           max= Math.max(dp[i],max);
       }
       return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int res = maxSubArray(arr);
        System.out.println(res);
    }
}
