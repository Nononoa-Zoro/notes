package com.study.zuoshen;

import java.util.LinkedList;
import java.util.Queue;

//leecode 200
public class 计算岛的数量 {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0}
        };
        int[][] arr1 = {
                {1, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0}
        };

        System.out.println(countIslands(arr));
        System.out.println(numIslands(arr1));
    }


    public static int countIslands(int[][] arr) {

        if (arr == null || arr[0] == null) return 0;

        int row = arr.length;
        int col = arr[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 1) {
                    res++;
                    infect(arr, i, j, row, col);
                }
            }
        }

        return res;
    }

    //深度优先搜索 dfs
    private static void infect(int[][] arr, int i, int j, int row, int col) {

        if (i < 0 || i >= row || j < 0 || j >= col || arr[i][j] != 1) return;
        //所有和1相关的1都被传染成2
        arr[i][j] = 2;
        //上
        infect(arr, i - 1, j, row, col);
        //下
        infect(arr, i + 1, j, row, col);
        //左
        infect(arr, i, j - 1, row, col);
        //右
        infect(arr, i, j + 1, row, col);
    }


    //广度优先搜索 bfs
    public static int numIslands(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        int num_islands = 0;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {

                if (grid[r][c] == 1) {
                    ++num_islands;
                    grid[r][c] = 2;
                    Queue<Integer> queue = new LinkedList<>();
                    //队列记录当前点的id 所谓id就是当前元素之前有多少个元素
                    queue.add(r * col + c);
                    while (!queue.isEmpty()) {
                        int id = queue.poll();
                        //从队列中拿出一个元素计算所在的行和列
                        int i = id / col;
                        int j = id % col;
                        //如果左边也是一个岛 则加入队列 队列中存的都是当前元素前面有多少个元素

                        //上
                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                            queue.add((i - 1) * col + j);
                            //将访问过的元素打标
                            grid[i - 1][j] = 2;
                        }

                        //下
                        if (i + 1 < row && grid[i + 1][j] == 1) {
                            queue.add((i + 1) * col + j);
                            grid[i + 1][j] = 2;
                        }

                        //左
                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                            queue.add(i * col + j - 1);
                            grid[i][j - 1] = 2;
                        }
                        
                        //右
                        if (j + 1 < col && grid[i][j + 1] == 1) {
                            queue.add(i * col + j + 1);
                            grid[i][j + 1] = 2;
                        }

                    }
                }
            }
        }

        return num_islands;
    }
}



