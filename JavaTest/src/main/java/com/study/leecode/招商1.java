package com.study.leecode;

import java.util.Scanner;

public class 招商1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        while (cases-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            int res = method(arr);
            System.out.println(res);
        }

    }


    public static int method(int[] arr) {
        int zeros = 0, moreThan2_nums = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++;
            }
            if (arr[i] >= 2) {
                moreThan2_nums++;
            }
        }

        //没有一个超过2的
        if (moreThan2_nums == 0) return -1;

        return arr.length - zeros + 1;
    }
}
