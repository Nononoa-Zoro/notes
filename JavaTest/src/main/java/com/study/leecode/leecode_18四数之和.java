package com.study.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leecode_18四数之和 {

    public static void main(String[] args) {
        int[] arr = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> res = fourSum(arr, 0);
        System.out.println(res);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<>();

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int n = k + 1; n < len; n++) {
                        if (nums[i] + nums[j] + nums[k] + nums[n] == target) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[n]);
                            res.add(list);
                        }
                    }
                }
            }
        }
        return res;
    }


}
