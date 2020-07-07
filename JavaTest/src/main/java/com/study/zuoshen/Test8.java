package com.study.zuoshen;

/**
 * 打印对角线
 */
public class Test8 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        int a = 0, b = 0, c = 0, d = 0;
        boolean flag = false;
        int column = arr[0].length - 1;
        int row = arr.length - 1;
        while (a != row + 1) {
            print(arr, a, b, c, d, flag);
            a = b == column ? a +1 : a;
            b = b == column ? b  : b+1;
            d = c == row ? d + 1 : d;
            c = c == row ? c : c + 1;
            flag = !flag;
        }
    }

    private static void print(int[][] arr, int a, int b, int c, int d, boolean flag) {
        if (flag) {
            //右上到左下
            while (a != c + 1) {
                System.out.print(arr[a++][b--] + " ");
            }
        } else {
            while (c != a - 1) {
                System.out.print(arr[c--][d++] + " ");
            }
        }

    }
}
