package com.study.leecode;


public class leecode_53最大连续子序和 {

    public static void main(String[] args) {
        int[] arr ={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }

    //dp[i]表示以i结尾的最大子序和
    //转移方程  假如或者不加入
    //要么nums[i]独自成为一个子序列 因为i前面的和小于0
    //要么nums[i] 添加在以i-1结尾的子序和中
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }


}
