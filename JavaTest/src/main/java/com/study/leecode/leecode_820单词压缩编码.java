package com.study.leecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class leecode_820单词压缩编码 {
    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        int res = minimumLengthEncoding(words);
        System.out.println(res);
    }

    public static int minimumLengthEncoding(String[] words) {

        Set<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }

        int res =    0;
        for (String s : set) {
            res += s.length() + 1;
        }

        return res;

    }
}
