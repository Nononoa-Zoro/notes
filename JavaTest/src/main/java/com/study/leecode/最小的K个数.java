package com.study.leecode;

import java.util.Arrays;

//https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
public class 最小的K个数 {

    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        System.arraycopy(arr,0,res,0,k);

        return res;
    }
}
