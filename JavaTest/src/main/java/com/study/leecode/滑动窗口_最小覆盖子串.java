package com.study.leecode;
//lecode76

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 */
public class 滑动窗口_最小覆盖子串 {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) return res;
        int[] hash = new int[128];
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i) - 'A']++;
        }

        int cnt = 0;
        for (int a : hash) {
            if (a > 0) cnt++;
        }

        char[] chars = s.toCharArray();
        for (int i = 0, j = 0, c = 0; i < chars.length; i++) {
            if (hash[chars[i] - 'A'] == 1) c++;
            hash[chars[i] - 'A']--;
            while (c == cnt && hash[chars[j] - 'A'] < 0) hash[chars[j++] - 'A']++;
            if (c == cnt) {
                if (res == "" || i - j + 1 < res.length()) {
                    res = s.substring(j, i + 1);
                }
            }
        }
        return res;

    }

}
