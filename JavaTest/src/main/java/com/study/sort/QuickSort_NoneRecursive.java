package com.study.sort;

import java.util.*;

/**
 * 快速排序算法
 * 找到一个枢纽值 将数组划分为 左边的值都比该枢纽值小 右边的值都比该枢纽值大
 */
public class QuickSort_NoneRecursive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int len = scanner.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = scanner.nextInt();
            }
            QuickSort(arr,0,arr.length-1);
            StringBuilder res = new StringBuilder();
            for(int i:arr){
                res.append(i).append(" ");
            }
            System.out.println(res.toString().trim());
        }
    }


    private static void QuickSort(int[] a, int start, int end) {
        Stack<Integer> stack = new Stack<>();
        if (start < end) {
            //先放end后放start
            stack.push(end);
            stack.push(start);
            while (!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int index = partition(a, l, r);
                if (l < index - 1) {
                    stack.push(index - 1);
                    stack.push(l);
                }
                if (r > index + 1) {
                    stack.push(r);
                    stack.push(index + 1);
                }
            }
        }
    }

    private static int partition(int[] a, int start, int end) {
        int pivot = a[start];
        while (start < end) {
            while (start < end && a[end] >= pivot)
                end--;
            if(start<end) {
                swap(a, start, end);
            }
            while (start < end && a[start] <= pivot)
                start++;
            if(start<end) {
                swap(a, start, end);
            }
        }
        return start;
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
