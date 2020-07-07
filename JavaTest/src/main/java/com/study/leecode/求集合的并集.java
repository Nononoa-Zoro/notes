package com.study.leecode;

import java.util.Scanner;

public class 求集合的并集 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int line = 0;


        int sizeA = scanner.nextInt();
        int sizeB = scanner.nextInt();
        int[] arrA = new int[sizeA];
        int[] arrB = new int[sizeB];

        for(int i=0;i<sizeA;i++){
            arrA[i]=scanner.nextInt();
        }

        for(int i=0;i<sizeB;i++){
            arrB[i]=scanner.nextInt();
        }


    }
}
