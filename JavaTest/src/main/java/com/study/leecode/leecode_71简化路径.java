package com.study.leecode;

import java.util.*;

public class leecode_71简化路径 {
    public static void main(String[] args) {
        String res = simplifyPath("/home//foo/");
        System.out.println(res);
    }
    public static String simplifyPath(String path) {
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();
        for(int i=0;i<s.length;i++){
            if(!s[i].equals("")&&!s[i].equals(".")){
                stack.push(s[i]);
            }else if (!stack.isEmpty()&&s[i].equals("..")){
                stack.pop();
            }
        }
        if(stack.isEmpty())return "/";
        StringBuilder res = new StringBuilder();
        for(int i=0;i<stack.size();i++){
            res.append("/"+stack.get(i));
        }
        return res.toString();
    }
}
