package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

public class leecode_17电话号码的组合 {

    public static String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    public static void main(String[] args) {
        List<String> list = letterCombinations("23", new ArrayList<>());
        System.out.println(list);
    }

    public static List<String> letterCombinations(String digits, List<String> res) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        iterStr(digits, "", 0, res);
        return res;
    }

    /**
     * @param str    输入的字符串 如："23"
     * @param letter 拼接的字符串
     * @param index  遍历的索引位置
     */
    public static void iterStr(String str, String letter, int index, List<String> res) {

        //base case
        if (index == str.length()) {
            res.add(letter);
            return;
        }

        char c = str.charAt(index);

        int pos = c - '0';

        String map_string = letter_map[pos];

        //当前位置有几种选择 假如输入的是2 对应abc 有三种选择 相当于这里有三个分支
        for (int i = 0; i < map_string.length(); i++) {
            iterStr(str, letter + map_string.charAt(i), index + 1, res);
        }


    }

}
