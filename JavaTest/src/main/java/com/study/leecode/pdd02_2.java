package com.study.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class pdd02_2 {

    public static int cal(int m, int n) {
        int i, j, sum = 1;
        for (i = m, j = 0; j < n; j++, i--) {
            sum = sum * i / (j + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] x = new int[n + 1];
        x[0] = 0;
        int res = 0;
        int w = 0;
        for (int i = 1; i <= n; ++i) {
            w = scanner.nextInt();
            x[i] = (x[i - 1] + w) % m + m;
        }
        Arrays.sort(x);
        int s = 1;
        for (int i = 1; i <= n; ++i) {
            if (x[i] == x[i - 1]) {
                s++;
            } else {
                res += cal(s, 2);
                s = 1;
            }
        }

        res += cal(s, 2);
        System.out.println(res);

    }


}
