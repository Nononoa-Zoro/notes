package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

public class leecode_1324竖直打印单词 {

    public static void main(String[] args) {
        List<String> list = printVertically("TO BE OR NOT TO BE");
        for (String s : list) {
            System.out.println(s+"\t"+s.length());
        }
    }

    public static List<String> printVertically(String s) {
        String[] arr = s.split(" ");
        List<String> res = new ArrayList<>();
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLen = Math.max(arr[i].length(), maxLen);
        }
        for (int i = 0; i < maxLen; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                int len = arr[j].length();
                if (i > len - 1) {
                    builder.append(" ");
                } else {
                    char c = arr[j].charAt(i);
                    builder.append(c);
                }
            }
            res.add(builder.toString());
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            list.add(trimEnd(res.get(i)));
        }
        return list;

    }

    public static String trimEnd(String s) {
        int len = s.length();
        int end = len - 1;
        while (s.charAt(end) == ' ' && end >= 0) {
            end--;
        }
        return s.substring(0, end + 1);
    }
}
