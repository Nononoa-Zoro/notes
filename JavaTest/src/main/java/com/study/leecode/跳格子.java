package com.study.leecode;

public class 跳格子 {

    public static void main(String[] args) {

        System.out.println(recursive(2));
        System.out.println(method(2));

    }

    public static int recursive(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public static int method(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }
}
