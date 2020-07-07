package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class 两数之和 {

    static List<List<Integer>> res = new ArrayList<>();

    //方法一 左右指针
    public static List<List<Integer>> solve(int[] arr, int target) {

        Arrays.sort(arr);

        int i = 0, j = arr.length - 1;

        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                res.add(Arrays.asList(arr[i],arr[j]));
                //跳过重复元素
                while (arr[i]==arr[i+1]){
                    i++;
                }
                while (arr[j-1]==arr[j]){
                    j--;
                }
                i++;
                j--;
            }
        }
        return res;

    }


    public static void main(String[] args) {
        int[] arr = {1,2,2,3,5,6,7};

        List<List<Integer>> lists = solve(arr, 8);

        for(List<Integer> list:lists){
            for(Integer i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }

    }
}
