package com.study.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 快速排序算法
 * 找到一个枢纽值 将数组划分为 左边的值都比该枢纽值小 右边的值都比该枢纽值大
 * <p>
 * 快排是不稳定的排序算法
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 3, 2, 9, 7, 1, 5, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int privot = partion(arr, left, right);
            quickSort(arr, left, privot - 1);
            quickSort(arr, privot + 1, right);
        }
    }

    public static int partion(int[] arr, int low, int high) {
        int privot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= privot) high--;
            swap(arr, low, high);
            while (low < high && arr[low] <= privot) low++;
            swap(arr, low, high);
        }
        return low;
    }



    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
