package com.study.leecode;

public class leecode_14最长公共前缀 {

    public static void main(String[] args) {
        String[] str = {"flower", "flow", "flight"};
        String res = longestCommonPrefix1(str);
        System.out.println(res);
    }

    //自己写的
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder builder = new StringBuilder();
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            min = Math.min(str.length(), min);
        }
        while (index < min) {
            for (int i = 1; i < strs.length; i++) {
                char x = strs[i - 1].charAt(index);
                char y = strs[i].charAt(index);
                if (x != y) return builder.toString();
            }
            builder.append(strs[0].charAt(index));
            index++;
        }
        return builder.toString();
    }

    //leecode的题解
    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
