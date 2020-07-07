package com.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leecode_51八皇后问题 {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];
        //初始化n*n的棋盘
        init(board);
        dfs(res,board,0);
        return res;
    }

    private static void init(char[][] board) {
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }

    }

    private static void dfs(List<List<String>> res, char[][] board, int rowIndex) {

        //如果递归到最后一行 表示该方案可行 加入结果
        if (rowIndex == board.length) {
            res.add(generate(board));
            return;
        }

        for (int colIndex = 0; colIndex < board.length; colIndex++) {
            //对于棋盘的每一行 看每一列是否符合要求
            if (isValid(board, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                dfs(res, board, rowIndex + 1);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int rowIndex, int colIndex) {

        //如果同一列上有Q返回false
        for (int i = 0; i < rowIndex; i++) {
            if (board[i][colIndex] == 'Q') return false;
        }

        //如果左上方有Q 返回false
        for (int i = rowIndex - 1, j = colIndex - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        //如果右上方有Q 返回false
        for (int i = rowIndex - 1, j = colIndex + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    //将一个二维的字符数组转换为字符串
    private static List<String> generate(char[][] board) {
        List<String> list = new ArrayList<>();
        for(char[] row:board){
            StringBuilder builder = new StringBuilder();
            for(char c:row){
                builder.append(c);
            }
            list.add(builder.toString());
        }
        return list;
    }

    public static void main(String[] args) {
        List<List<String>> res = solveNQueens(8);
        for(List<String> list:res){
            for(String s:list){
                System.out.println(s);
            }
            System.out.println("--------");
        }
    }
}
