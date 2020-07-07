package com.study.leecode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 */
public class Test3 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0,j=0,k=0;
        int len1=nums1.length;
        int len2=nums2.length;
        int num = len1 + len2;
        int[] res = new int[num];
        while (i!=len1&&j!=len2){
            int x=nums1[i];
            int y=nums2[j];
            if(x<y){
                res[k]=x;
                k++;
                i++;
            }else{
                res[k]=y;
                k++;
                j++;
            }
        }
        if(i<len1){
            for (int l = i; l < len1; l++) {
                res[k]=nums1[l];
                k++;
            }
        }
        if(j<len2){
            for (int l = j; l < len2; l++) {
                res[k]=nums2[l];
                k++;
            }
        }

        if((num&1)!=0){
            int index = num / 2;
            return res[index];
        }else{
            int index = num/2;
            return (double) (res[index]+res[index-1])/2;
        }
    }

    public static void main(String[] args) {
        int[] nums1={1,3};
        int[] nums2={3};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
}
