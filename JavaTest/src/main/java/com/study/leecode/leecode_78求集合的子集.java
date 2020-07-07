package com.study.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leecode_78求集合的子集 {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> res = subsets(arr);
        for(List<Integer> list:res){
            for(Integer i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        backTrack(nums,0, res,new Stack<>());
        return res;
    }

    public static void backTrack(int[] nums, int start, List<List<Integer>> res, Stack<Integer> stack){
        res.add(new ArrayList<>(stack));
        for(int i=start;i<nums.length;i++){
            stack.push(nums[i]);
            backTrack(nums,i+1,res,stack);
            stack.pop();
        }
    }
}
