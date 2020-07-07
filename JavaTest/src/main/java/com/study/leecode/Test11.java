package com.study.leecode;

/**
 * DP
 * arr={3,34,4,12,5,2}
 * 数组中都是正数
 * 问题：能否在数组中找到多个数，使得他们的和等于给定的正数 S
 *
 */
public class Test11 {
    //从0到i需要找到数字之和为s
    private static boolean rec_subset(int[] arr, int i, int s) {
        //已经找到了
        if (s == 0) {
            return true;
        }
        //只剩下一个 这个数一定是需要的s
        if (i == 0) {
            return arr[i] == s;
        }
        //如果选了这个数之后大于s，那只能不选择这个数
        if (arr[i] > s) {
            return rec_subset(arr, i - 1, s);
        }
        //递归 选或者不选这个数
        return rec_subset(arr, i - 1, s - arr[i]) || rec_subset(arr, i - 1, s);
    }

    private static boolean dp_subset(int[] arr, int num) {
        boolean[][] subset = new boolean[arr.length][num + 1];
        for (int j = 0; j < subset.length; j++) {
            subset[j][0] = true;
        }
        for (int i = 1; i < num + 1; i++) {
            if (i == arr[0]) {
                subset[0][i] = true;
                continue;
            }
            subset[0][i] = false;
        }

        for (int i = 1; i < subset.length; i++) {
            for (int s = 1; s < num + 1; s++) {
                if (arr[i] > s) {
                    subset[i][s] = subset[i - 1][s];
                } else {
                    subset[i][s] = subset[i - 1][s - arr[i]] || subset[i - 1][s];
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < num + 1; j++) {
                System.out.format("%b  ", subset[i][j]);
            }
            System.out.println();
        }
        return subset[arr.length - 1][num];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        boolean res1 = rec_subset(arr, 5, 7);
        System.out.println(res1);
        boolean b = dp_subset(arr, 9);
        System.out.println(b);
    }

}
