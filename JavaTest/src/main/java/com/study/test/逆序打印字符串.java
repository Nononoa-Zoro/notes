package com.study.test;

public class 逆序打印字符串 {

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        help(0, s);
        System.out.println(s);
    }

    public static void help(int index, char[] s) {
        if (s == null || index >= s.length) return;
        help(index + 1, s);
        char tmp = s[index];
    }
}
