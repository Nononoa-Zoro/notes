package com.study.leecode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class leecode_1160拼写单词 {

    public static void main(String[] args) {
        String[] words = {"cat", "bt", "hat", "tree"};

        String chars = "atach";

        System.out.println(countCharacters(words, chars));


    }

    public static int countCharacters(String[] words, String chars) {
        int[] c = new int[26];
        for (char cc : chars.toCharArray()) {
            c[cc - 'a'] += 1;
        }
        int res = 0;

        a:
        for (String s : words) {
            int[] w = new int[26];
            for(int i=0;i<s.length();i++){
                w[s.charAt(i)-'a']+=1;
            }
            for(int i=0;i<26;i++){
                if(w[i]>c[i])continue a;
            }
            res+=s.length();
        }
        return res;
    }

}
