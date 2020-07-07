package com.study.leecode;

import java.util.Scanner;

public class pdd02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = scanner.nextInt();
        }
        int res = solve(M, trees);
        System.out.println(res);

    }

    public static int solve(int M, int[] trees) {

        int res = 0;
        int size = 1;
        while (size < trees.length) {
            for (int i = 0; i <= trees.length - size; i++) {
                int sum = 0;
                for (int j = 0; j < size; j++) {
                    sum += trees[i + j];
                }
                if (sum % M == 0) res++;
            }
            size++;
        }

        int x=0;
        for(int i=0;i<trees.length;i++){
            x+=trees[i];
        }
        if(x%M==0)res++;
        return res;
    }
}
