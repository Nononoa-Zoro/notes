package com.study.leecode;

/**
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * n=3 k=4 输出 "231"
 */
public class leecode_60第K个排列 {

    public String getPermutation(int n, int k) {
        //total是n的阶乘
        int total = 1;
        for (int i = 2; i <= n; i++) total *= i;
        //异常处理 如果要找的数比阶乘还大或者小于1 返回空字符串
        if (k > total || k < 1) return "";

        //初始化数组 数组元素为1-n
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;

        getPermutation(arr, 0, k, total / n);

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++)
            res.append(arr[i]);
        return res.toString();
    }

    /**
     * @param arr     数组1-n
     * @param index   开始索引
     * @param k       要找的第k个元素
     * @param maxOnce 每组中最大的元素个数
     */
    public void getPermutation(int[] arr, int index, int k, int maxOnce) {
        if (k == 1) return;
        int i = index;
        //这一步是在找第K组内的偏移量offset K就是offset
        //最终的i就是第K组的首位数字
        for (; i < arr.length; i++) {
            if (k - maxOnce < 1) break;
            k -= maxOnce;
        }

        int tmp = arr[i];
        for (int j = i - 1; j >= index; j--) {
            arr[j + 1] = arr[j];
        }
        arr[index] = tmp;

        getPermutation(arr, index + 1, k, maxOnce / (arr.length - 1 - index));
    }
}