package com.study.zuoshen;

public class 递归求前n个数字之和 {
    public static int f(int n){
        if(n==1)return 1;
        else{
            return f(n-1)+n;
        }
    }

    public static void main(String[] args) {
        int res = f(100);
        System.out.println(res);
    }
}
