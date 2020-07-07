package com.study.zuoshen;

import java.util.ArrayList;

public class Z字形打印字符串 {

    /**
     * @param s   字符串
     * @param num 行数
     * @return
     */
    public static String convert(String s, int num) {
        if (num < 2) return s;

        ArrayList<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            rows.add(new StringBuilder());
        }

        //如果遍历到第一行或者是最后一行都要反转
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == num - 1) {
                flag = -flag;
            }
            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder builder : rows) {
            res.append(builder);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String str ="LEETCODEISHIRING";
        String res = convert(str, 3);
        System.out.println(res.equals("LCIRETOESIIGEDHN"));
    }
}