package com.study.leecode;

public class 生成字符串的全排列 {
    public static void main(String[] args) {
        String test = "abc";
        permutation(test.toCharArray(), 0, test.length() - 1);
    }

    public static void permutation(char[] str, int start, int end) {
        //base case
        if (start == end) {
            System.out.println(String.valueOf(str));
            return;
        }

        //对于当前位置你有多少个选择 end-start个选择
        for (int i = start; i <= end; i++) {
            //换头
            swap(str, i, start);
            permutation(str, start + 1, end);
            swap(str, i, start);
        }

    }

    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }
}
