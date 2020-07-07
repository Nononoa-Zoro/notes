package com.study.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class pdd01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] prices = new Integer[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        if(n==0){
            System.out.println(0);
        }else{
            int payment = solve(prices);
            System.out.println(payment);
        }

    }

    public static int solve(Integer[] prices) {
        Arrays.sort(prices, Collections.reverseOrder());
        int len = prices.length;
        int payment = 0;

        for (int i = 0; i < len; i++) {
            if((i+1)%3!=0){
                payment+=prices[i];
            }
        }

        return payment;


    }
}
