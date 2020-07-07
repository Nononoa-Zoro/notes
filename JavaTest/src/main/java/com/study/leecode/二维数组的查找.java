package com.study.leecode;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 * 思路：
 * 用二维矩阵的左下角或者右上角作为参考arr[i][j]
 * 如果当前值小于arr[i][j] 说明arr[i][j]所在的列都是大于target 因此 j--
 * 如果当前值大于arr[i][j] 说明arr[i][j]所在的行都是小于target 因此 i++
 */

public class 二维数组的查找 {

    public static void main(String[] args) {
        int[][] arr = {
                {1,2,3,4,5},
                {6,7,8,9,10}
        };
        boolean res = find(arr, 7);
        boolean res1 = find(arr, 11);
        System.out.println(res);
        System.out.println(res1);

    }
    public static boolean find(int[][] arr, int target) {
        int row = arr.length;
        int col = arr[0].length;

        int i = 0, j = col - 1;
        while (i < row && j >= 0) {
            if (target == arr[i][j]) {
                return true;
            } else if (target < arr[i][j]) {
                j--;
            } else {
                i++;
            }
        }
        return false;

    }
}
