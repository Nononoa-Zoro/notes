package com.study.leecode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 */
public class 股票的最大利润2 {
    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        int res = maxProfit(arr);
        System.out.println(res);
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        //波谷
        int valley = prices[0];
        //波峰
        int peak = prices[0];
        //利润
        int profit = 0;
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) i++;
            peak = prices[i];
            profit += peak - valley;
        }
        return profit;
    }
}
