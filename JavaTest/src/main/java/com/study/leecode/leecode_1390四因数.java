package com.study.leecode;

public class leecode_1390四因数 {


    public static void main(String[] args) {
        int[] arr = {10};
        int res = sumFourDivisors(arr);
        System.out.println(res);
    }

    public static int sumFourDivisors(int[] nums) {
        int res = 0;
        for (int a : nums) {
            res += method(a);
        }
        return res;
    }

    //求a的所有因数之和
    public static int method(int a) {
        int cnt = 0;
        int res = 0;
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (i * i == a) {
                return 0;
            }

            if (a % i == 0) {
                res += i + a / i;
                cnt += 2;
            }
        }

        if (cnt == 4) return res;
        else {
            return 0;
        }
    }
}
