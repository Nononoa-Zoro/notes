package com.study.leecode;

/**
 * 最长有效括号
 */
public class Test16 {
    public static int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
            } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
            }
            max= Math.max(max,dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int res = longestValidParentheses("(())(()(((())))");
        System.out.println(res);
    }
}
