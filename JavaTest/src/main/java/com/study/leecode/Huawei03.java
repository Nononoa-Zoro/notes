package com.study.leecode;

import java.util.Scanner;

public class Huawei03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int lines = Integer.parseInt(s[0]);
        int[] values = new int[lines];
        int[][] arr = new int[lines][lines];
        for (int i = 0; i < lines; i++) {
            String[] str = scanner.nextLine().split(" ");
            values[i] = Integer.parseInt(str[1]);
            for (int j = 2; j < str.length; j++) {
                int col = Integer.parseInt(str[j]);
                arr[i][col - 1] = 1;
            }
        }

        dfs(arr,values,0,values[0]);
        System.out.println(res);
    }

    static int res = Integer.MIN_VALUE;

    public static void dfs(int[][] arr,int[] values,int cur,int count){
        if(hasNoLink(arr,cur)){
            res= Math.max(count,res);
            return;
        }

        for(int j=0;j<arr[cur].length;j++){
            if(arr[cur][j]==1){
                dfs(arr,values,j,count+values[j]);
            }
        }

    }

    public static boolean hasNoLink(int[][] arr,int i){
        for (int j = 0; j < arr[i].length; j++) {
           if(arr[i][j]!=0){
               return false;
           }
        }
        return true;
    }
}
