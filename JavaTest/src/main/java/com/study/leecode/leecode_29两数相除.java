package com.study.leecode;

public class leecode_29两数相除 {


    public static void main(String[] args) {
        int res = divide(-2147483648, 2);
        System.out.println(res);

    }

    //除数 被除数
    public static int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) return -dividend;
            else {
                return Integer.MAX_VALUE;
            }
        }
        boolean sign = true;

        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = false;
        }

        long a,b;
        b = divisor > 0 ? divisor : -divisor;
        if(dividend==Integer.MIN_VALUE){
            return -(int) ((Integer.MAX_VALUE) /b+1);
        }
         a = dividend > 0 ? dividend : -dividend;


        long res = div(a, b);
        if (sign) {
            //正数
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        } else {
            return -(int) res;
        }


    }

    public static long div(long a, long b) {
        if (a < b) return 0;
        long count = 1;
        long tb = b;
        while (tb * 2 <= a) {
            count *= 2;
            tb *= 2;
        }
        return count + div(a - tb, b);
    }
}
