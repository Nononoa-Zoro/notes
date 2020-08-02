package com.study.leecode;

import java.util.HashMap;
import java.util.Map;

public class leecode_03无重复字符的最长子串 {
    public static void main(String[] args) {
        int res = lengthOfLongestSubstring("abcabcbb");
        System.out.println(res);
    }

    /*"abcabcbb"*/
    public static int lengthOfLongestSubstring(String s) {
//        int n = s.length();
//        int ans = 0;
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int start = 0, end = 0; end < n; end++) {
//            char c = s.charAt(end);
//            // 如果当前字符出现过 那么找到这个字符最后一次出现的位置
//            if (map.containsKey(c)) {
//                start = Math.max(map.get(c), start);
//            }
//            // 当前的不重复字符串长度就是end-start+1
//            ans = Math.max(end - start + 1, ans);
//            map.put(s.charAt(end), end + 1);
//        }
//        return ans;

        int len = s.length();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int start = 0, end = 0; end < len; end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)));
            }
            ans = Math.max(end - start + 1, ans);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
