package com.study.zuoshen;

public class 从数组中能否找到多个数的和等于目标值 {


    public static void main(String[] args) {
        int[] arr = {5, 9, 7, 8};
        boolean res = isSum(arr, 0, 0, 15);
        System.out.println(res);
    }


    //当前位置到数组末尾能否组合出sum
    public static boolean isSum(int[] arr, int i, int sum, int target) {
        if (i == arr.length) {
            return sum == target;
        }
        return isSum(arr, i + 1, sum, target) || isSum(arr, i + 1,sum + arr[i], target);
    }
}
