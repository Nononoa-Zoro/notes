package com.study.leecode;

/**
 * 求矩形围成的最大面积 双指针
 */
public class leecode_11 {

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = maxArea1(arr);
        System.out.println(res);
    }

    //贪心算法 初始化指针分别从左右两边开始 i=0 j=len-1
    public static int maxArea1(int[] arr) {
        int ans = 0;
        int len = arr.length;
        int i = 0, j = len - 1;
        while (i != j) {
            //只要i!=j
            int width = j - i;
            int height = Math.min(arr[i], arr[j]);
            int area = width * height;
            ans = Math.max(ans, area);
            //在每次循环中 选择最短的一端向中间移动
            if (arr[i] <= arr[j]) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }


}
