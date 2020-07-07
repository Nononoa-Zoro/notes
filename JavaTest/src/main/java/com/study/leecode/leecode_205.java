package com.study.leecode;

import java.util.HashMap;
import java.util.Map;

public class leecode_205 {

    public static void main(String[] args) {
        boolean res = isIsomorphic("egg", "add");
        System.out.println(res);
    }

    public static boolean isIsomorphic(String s, String t) {
        if(s.length()==0&&t.length()==0)return true;
        if(s==null&&t==null)return true;
        if(s.length()!=t.length())return false;
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for(int i=0;i<arr1.length;i++){
            Integer value =  map1.getOrDefault(arr1[i],null);
            if(value==null){
                map1.put(arr1[i],i);
            }
            Integer value2 = map2.getOrDefault(arr2[i], null);
            if(value2==null){
                map2.put(arr2[i],i);
            }
        }


        int[] a = new int[s.length()];
        int[] b = new int[t.length()];
        for(int i=0;i<arr1.length;i++){
            Integer value1 = map1.get(arr1[i]);
            Integer value2 = map2.get(arr2[i]);
            if(value1!=value2)return false;
        }
        return true;
    }
}
