package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法
 * 输入a1b1
 * 输出将输入字符串中的字母按照大小写全排列之后输出
 * 输出  a1b1 A1b1 a1B1 A1B1
 */
public class leecode_784字母大小写全排列 {

    public static void main(String[] args) {
        List<String> res = letterCasePermutation("a1b2");
        System.out.println(res);
    }

    public static List<String> letterCasePermutation(String S) {
        int len = S.length();
        List<String> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        char[] charArray = new char[len];
        dfs(S, 0, len, charArray, res);
        return res;
    }

    //a1b2
    private static void backTrack(String S, int start, int len, char[] charArray, List<String> res) {
        if (start == len) {
            res.add(new String(charArray));
            return;
        }

        charArray[start] = S.charAt(start);
        backTrack(S, start + 1, len, charArray, res);

        // 如果是字符，就可以派生出一个新分支
        if (Character.isLetter(S.charAt(start))) {
            // 这一步直接修改，相当于回溯
            //ascall码中 "a" 与 "A" 想差32 大小写转换可以用异或表示
            charArray[start] = (char) (S.charAt(start) ^ (1 << 5));
            backTrack(S, start + 1, len, charArray, res);
        }
    }

    //dfs过程中用char[] 存储路径 List<String>存储结果
    private static void dfs(String str, int start, int len, char[] charArray, List<String> res) {
        //base case
        if (start == len) {
            res.add(new String(charArray));
            return;
        }

        char cur_char = str.charAt(start);

        charArray[start] = cur_char;

        dfs(str, start + 1, len, charArray, res);

        //如果当前字符是字母则有分支
        if (Character.isLetter(cur_char)) {
            charArray[start] = Character.isUpperCase(cur_char) ? Character.toLowerCase(cur_char) : Character.toUpperCase(cur_char);
            dfs(str, start + 1, len, charArray, res);
        }
    }
}

