package com.study.sort;

import java.util.Arrays;

public class 快排算法 {

    public static void main(String[] args) {
        int[] arr = new int[]{9,10,2,5,4};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr,int left,int right) {
        if (left<right){
            int privot = partition(arr,left,right);
            quickSort(arr,left,privot-1);
            quickSort(arr,privot+1,right);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int privot = arr[low];
        while (low < high) {
            while (low < high && arr[high] > privot) high--;
            swap(arr, low, high);
            while (low < high && arr[low] < privot) low++;
            swap(arr, low, high);
        }
        return low;

    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
