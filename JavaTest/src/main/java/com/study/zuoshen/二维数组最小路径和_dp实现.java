package com.study.zuoshen;

/**
 * 给定一个二维数组，所有的元素都是正数
 * 规则：只能向下或者向右走
 * 求从左上角到右下角的最短路径和
 */
public class 二维数组最小路径和_dp实现 {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };

        int res = minCost(matrix);
        System.out.println(res);

    }

    public static int minCost(int[][] cost) {
        int row = cost.length;
        int col = cost[0].length;

        int[][] dp = new int[row][col];

        dp[0][0] = cost[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + cost[i][0];
        }

        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + cost[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //因为规则只能是向下或者向右走
                //所以当前节点只能是左边或者是上面的节点走过来的
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + cost[i][j];
            }
        }

        return dp[row - 1][col - 1];

    }

    public static int min(int x, int y, int z) {
        if (x < y) {
            return x < z ? x : z;
        } else {
            return y < z ? y : z;
        }

    }
}
