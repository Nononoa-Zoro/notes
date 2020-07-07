package com.study.leecode;

public class leecode_1103分糖果2 {

    public static void main(String[] args) {
        int[] res = distributeCandies(60, 4);
        for(int i:res){
            System.out.println(i);
        }
    }
    public static int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int round = 1;
        int residue = candies;
        while(residue!=0){
            for(int i=0;i<num_people;i++){
                int x =(round-1)*num_people+(i+1);
                if(residue<x){
                    res[i]=res[i]+residue;
                    residue=0;
                }else{
                    res[i]=res[i]+x;
                    residue-=x;
                }
            }
            round++;
        }
        return res;
    }
}
