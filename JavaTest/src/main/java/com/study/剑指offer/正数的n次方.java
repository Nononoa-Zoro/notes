package com.study.剑指offer;

public class 正数的n次方 {

    public static void main(String[] args) {
        double s = power(2, 0);
        System.out.println(s);
    }

    public static double power(int x, int n) {
        //x的n次方
        if (n == 0) return 1;
        int e = Math.abs(n);
        double res = 1;

        while (e != 0) {
            //如果是奇数次方 先消耗一个1
            if ((e & 1) != 0) {
                res *= x;
            }
            //底数平方 指数除2
            x *= x;
            e >>= 1;
        }

        return n < 0 ? (1 / res) : res;

    }

}
