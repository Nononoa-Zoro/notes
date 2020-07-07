package com.study.zuoshen;

import java.util.Arrays;

/**
 * 快速排序
 *
 * 将元数组通过privot划分为三个部分
 * <privot =privot >privot
 */
public class Test3 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 4, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //随机选择一个数做划分
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0] - 1);
            quickSort(arr, p[1] + 1, right);
        }
    }

    //每次都拿最后一个元素做划分

    /**
     * @param arr 待划分数组
     * @param left 数组左边的起点
     * @param right 数组右边的结束点
     * @return 中间等于privot的起点和终点位置
     */
    private static int[] partition(int[] arr, int left, int right) {
        int less = left - 1;//小于划分元素的有边界
        int more = right;//大于划分元素的左边界
        int cur = left;
        while (cur < more) {
            int privot = arr[right];
            if (arr[cur] < privot) {
                swap(arr, ++less, cur++);
            } else if (arr[left] > privot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
