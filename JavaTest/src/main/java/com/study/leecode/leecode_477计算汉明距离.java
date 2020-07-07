package com.study.leecode;

public class leecode_477计算汉明距离 {
    static int sum = 0;

    public static void main(String[] args) {
        int[] arr = {4, 14, 2};
        int res = totalHammingDistance(arr);
        System.out.println(res);
    }

    public static int totalHammingDistance(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int x = nums[i] ^ nums[j];
                String str = Integer.toBinaryString(x);
                int res = findOne(str);
                sum += res;
            }
        }
        return sum;
    }

    public static int findOne(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (char c : chars) {
            if (c == '1') count++;
        }
        return count;
    }
}
