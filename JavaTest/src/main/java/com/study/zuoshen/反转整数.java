package com.study.zuoshen;

/**
 * 输入 123
 * 输出 321
 * 主要是要判断32位有符号整数的左右区间
 *
 */
public class 反转整数 {

    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            //越界
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            ans = ans * 10 + pop;
        }
        return ans;
    }

    public static void main(String[] args) {
        int res = reverse(123);
        System.out.println(res);
    }
}