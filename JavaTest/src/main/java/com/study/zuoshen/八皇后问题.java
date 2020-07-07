package com.study.zuoshen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 八皇后问题 {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        init(board);
        helper(res,board,0);
        return res;
    }

    private static void init(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

    }

    private static void helper(List<List<String>> res, char[][] board, int rowIndex) {
        if (rowIndex == board.length) {
            res.add(generate(board));
            return;
        }

        for (int colIndex = 0; colIndex < board.length; colIndex++) {
            if (isValid(board, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                helper(res, board, rowIndex + 1);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int rowIndex, int colIndex) {
        for (int i = 0; i < rowIndex; i++) {
            if (board[i][colIndex] == 'Q') return false;
        }

        for (int i = rowIndex - 1, j = colIndex - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        for (int i = rowIndex - 1, j = colIndex + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;

    }

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
        List<List<String>> res = solveNQueens(4);
        System.out.println(res);
    }
}
