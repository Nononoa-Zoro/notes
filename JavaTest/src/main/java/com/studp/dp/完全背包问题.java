package com.studp.dp;

public class 完全背包问题 {

    public static void main(String[] args) {
        int[] weight = {0, 3, 4, 2};
        int[] val = {0, 4, 5, 3};

        int res = solve(weight, val, 3, 7);
        System.out.println(res);
    }

    //dp[i][j] 考虑前i个物品 总重量不超过j 的最大价值
    public static int solve(int[] weight, int[] value, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (weight[i] > j) dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = Math.max(dp[i][j - weight[i]] + value[i], dp[i - 1][j]);
                }
            }
        }

        return dp[n][m];

    }
}
