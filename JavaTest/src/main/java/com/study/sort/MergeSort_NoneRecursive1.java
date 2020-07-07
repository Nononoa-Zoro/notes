package com.study.sort;

/**
 * 归并-非递归
 */
public class MergeSort_NoneRecursive1 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 8, 4, 9};
        mergeSort(arr);
        StringBuilder res = new StringBuilder();
        for (int i : arr) {
            res.append(i).append(" ");
        }
        System.out.println(res.toString().trim());
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int width = 1;
        while (width < arr.length) {
            mergePre(arr, width);
            width *= 2;
        }
    }

    private static void mergePre(int[] arr, int width) {
        int start = 0;
        while (start + 2 * width - 1 < arr.length) {
            int mid = start + width - 1;
            int right = start + 2 * width - 1;
            merge(arr, start, mid, right);
            start = start + 2 * width;
        }
        if (start + width - 1 < arr.length)
            merge(arr, start, start + width - 1, arr.length - 1);
    }

    private static void merge(int[] arr, int left, int mid, int right) {

        int[] leftArr = new int[mid - left];
        int[] rightArr = new int[right - left + 1];
        if (mid - left >= 0) System.arraycopy(arr, left, leftArr, 0, mid - left);
        if (right - mid + 1 >= 0) System.arraycopy(arr, mid, rightArr, 0, right - mid + 1);

        int i = 0, j = 0;
        int k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
                k++;
            } else {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }

        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        while (j > rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

}