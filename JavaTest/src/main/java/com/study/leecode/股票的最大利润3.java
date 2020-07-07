package com.study.leecode;

import java.util.Arrays;

/**
 *123 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 */
public class 股票的最大利润3 {

    public static void main(String[] args) {
        int[] arr ={3,3,5,0,0,3,1,4};
        int res = maxProfit(arr);
        System.out.println(res);
    }
    public static int maxProfit(int[] prices){
        if (prices==null || prices.length<=0) {
            return 0;
        }
        //状态定义：
        //j=0 什么都不做
        //j=1 第一次买入
        //j=2 第一次卖出
        //j=3 第二次买入
        //j=4 第二次卖出
        int[][] dp=new int[prices.length][5];
        int INF=Integer.MIN_VALUE,n=prices.length;
        Arrays.fill(dp[0],INF); //不可达状态
        dp[0][0]=0;
        dp[0][1]=-prices[0];//第一次买入 -prices[0]
        for(int i=1;i<n;i++){
            dp[i][0]=0;
            dp[i][1]=Math.max(-prices[i],dp[i-1][1]);
            dp[i][2]=Math.max(dp[i-1][1]+prices[i],dp[i-1][2]);
            //第i天处于第二次买入状态最大值 = max(前一天处于第一次卖出状态 今天又新买入 , 前一天处于第二次买入状态)
            dp[i][3]=Math.max(dp[i-1][2]!=INF?dp[i-1][2]-prices[i]:INF,dp[i-1][3]);
            dp[i][4]=Math.max(dp[i-1][3]!=INF?dp[i-1][3]+prices[i]:INF,dp[i-1][4]);
        }
        return Math.max(Math.max(dp[n-1][0],dp[n-1][2]),dp[n-1][4]);
    }

}
