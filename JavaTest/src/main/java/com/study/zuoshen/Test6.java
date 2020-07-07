package com.study.zuoshen;

/**
 * 螺旋输出一个矩阵
 *
 */
public class Test6 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        spiralOrderPrint(matrix);
    }

    private static void spiralOrderPrint(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a <= c && b <= d) {
            printEdge(matrix, a++, b++, c--, d--);
        }
    }

    private static void printEdge(int[][] matrix, int a, int b, int c, int d) {
        if (a == c) {
            for (int i = b; i < d; i++) {
                System.out.print(matrix[a][i] + " ");
            }
        } else if (b == d) {
            for (int i = a; i < c; i++) {
                System.out.print(matrix[i][b] + " ");
            }
        } else {
            int x = a;
            int y = b;
            while (y != d) {
                System.out.print(matrix[a][y++] + " ");
            }
            while (x != c) {
                System.out.print(matrix[x++][d] + " ");
            }
            while (y != a) {
                System.out.print(matrix[c][y--] + " ");
            }
            while (x != a) {
                System.out.print(matrix[x--][b] + " ");
            }
        }
    }


}
