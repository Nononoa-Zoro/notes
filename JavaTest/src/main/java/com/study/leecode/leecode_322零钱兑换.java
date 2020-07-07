package com.study.leecode;

import java.util.Arrays;

public class leecode_322零钱兑换 {

    public static void main(String[] args) {
        int[] coins = {1,5,10};
        int res = coinChange(coins, 26);
        System.out.println(res);
    }



    public static int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;

        Arrays.sort(coins);

        int[] tmp = new int[coins.length];

        //面额按照降序排列
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = coins[coins.length - i - 1];
        }

        dfs(tmp, amount, 0, 0);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    static int ans = Integer.MAX_VALUE;

    public static void dfs(int[] coins, int amount, int index, int count) {
        if (amount == 0) {
            ans = Math.min(ans, count);
            return;
        }

        if (index == coins.length) return;

        //当前最多选几个面额最大的
        for (int k = amount / coins[index]; k >= 0 && k + count < ans; k--) {
            dfs(coins, amount - k * coins[index], index + 1, count + k);
        }

    }


}
