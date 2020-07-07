package com.study.leecode;

import com.study.java8.Apple;

import java.util.ArrayList;
import java.util.List;

public class 将字符串中的字母大小写全排列 {

    public static void main(String[] args) {
        method("a1b2");
        System.out.println(res);
    }

    public static List<String> res = new ArrayList<>();




    // a1b2
    public static List<String> method(String s){
        if(s==null||s.length()==0)return new ArrayList<>();
        iterStr(s,new StringBuilder(""),0);
        return res;
    }

    public static void iterStr(String str,StringBuilder letter,int index){
        if(index==str.length()){
            res.add(letter.toString());
            return;
        }

        char c = str.charAt(index);

        iterStr(str,letter.append(c),index+1);

        if(Character.isLowerCase(c)){
            char c1 = Character.toUpperCase(c);
            iterStr(str,letter.append(c1),index+1);
        }

        if(Character.isUpperCase(c)){
            char c1 = Character.toLowerCase(c);
            iterStr(str,letter.append(c1),index+1);
        }
    }
}
