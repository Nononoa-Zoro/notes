package com.study.leecode;

public class 圆圈中剩下的最后一个数字 {


    public int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; i++) {
            f = (m + f) % i;
        }
        return f;
    }

}
