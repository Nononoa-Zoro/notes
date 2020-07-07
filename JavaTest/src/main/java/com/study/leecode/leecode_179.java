package com.study.leecode;

import java.util.Arrays;
import java.util.Comparator;

public class leecode_179 {

    public static void main(String[] args) {
        int[] nums ={10,2};
        String res = largestNumber(nums);
        System.out.println(res);
    }

    public static String largestNumber(int[] nums) {

        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(str, (o1, o2) -> {
            //A.compareTo(B)
            //如果A大于B返回1，交换前后顺序
            //如果A小于B返回-1，不交换前后顺序
            return (o2 + o1).compareTo(o1 + o2);
        });

        StringBuilder res = new StringBuilder();
        if(str[0].equals("0"))return "0";
        for(String s:str){
            res.append(s);
        }
        return res.toString();

    }
}
