package com.study.leecode;

/**
 * int 数字反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 输入: 123
 * 输出: 321
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 输入: 120
 * 输出: 21
 */
public class Test6 {
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
        change(1024);
    }

    public static void change(int n) {
        int ans = 0;
        while (n != 0) {
            int x = n % 10;
            n /= 10;
            ans = ans * 10 + x;
        }
        System.out.println(ans);
    }
}
