package com.study.leecode;

public class leecode_892三锥形的表面积 {

    public int surfaceArea(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        int hide = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] > 0) {
                    count += grid[i][j];
                    hide += grid[i][j] - 1;
                }

                if (i > 0) {
                    hide += Math.min(grid[i - 1][j], grid[i][j]);
                }

                if (j > 0) {
                    hide += Math.min(grid[i][j - 1], grid[i][j]);
                }
            }
        }
        return count * 6 - hide * 2;
    }
}
