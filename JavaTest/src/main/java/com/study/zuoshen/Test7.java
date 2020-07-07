package com.study.zuoshen;

/**
 * 将一个矩阵顺时针旋转90度
 */
public class Test7 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("旋转前的矩阵");
        printMatrix(matrix);
        System.out.println("旋转后的矩阵");
        rotate(matrix);
        printMatrix(matrix);
    }
    private static void rotate(int[][] matrix){
        int a=0;
        int b=0;
        int c=matrix.length-1;
        int d=matrix[0].length-1;
        while (a<c){
            rotateEdge(matrix,a++,b++,c--,d--);
        }
    }
    private static  void rotateEdge(int[][] matrix,int a,int b ,int c ,int d){
        int times = c-a;
        int tmp;
        for(int i=0;i!=times;i++){
            tmp=matrix[a][b+i];
            matrix[a][b+i]=matrix[c-i][b];
            matrix[c-i][b]=matrix[c][d-i];
            matrix[c][d-i]=matrix[a+i][d];
            matrix[a+i][d]=tmp;
        }
    }

    private static void printMatrix(int[][] matrix){
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j]+" ");
            }
            System.out.println();
        }
    }
}
