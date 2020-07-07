package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = solve(arr, 0);

        for(List<Integer> list:lists){
            for(Integer i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> solve(int[] arr, int target) {
        Arrays.sort(arr);

        List<List<Integer>> res = new ArrayList<>();

        int len = arr.length;

        for (int k = 0; k < len; k++) {

            if (arr[k] > target) break;
            if (k > 0 && arr[k] == arr[k - 1]) continue;

            int i = k + 1, j = arr.length - 1;

            while (i < j) {
                int sum = arr[i] + arr[j];
                if (sum == target - arr[k]) {
                    res.add(new ArrayList<>(Arrays.asList(arr[k], arr[i], arr[j])));
                    while (i < j && arr[i] == arr[i + 1]) i++;
                    while (i < j && arr[j - 1] == arr[j]) j--;
                    i++;
                    j--;
                } else if (sum < target - arr[k]) {
                    while (i < j && arr[i] == arr[i + 1]) i++;
                    i++;
                } else {
                    while (i < j && arr[j - 1] == arr[j]) j--;
                    j--;
                }
            }
        }
        return res;
    }
}
