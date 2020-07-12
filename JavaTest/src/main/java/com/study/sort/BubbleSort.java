package com.study.sort;

import java.util.Scanner;

/**
 * 冒泡排序算法
 * 两两相邻进行比较 如果逆序则交换
 * <p>
 * 1.最好情况下 原列表顺序  只需要比较n-1次 算法复杂度o(n)
 * 2.最坏情况下 原列表逆序  需要比较(n-1)*n/2次 算法复杂度o(n^2)
 * <p>
 * 归并排序是稳定的排序算法
 */
public class BubbleSort {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] sortedArr = bubbleSort(arr);
        for (int i : sortedArr) {
            System.out.println(i);
        }
    }

    private static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        boolean flag = true;
        // 标志位表示一轮循环中是否发生交换 发生交换flag=true 不发生flag=false
        // 如果没有发生交换说明已经有序，可以退出循环
        for (int i = 0; i < length && flag; i++) {
            flag = false;
            //注意这里内层循环从length-2开始因为有j+1
            //从最后面开始进行两两比较 如果前者比后者大 则交换
            //0-i是有序的 所以j>=i
            for (int j = length - 2; j >= i; j--) {
                if (arr[j] > arr[j + 1]) {
                    //如果前面的元素大于后面的元素 则交换
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
