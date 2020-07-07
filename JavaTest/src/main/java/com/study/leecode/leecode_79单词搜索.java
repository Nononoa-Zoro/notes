package com.study.leecode;

public class leecode_79单词搜索 {

    private boolean[][] visited;

    //        x-1,y
    // x,y-1  x,y    x,y+1
    //        x+1,y
    private int[][] direction = {
            {-1, 0},
            {0, -1},
            {1, 0},
            {0, 1}
    };

    private int row;
    private int column;
    private String word;
    private char[][] board;


    public boolean exist(char[][] board, String word) {
        this.row = board.length;
        if (row == 0) return false;
        this.column = board[0].length;
        if (column == 0) return false;

        this.visited = new boolean[row][column];

        this.word = word;
        this.board = board;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 从(i,j)位置出发能不能找到一个单词
     * @param i 行索引
     * @param j 列索引
     * @param start 单词的索引
     * @return true:能找到 false:不能
     */
    private boolean dfs(int i, int j, int start) {
        //base case 如果当前位置是字符串的最后一个位置
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }

        if (board[i][j] == word.charAt(start)) {
            visited[i][j] = true;
            //当前字符如果匹配 有四个选择
            for (int k = 0; k < direction.length; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                //如果新计算出的坐标在边界内 且没有被访问过
                if (inArea(newX, newY) && !visited[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            //上面for循环没有return 说明(i,j)这个点开始不能组合成word 所以别访问(i,j)
            visited[i][j]=false;
        }
        return false;
    }

    /**
     * 判断一个点是否在二维数组区域内
     * @param i 行索引
     * @param j 列索引
     * @return {true/false}
     */
    private boolean inArea(int i, int j) {
        return i >= 0 && i < row && j >= 0 && j < column;
    }

}
