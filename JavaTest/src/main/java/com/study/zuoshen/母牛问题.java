package com.study.zuoshen;

/**
 * 所有牛不会死
 * 一只牛三年后才会成熟，成熟的牛会生一只牛
 */
public class 母牛问题 {

    public static void main(String[] args) {
        int cow = cow(6);
        System.out.println(cow);
    }

    public static int cow(int n) {
        if (n < 1) return -1;
        if (n <= 3) return n;
        return cow(n - 1) + cow(n - 3);
    }
}
