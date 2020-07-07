package com.study.zuoshen;

/**
 * 求数组中排序好的相邻两个数的最大差值
 * 不能使用比较排序 使用桶排序
 */
public class Test5 {
    private static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = arr.length;
        for (int value : arr) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        if (min == max) return 0;
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid ;
        for (int i = 0; i < len; i++) {
            bid = bucket(arr[i], len, min, max);
            mins[i] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax;//前一个桶的最大值
        lastMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

    public static void main(String[] args) {
        int[] arr = {1, 2,3,5,7,8};
        int res = maxGap(arr);
        System.out.println(res);
    }
}
