package com.study.zuoshen;
//问题：切金条问题
//关键词：贪心、哈夫曼编码
//描述：一块金条切成两半，需要花费和长度数值一样的铜板。求最省铜板的切金条方法
//     例如，给定数组[10，20，30]，代表要把长度为10+20+30=60的金条分成10，20，30三个部分
//     如果先把长度为60的金条分成10和50，花费60，
//     再把长度为50的金条分成20和30，花费30，总共花费110铜板
//     但如果先把长度为60的金条分成30和30，花费60，
//     再把长度为30的金条分成10和20，花费30，总共花费90铜板

import java.util.Comparator;
import java.util.PriorityQueue;

//要求：数组一个数组，返回分割的最小代价
//分析：这道题目属于经典的哈夫曼编码问题，建立小根堆，每次从小根堆的堆顶取走两个元素
//      再将这两个元素的和放进小根堆中，直到小根堆只剩下一个元素，即为分割的最小代价
//思考：当一个问题是求代价时，它总共的代价是由子代价的某种计算形成的
//      这种问题都可以考虑采用哈夫曼编码的贪心策略去解决
public class 切金条问题 {

    private static PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public static int huffman(int[] arr) {

        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        } else if (arr.length == 2) {
            return arr[0] + arr[1];
        }

        int cost = 0;

        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }

        while (queue.size() > 1) {
            int less1 = queue.poll();
            int less2 = queue.poll();
            queue.add(less1 + less2);
            cost += less1 + less2;
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] arr = {10,20,30};
        int res = huffman(arr);
        System.out.println(res);
    }
}
