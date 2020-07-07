package com.study.leecode;

public class leecode_122买卖股票的最佳时机2 {
    public int maxProfit(int[] prices) {
        if(prices.length==0||prices==null)return 0;

        //波谷
        int valley = prices[0];
        //波峰
        int peak = prices[0];
        int profit = 0;
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
            peak = prices[i];
            profit += peak - valley;
        }
        return profit;
    }
}
