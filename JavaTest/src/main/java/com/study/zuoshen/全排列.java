package com.study.zuoshen;

public class 全排列 {

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c'};
        fullPermutation(arr, 0);
    }

    //index表示要用其后的字符对其进行替换的下标
    public static void fullPermutation(char[] arr, int start) {
        if (start == arr.length) {
            System.out.println(String.valueOf(arr));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            fullPermutation(arr, start + 1);
            swap(arr, start, i);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}



