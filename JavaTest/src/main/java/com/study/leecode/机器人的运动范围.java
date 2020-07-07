package com.study.leecode;
//https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

import java.util.LinkedList;
import java.util.Queue;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 机器人的运动范围 {

    int row, col, k;
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        this.row = m;
        this.col = n;
        this.k = k;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0);

    }

    //深度优先遍历
    public int dfs(int i, int j, int num) {
        //不满足条件的直接返回
        if (i < 0 || i >= row || j < 0 || j >= col || num > k || visited[i][j]) return 0;
        //将访问过的置为true
        visited[i][j] = true;
        //从当前位置向下和向右进行访问 这里体现了深度优先遍历的思想 是从一直访问当前位置的下面一行 直到结束才会访问右边
        return 1 + dfs(i + 1, j, cal(i + 1, j)) + dfs(i, j + 1, cal(i, j + 1));
    }

    //求解两个数的数位之和
    public int cal(int x, int y) {
        int res = 0;
        while (x != 0 || y != 0) {
            if (x != 0) {
                res += x % 10;
                x /= 10;//相当于右移一位
            }
            if (y != 0) {
                res += y % 10;
                y /= 10;//相当于右移一位
            }
        }
        return res;
    }

    //广度优先遍历
    public int bfs(int row, int col, int k) {
        boolean[][] visisted = new boolean[row][col];
        int res = 0;
        //队列中存的是 行 列 和当前的各位数之和
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int i = arr[0], j = arr[1], num = arr[2];
            if (i < 0 || i >= row || j < 0 || j >= col || visisted[i][j] || num > k) continue;

            visisted[i][j] = true;
            res++;
            queue.add(new int[]{i + 1, j, cal(i + 1, j)});
            queue.add(new int[]{i, j + 1, cal(i, j + 1)});
        }
        return res;
    }
}
