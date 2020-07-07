package com.study.leecode;

/**
 * 质因数分解
 */
public class Test2 {

    public static void solve1(int n) {
        for (int i = 2; i <= n; i++) {
            if (n == i) {
                System.out.println(i);
                return;
            }
            if (n > i && (n % i == 0)) {
                System.out.println(i);
                solve1(n / i);
                break;
            }
        }
    }

    public static void solve2(int n) {
        int i = 2;
        while (i * i <= n) {
            while (n % i == 0) {
                System.out.print(i+" ");
                n = n / i;
            }
            i++;
        }
        if (n > 1) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        solve2(100);
    }

}
