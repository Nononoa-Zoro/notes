package com.study.zuoshen;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给一个cost数组和一个profit数组，分别表示一个项目的成本和利润
 * W表示初始资金 K表示最多做多少个项目
 *
 * 求：在初始资金W下，可以获得的最大的收益（成本+利润）。规定每次只能做一个项目
 */
public class 贪心算法做最多的项目 {

    public static class Node {
        public int cost;
        public int profit;

        public Node(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.profit - o1.profit;
        }
    }

    /**
     *
     * @param k 最多做多少个项目
     * @param w 初始资本
     * @param cost 成本
     * @param profit 利润
     * @return
     */
    public static int findMaxCapital(int k, int w, int[] cost, int[] profit) {

        PriorityQueue<Node> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());

        for (int i = 0; i < cost.length; i++) {
            minCostQueue.add(new Node(cost[i], profit[i]));
        }

        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= w) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            //没有足够资金做项目
            if (maxProfitQueue.isEmpty()) {
                return w;
            }
            w += maxProfitQueue.poll().profit;
        }

        return w;
    }
}
