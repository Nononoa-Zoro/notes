package com.study.leecode;

/**
 * 滑动窗口算法可以用以解决数组/字符串的子元素问题
 * 它可以将嵌套的循环问题，转换为单循环问题，降低时间复杂度。
 */
public class 滑动窗口_长度为k的连续子数组的最大和 {


    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int res = solve(arr, 2);
        System.out.println(res);
    }


    public static int solve(int[] arr, int k) {
        int sum = 0;
        //初始化 将第一个窗口的和作为最大值
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int max = sum;
        //此后我们只关注窗口中移除的元素和进来的元素
        for (int i = k; i < arr.length; i++) {
            sum += arr[i] - arr[i - k];
            max = Math.max(max, sum);
        }
        return max;
    }
}
