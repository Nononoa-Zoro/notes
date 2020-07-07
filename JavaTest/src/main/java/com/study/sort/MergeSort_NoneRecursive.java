package com.study.sort;

import java.util.Scanner;


public class MergeSort_NoneRecursive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int len = scanner.nextInt();
            int[] arr = new int[len];

            for (int i = 0; i < len; i++) {
                arr[i] = scanner.nextInt();
            }

            sort(arr);
            StringBuilder res = new StringBuilder();
            for (int value : arr) {
                res.append(value).append(" ");
            }
            System.out.println(res.toString().trim());
        }
    }

    /**
     * 归并排序（非递归实现）
     * 时间复杂度O(nlogn)
     * 空间复杂度O(n)
     */
    private static void sort(int[] arr) {
        // 从 1开始分割，与递归不同的是，递归由数组长度一分为二最后到1，
        // 而非递归则是从1开始扩大二倍直到数组长度
        int len = 1;

        while (arr.length > len) {

            // 完全二叉树一层内的遍历
            for (int i = 0; i + len <= arr.length - 1; i += len * 2) {
                int left;
                left = i;
                int mid = i + len - 1;
                int right = i + len * 2 - 1;

                // 防止超出数组长度
                if (right > arr.length - 1)
                    right = arr.length - 1;

                // 合并排序相同
                merge(arr, left, mid, right);
            }

            // 下一层
            len *= 2;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        // 注意： 此处并没有全部放入temp中，当一边达到mid或right时就是退出循环
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        // 如果左边或右边有剩余，则继续放入，只可能一边有剩余
        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        // 排好序的临时数组重新放入原数组
        for (int m = 0; m < temp.length; m++) {
            arr[m + left] = temp[m];
        }
    }

}

