package com.study.leecode;

import java.util.Scanner;

public class ali1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int[] arr = new int[s.length];

        for(int i=0;i<arr.length;i++){
            arr[i]=Integer.parseInt(s[i]);
        }

    }



}
