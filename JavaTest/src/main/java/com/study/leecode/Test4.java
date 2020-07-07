package com.study.leecode;

/**
 * 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 */
public class Test4 {

    //中心扩展法 o(n^2)
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            int prior = end - start;
            if (len > prior) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }


    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        String res = longestPalindromic("abcba");
        System.out.println(res);
    }

    //暴力法 o(n^3)
    private static boolean isPalindromic(String s) {
        int len = s.length();
        int i = 0;
        while (i < len / 2 && s.charAt(i) == s.charAt(len - i - 1)) {
            i++;
        }
        return i == (len / 2);
    }

    private static String longestPalindromic(String s) {
        String ans = "";
        int max=0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j <=len; j++) {
                String str=s.substring(i,j);
                if(isPalindromic(str)&&str.length()>max){
                    ans=str;
                    max= str.length();
                }
            }
        }
        return ans;
    }
}
