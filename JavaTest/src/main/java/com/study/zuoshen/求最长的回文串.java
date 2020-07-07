package com.study.zuoshen;

/**
 * dp[i][j] 表示字符串从i到j是不是一个回文串 如果是返回true 否则返回false
 *
 * if(s.charAt(i)==s.charAt(j)){
 *     if(j-1-(i+1)+1<2   返回true
 *     else
 *     dp[i][j]=dp[i+1][j-1]
 * }
 *
 */


public class 求最长的回文串 {

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        boolean[][] dp = new boolean[len][len];

        // 初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int start = 0;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {

                if (s.charAt(i) == s.charAt(j)) {
                    //dp[i+1][j-1] j-1-(i+1)+1<2
                    if (j - i < 3) {
                        //j-i<3只需要判断两端的字符是否一致
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String str = "babad";
        String res = longestPalindrome(str);
        System.out.println(res);
    }
}

