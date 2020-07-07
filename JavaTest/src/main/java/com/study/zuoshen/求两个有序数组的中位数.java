package com.study.zuoshen;

public class 求两个有序数组的中位数 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int num = len1 + len2;
        int[] res = new int[num];
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }
        while (i < len1) {
            res[k++] = nums1[i++];
        }
        while (j < len2) {
            res[k++] = nums2[j++];
        }

        if ((num & 1) != 0) {
            //说明只有奇数个元素
            int index = num / 2;
            return res[index];
        } else {
            //偶数个元素
            int m = (res[num / 2] + res[num / 2 - 1]) / 2;
            return m;
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }
}
