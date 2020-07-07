package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

public class 面试题57和为s的连续正数序列 {
    public static void main(String[] args) {
        int[][] res = findContinuousSequence(7);
        System.out.println(res);
    }
    public static int[][] findContinuousSequence(int target) {
        int i = 1;
        int j = 1;
        int sum = 0;
        List<int[]> res = new ArrayList<>();

        while (i<=target/2){
            if(sum<target){
                sum+=j;
                j++;
            }else if(sum>target){
                sum-=i;
                i++;
            }else{
                int[] arr = new int[j-i];
                for(int k=i;k<j;k++){
                    arr[k-i]=k;
                }
                res.add(arr);
                sum-=i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }


}
