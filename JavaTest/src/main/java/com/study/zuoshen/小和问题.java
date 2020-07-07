package com.study.zuoshen;

/**
 * 小和问题
 *
 * 在一个数组中，每一个左边比当前数小的数累加起来，叫做这个数的小和。求一个数组的小和
 * 例子[1,3,4,2,5]
 * 1左边比1小的数，没有
 * 3左边比3小的数1
 * 4左边比4小的数1，3
 * 2左边比2小的数1
 * 5左边比5小的数1，3，4，2
 * 所以小和为1+1+3+1+1+3+4+3=16
 *
 */
public class 小和问题 {
    private static int smallSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return mergeSort(arr, L, mid) + mergeSort(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int k = 0;
        int res = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            //如果P1小于P2，当前小和就是p1处的值乘从p2开始后面所有的元素个数，因为比p2小，就比p2后面所有的都小。
            res += arr[i] < arr[j] ? (right - j + 1) * arr[i] : 0;
            help[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            help[k++] = arr[i++];
        }
        while (j <= right) {
            help[k++] = arr[j++];
        }
        for (k = 0; k < help.length; k++) {
            arr[left + k] = help[k];
        }
//        System.arraycopy(help,0,arr,left,help.length);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        int res = smallSort(arr);
        System.out.println(res);
    }
}