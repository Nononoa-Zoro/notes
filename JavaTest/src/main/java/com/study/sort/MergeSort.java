package com.study.sort;

import java.util.Arrays;

/**
 * 归并排序的思想主要是对两个已经排序好的数组 划分到每个组只有一个元素的时候[1] [2] ==>[1,2]
 * <p>
 * 如何得到两个已经排序好的数组？--->一直递归划分知道数组元素只有两个
 * <p>
 * 通过合并为最终一个有序的数组
 * <p>
 * 归并排序是稳定的排序算法
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arrays = {8, 3, 2, 9, 7, 1, 5, 4};
        mergeSort(arrays, 0, arrays.length - 1);
        System.out.println(Arrays.toString(arrays));
    }

    /**
     * @param arr   待拆分数组
     * @param left  左端点
     * @param right 右端点
     *              3步：
     *              1.划分数组，排序左边
     *              2.排序右边
     *              3.合并两边
     */
    private static void mergeSort(int[] arr, int left, int right) {

        // 如果只有一个元素就不用进行排序了
        if (left < right) {
            int m = (left + right) / 2;
            // 左边不断进行拆分
            mergeSort(arr, left, m);
            // 右边不断进行拆分
            mergeSort(arr, m + 1, right);
            // 合并
            merge(arr, left, m + 1, right);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        // [1,2|3|,4,5] left=0,middle=2,right=4
        // [0 1 2 3 4]
        int[] leftArr = new int[middle - left];//左边数组大小m-l
        int[] rightArr = new int[right - middle + 1];//右边数组大小

        // left right是递归划分得到的两个数组
        // sourceArr sourceStart DesArr DesStart length
        System.arraycopy(arr, left, leftArr, 0, middle - left);
        System.arraycopy(arr, middle, rightArr, 0, right - middle + 1);

        // i j 是左右两个数组的指针 k初始化为最终合并后数组的指针
        int i = 0, j = 0;
        int k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // 如果合并完 left数组中还有剩余 就append在最终数组后面
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        // 如果合并完 right数组中还有剩余 就append在最终数组后面
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

}
