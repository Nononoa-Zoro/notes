package com.study.leecode;

public class leecode_686重复叠加字符串匹配 {

    public static void main(String[] args) {
        String A = "abcd",B="cdabcdab";
        int res = repeatedStringMatch(A, B);
        System.out.println(res);
    }

    public static int repeatedStringMatch(String A, String B) {
        StringBuilder str = new StringBuilder(A);
        int i=1;
        while(str.indexOf(B)<0){
            str.append(A);
            i++;
        }
        return i;
    }
}
