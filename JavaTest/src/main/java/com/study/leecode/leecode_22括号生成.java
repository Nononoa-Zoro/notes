package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法的思想是 搜索解空间
 * 从树状解空间的根节点开始往下搜索
 * 如果不满足条件就返回上一级 选择另外一个分支继续搜索
 * 类似树的DFS遍历
 */
public class leecode_22括号生成 {

    public static void main(String[] args) {
        List<String> ans = new ArrayList<>();
        dfs(ans, "", 0, 0, 3);
        for (String str : ans) {
            System.out.println(str);
        }
    }

    /**
     * @param ans   生成的所有括号
     * @param cur   当前字符串
     * @param left  当前左括号的个数
     * @param right 当前右括号的个数
     * @param n     总共需要的括号的个数
     */
    public static void dfs(List<String> ans, String cur, int left, int right, int n) {
        //结束条件是 当前生成的字符串长度==括号的对数*2
        if (cur.length() == n * 2) {
            ans.add(cur);
            return;
        }

        //如果左括号的个数<括号的对数 就可以加(
        if (left < n) dfs(ans, cur + "(", left + 1, right, n);
        //如果右括号的个数<左括号的个数就加)
        if (right < left) dfs(ans, cur + ")", left, right + 1, n);

    }


}

