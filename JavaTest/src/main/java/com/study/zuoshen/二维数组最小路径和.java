package com.study.zuoshen;

/**
 * 给定一个二维数组，所有的元素都是正数
 * 规则：只能向下或者向右走
 * 求从左上角到右下角的最短路径和
 */
public class 二维数组最小路径和 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        int res = walk(matrix, 0, 0);
        System.out.println(res);
    }

    //从(i,j)位置走到右下角的最短路径是多少
    public static int walk(int[][] matrix, int i, int j) {
        //base case 走到了右下角
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }

        //走到了最后一行
        if (i == matrix.length - 1) {
            return walk(matrix, i, j + 1) + matrix[i][j];
        }

        //走到了最后一列
        if (j == matrix[0].length - 1) {
            return walk(matrix, i + 1, j) + matrix[i][j];
        }

        //获得当前位置走右边的最小值
        int right = walk(matrix, i, j + 1);
        //获得当前位置走下边的最小值
        int down = walk(matrix, i + 1, j);
        //取二者的最小值作为一个普通节点的到达右下角的最小路径
        return matrix[i][j] + Math.min(right, down);
    }
}
