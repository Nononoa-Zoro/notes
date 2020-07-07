package com.study.leecode;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * leecode 2
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 滑动窗口
 */
public class Test1 {

    public static int lengthOfLongestSubstring(String s) {
        int n =  s.length();
        int ans =0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int start=0,end=0;end<n;end++){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                start= Math.max(map.get(c),start);
            }
            ans= Math.max(end-start+1,ans);
            map.put(c,end+1);
        }
        return ans;
    }


    public static void main(String[] args) {
        int a = lengthOfLongestSubstring("abdsca");
        System.out.println(a);
    }
}
