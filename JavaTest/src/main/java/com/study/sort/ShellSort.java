package com.study.sort;

import java.util.Arrays;

/**
 * 希尔排序是通过一个增量 抽取出一个子序列
 * 然后对这个子序列进行直接插入排序
 * 当增量为1时 也就是原序列有序
 */
public class ShellSort {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                5, 2, 1, 4, 3
        };
        Integer[] sortedArray = shellsort(arr);
        System.out.println(Arrays.toString(sortedArray));
    }

    private static Integer[] shellsort(Integer[] arr) {
        int i, j, t;
        int increment = arr.length;
        while (increment>1){
            increment = increment / 3 + 1;
            for (i = increment ; i < arr.length; i++) {
                //如果当前位置的元素比之前的元素值小，则找到一个合适的位置插入
                if (arr[i] < arr[i - increment]) {
                    t = arr[i];
                    for (j = i - increment; j >= 0 && arr[j] > t; j -= increment) arr[j + increment] = arr[j];
                    arr[j+increment] = t;
                }
            }
        }
        return arr;
    }
}
