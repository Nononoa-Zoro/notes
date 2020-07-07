package com.study.leecode;

import java.util.*;

public class leecode_239单调队列_滑动窗口的最大值 {

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(res));

    }

    //维护一个单调递减队列 没次只需要返回队首元素即可
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            //因为要保证队列的长度为k 所以i-k+1也就是窗口大小为K的第一个元素的索引位置 要是大于队首的元素 则删除队首元素
            if (!queue.isEmpty() && i - k + 1 > queue.peekFirst()) queue.pollFirst();
            //如果当前元素大于队列最后一个元素 打破了单调队列的规则 删除队尾元素
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) queue.pollLast();
            //其他情况向队列中添加元素
            queue.add(i);
            //每次向返回结果中放入队首元素
            if (i >= k - 1) res[i - k + 1] = nums[queue.getFirst()];
        }

        return res;
    }
}
