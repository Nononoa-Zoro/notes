package com.study.zuoshen;

public class 打印字符串的子序列 {

    public static void main(String[] args) {
        String test = "abc";
        printAllSubSequcence(test.toCharArray(), 0, "");
    }

    public static void printAllSubSequcence(char[] chars, int start, String res) {
        //base case
        if (start == chars.length) {
            System.out.println(res);
            return;
        }
        //每一个位置的字母有两个抉择，要么不要 要么要
        printAllSubSequcence(chars, start + 1, res);
        printAllSubSequcence(chars, start + 1, res + String.valueOf(chars[start]));
    }
}
