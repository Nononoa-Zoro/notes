package com.study.leecode;

import java.util.ArrayList;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 4 时，排列如下：
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class Test5 {
    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;
        ArrayList<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            StringBuilder builder = new StringBuilder();
            rows.add(builder);
        }
        //骚操作
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            //attention
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String res = convert("LEETCODEISHIRING", 3);
        System.out.println(res);
    }


}
