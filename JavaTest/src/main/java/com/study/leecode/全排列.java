package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 全排列 {

    private static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        perm(arr, 0, 2);
        Collections.sort(res);
        for (String str : res) {
            System.out.println(str);
        }
    }

    public static void perm(int[] arr, int start, int end) {
        //base case
        if (start == end) {
            StringBuilder builder = new StringBuilder();
            for (int i : arr) {
                builder.append(i);
            }
            res.add(builder.toString());
        }

        for (int i = start; i <= end; i++) {
            swap(arr, start, i);
            perm(arr, start + 1, end);
            swap(arr, start, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
