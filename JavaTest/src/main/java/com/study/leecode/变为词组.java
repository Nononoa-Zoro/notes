package com.study.leecode;

import java.util.*;

public class 变为词组 {

    public static void main(String[] args) {
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(arr);
        for(List<String> list:lists){
            for(String str:list){
                System.out.print(str+" ");
            }
            System.out.println();
        }
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] word = new int[26];
            for (int i = 0; i < str.length(); i++) {
                ++word[str.charAt(i) - 'a'];
            }
            StringBuilder builder = new StringBuilder();
            for (int i : word) {
                builder.append(i);
            }
            String key = builder.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : entries) {
            res.add(entry.getValue());
        }
        return res;
    }
}
