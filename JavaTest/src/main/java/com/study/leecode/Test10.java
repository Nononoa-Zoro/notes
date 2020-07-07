package com.study.leecode;

/**
 * DP
 * 给定数组 1 2 4 1 7 8 3
 * 规则：不能选择相邻的数，使得最后选出的数的和最大
 */
public class Test10 {

    private static int rec_opt(int[] arr, int i) {
        if (i == 0) {
            return arr[0];
        } else if (i == 1) {
            return Math.max(arr[0], arr[1]);
        } else {
            return Math.max(rec_opt(arr, i - 2) + arr[i], rec_opt(arr, i - 1));
        }
    }

    private static int dp_opt(int[] arr) {
        int[] opt = new int[arr.length];
        opt[0] = arr[0];
        opt[1] = Math.max(opt[0], opt[1]);
        for (int j = 2; j < arr.length; j++) {
            int x = opt[j - 2] + arr[j];
            int y = opt[j - 1];
            opt[j] = Math.max(x, y);
        }
        return opt[arr.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 9, 1};
        int res = dp_opt(arr);
        System.out.println(res);
    }

}
