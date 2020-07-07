package com.study.leecode;

import java.util.HashMap;

public class 罗马数字转为整数 {

    public static void main(String[] args) {
        int res = romanToInt("III");
        System.out.println(res);
    }

    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        //curr表示当前位置的罗马字符代表的整数值 last表示它的右边的位置表示的整数值
        int curr, last = 0, res = 0;
        for (int i = len - 1; i >= 0; i--) {
            curr = map.get(s.charAt(i));
            if (curr < last) {
                //左边小于右边 应该减
                res -= curr;
            } else {
                //左边大于右边 应该加
                res += curr;
            }
            //每次循环都要更新last
            last = curr;
        }
        return res;
    }

}
