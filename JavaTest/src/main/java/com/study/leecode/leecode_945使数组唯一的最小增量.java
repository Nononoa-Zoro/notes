package com.study.leecode;

import java.util.Arrays;

public class leecode_945使数组唯一的最小增量 {

    public int minIncrementForUnique(int[] A) {
        int len =A.length;
        if(len==0)return 0;
        Arrays.sort(A);

        int pre = A[0];
        int res = 0;
        for(int i=1;i<len;i++){

            if(A[i]==pre+1){
                pre=A[i];
            }else if(A[i]>pre+1){
                pre=A[i];
            }else{
                res+=pre+1-A[i];
                pre++;
            }
        }
        return res;
    }


}
