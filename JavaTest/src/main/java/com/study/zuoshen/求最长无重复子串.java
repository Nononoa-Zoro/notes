package com.study.zuoshen;

import java.util.HashMap;

public class 求最长无重复子串 {
    
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for(int start=0,end=0;end<len;end++){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                start= Math.max(map.get(c),start);
            }
            ans= Math.max(end-start+1,ans);
            map.put(s.charAt(end),end+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
