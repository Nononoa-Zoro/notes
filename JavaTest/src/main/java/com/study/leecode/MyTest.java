package com.study.leecode;

import java.util.Arrays;

public class MyTest {
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[] arr={1,1,1,1,1,2,2,2,1,1};

        Arrays.sort(arr);
        putOut(arr,0);
        System.out.println("MIN>>>>>"+min);

    }

    private static void putOut(int[] bucket, int number) {
        //无牌,结束
        if(isZore(bucket)){
            System.out.println(">>"+number);
            min = min<number?min:number;
            return;
        }
        //判断能否出连对
        for (int i = 0; i < bucket.length-2; i++) {
            if(bucket[i]>=2 && bucket[i+1]>=2 && bucket[i+2]>=2){
                //打出连对
                bucket[i]-=2;
                bucket[i+1]-=2;
                bucket[i+2]-=2;
                //继续出牌
                putOut(bucket,number+1);
                bucket[i]+=2;
                bucket[i+1]+=2;
                bucket[i+2]+=2;
            }else{
                break;
            }
        }
        //判断能否出顺子
        for (int i = 0; i < bucket.length-4; i++) {
            if(bucket[i]>=1 && bucket[i+1]>=1 && bucket[i+2]>=1 && bucket[i+3]>=1 && bucket[i+4]>=1){
                //打出顺子
                bucket[i]--;
                System.out.print(i+" ");
                bucket[i+1]--;
                System.out.print(i+1+" ");
                bucket[i+2]--;
                System.out.print(i+2+" ");
                bucket[i+3]--;
                System.out.print(i+3+" ");
                bucket[i+4]--;
                System.out.print(i+4+" ");
                System.out.print("|");
                //继续出牌
                putOut(bucket,number+1);
                bucket[i]++;
                bucket[i+1]++;
                bucket[i+2]++;
                bucket[i+3]++;
                bucket[i+4]++;
            }
        }
        //判断能否出对子
        for (int i = 0; i < bucket.length; i++) {
            if(bucket[i]>=2){
                //打出对子
                bucket[i]-=2;
                System.out.print(i+" "+i+" ");
                System.out.print("|");
                //继续出牌
                putOut(bucket,number+1);

                bucket[i]+=2;
            }
        }
        //最后出单个
        for (int i = 0; i < bucket.length; i++) {
            if(bucket[i]>=1){
                //打出单张
                bucket[i]--;
                System.out.print(i);
                System.out.print("|");
                //继续出牌
                putOut(bucket,number+1);
                bucket[i]++;

            }
        }
    }

    private static boolean isZore(int[] bucket){
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i]!=0){
                return false;
            }
        }
        return true;
    }

}
