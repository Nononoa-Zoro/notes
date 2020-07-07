package com.studp.dp;

//01背包问题
public class 背包问题01 {


    public static void main(String[] args) {
        int n = 5, m = 10;                    //物品个数，背包容量
        int[] value = {0, 6, 3, 5, 4, 6};     //各个物品的价值
        int[] weight = {0, 2, 2, 6, 5, 4};    //各个物品的重量
        int res = solve(weight, value, n, m);
        System.out.println(res);
    }

    //dp[i][j]表示 在背包容量为j的条件下，考虑前i个物品的最大价值
    //N表示物品种类
    //M表示背包最大容量
    public static int solve(int[] weight, int[] value, int N, int M) {

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                //第i个物品的重量超过 背包重量  肯定不能放入
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //否则 考虑第i个物品放不放的最大值
                    dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j]);
                }
            }
        }

        return dp[N][M];
    }
}
