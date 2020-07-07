package com.study.leecode;

/**
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可获得的最大利润是多少？
 * 例如，一只股票在某些时间节点的价格为{9,11,8,5,7,12,16,14}。
 * 如果我们能在价格为5的时候买入并在价格为16时卖出，则能获得最大的利润为11.
 */
public class 股票的最大利润1 {
    public static void main(String[] args) {
        int[] arr = {9, 11, 8, 5, 7, 12, 16, 14};
        int res = maxProfit(arr);
        System.out.println(res);
    }

    public static int maxProfit(int[] prices) {

        int len = prices.length;
        //统计今天之前的最小值
        int min = Integer.MAX_VALUE;
        //今天卖出的最大利润
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }

        return max;


    }
}
