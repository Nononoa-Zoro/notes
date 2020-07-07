package com.study.leecode;

import java.util.ArrayList;
import java.util.List;

public class ali3 {

    private static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        String s = "1??0?101";
        dfs(s,0, "");
        for(String str:res){
            System.out.println(str);
        }
    }

    public static void dfs(String str, int index, String cur) {
        if (index == str.length()) {
            res.add(cur);
            return;
        }

        char c = str.charAt(index);

        if(c=='?'){
            dfs(str,index+1,cur+"0");
            dfs(str,index+1,cur+"1");
        }else{
            dfs(str,index+1,cur+c);
        }

    }
}
