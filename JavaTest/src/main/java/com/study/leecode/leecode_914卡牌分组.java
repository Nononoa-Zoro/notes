package com.study.leecode;

public class leecode_914卡牌分组 {

    public boolean hasGroupsSizeX(int[] deck) {
        int[] counts = new int[10000];
        for (int card : deck) {
            counts[card]++;
        }
        int gcd = counts[deck[0]];
        for (int cnt : counts) {
            if (cnt != 0) {
                gcd = Gcd(cnt, gcd);
                if (gcd < 2) {
                    return false;
                }
            }
        }
        return true;

    }

    //求最小公约数
    public int Gcd(int a, int b) {
//        if (b == 0) {
//            return a;
//        }
//        return Gcd(b, a % b);
        return a % b == 0 ? b : Gcd(b, a % b);
    }


}
