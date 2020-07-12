package com.study.sort;

public class 二分查找 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 10};
        int search = binary_search(arr, 0, arr.length - 1, 3);
        int search1 = binary_search_recursive(arr, 0, arr.length - 1, 3);
        System.out.println(arr[search]);
        System.out.println(arr[search1]);

    }

    public static int binary_search(int[] arr, int left, int right, int target) {
        //注意这里是小于等于 因为最后左右指针可能指向同一个位置
        while (left <= right) {
            int mid = (left + right) >>> 1;//无符号右移，不管是有符号还是无符号在右移的过程中都是高位补0。有符号右移会根据符号位来补齐高位
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    //递归版本
    public static int binary_search_recursive(int[] arr, int left, int right, int target) {

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                return binary_search_recursive(arr, left, right - 1, target);
            } else {
                return binary_search_recursive(arr, left + 1, right, target);
            }
        }
        return -1;
    }


}
