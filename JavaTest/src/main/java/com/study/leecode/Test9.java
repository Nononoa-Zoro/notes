package com.study.leecode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class Test9 {
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len/2; i++) {
                if(str.charAt(i)!= str.charAt(len-i-1))return false;
        }
        return true;
    }
}
