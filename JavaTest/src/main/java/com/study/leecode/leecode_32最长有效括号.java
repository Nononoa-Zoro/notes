package com.study.leecode;

public class leecode_32最长有效括号 {

    // 0 1 2 3 4
    // ( ( ( ) )
    public static int longestValidParentheses(String s) {
//        int maxans = 0;
//        int dp[] = new int[s.length()];
//        for (int i = 1; i < s.length(); i++) {
//            if (s.charAt(i) == ')') {
//                if (s.charAt(i - 1) == '(') {
//                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
//                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
//                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
//                }
//                maxans = Math.max(maxans, dp[i]);
//            }
//        }
//        return maxans;
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (s.charAt(i - 1) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + (dp[i - 1] + 2);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = ")(((((()())()()))()(()))(";
        int res = longestValidParentheses(s);
        System.out.println(res);
    }
}
