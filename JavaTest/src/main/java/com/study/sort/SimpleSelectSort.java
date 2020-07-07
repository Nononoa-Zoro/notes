package com.study.sort;

import java.util.Arrays;

/**
 * 简单选择排序
 *
 * 算法复杂度
 * 无论最好还是最差的情况下 简单选择排序的算法复杂度都是o(n^2)
 *
 * 应用：求最小交换次数
 *
 * 简单选择排序是不稳定的排序算法
 *
 */
public class SimpleSelectSort {

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{
                5, 2, 1, 4, 3
        };
        Integer[] SortedList = selectSort(arr);
        System.out.println(Arrays.toString(SortedList));

    }

    private static Integer[] selectSort(Integer[] arr) {

        int length = arr.length;

        for (int i = 0; i < length; i++) {
            //假设每一次循环的第一个总是最小的
            int min = i;
            for (int j = i + 1; j < length; j++) {
                //如果这次迭代中出现比min小的元素则交换
                if (arr[j] < arr[min])
                    min = j;
            }
            //最终一轮迭代完成之后如果min!=i则交换
            if (i != min) {
                swap(arr, i, min);
            }
        }
        return arr;

    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
