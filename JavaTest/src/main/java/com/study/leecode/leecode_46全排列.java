package com.study.leecode;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class leecode_46全排列 {

    public static void main(String[] args) {
        int[] arr ={1,2,3};
        List<List<Integer>> res = permute(arr);
        System.out.println(res);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        perm(nums, 0, nums.length - 1, res);
        return res;
    }

    public static void perm(int[] arr, int start, int end, List<List<Integer>> res) {
        if (start == end) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : arr) {
                list.add(i);
            }
            res.add(list);
            return;
        }

        for (int i = start; i <= end; i++) {
            swap(arr, i, start);
            perm(arr, start + 1, end, res);
            swap(arr, i, start);
        }
    }

    public static void perm1(int[] arr,int start,int end,List<List<Integer>> res){
        if(start==end){
            ArrayList<Integer> list = new ArrayList<>();
            for(int i:arr){
                list.add(i);
            }
            res.add(list);
        }

        for(int i=start;i<=end;i++){
            swap(arr,i,end);
            perm1(arr,i+1,end,res);
            swap(arr,i,end);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}