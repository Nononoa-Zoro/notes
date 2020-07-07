package com.study.leecode;

import java.util.Arrays;

public class leecode_1189 {
    public int maxNumberOfBalloons(String text) {
        int[] arr = new int[5];
        for(char c:text.toCharArray()) {
            switch (c) {
                case 'b':
                    arr[0]++;
                    break;
                case 'a':
                    arr[1]++;
                    break;
                case 'l':
                    arr[2]++;
                    break;
                case 'o':
                    arr[3]++;
                    break;
                case 'n':
                    arr[4]++;
                    break;
            }
        }
            arr[2]/=2;
            arr[3]/=2;
            Arrays.sort(arr);
            return arr[0];
    }
}
