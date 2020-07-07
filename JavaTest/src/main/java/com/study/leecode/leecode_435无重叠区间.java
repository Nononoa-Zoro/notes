package com.study.leecode;

import java.util.Arrays;
import java.util.Comparator;

public class leecode_435无重叠区间 {
    public static void main(String[] args) {
        int[][] arr = {{1,2},{2,3}};
        int res = eraseOverlapIntervals(arr);
        System.out.println(res);
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int count = 0;
        int x_end = intervals[0][1];

        for(int i=1;i<intervals.length;i++){
            int start = intervals[i][0];
            if(start>=x_end){
                x_end=intervals[i][1];
            }else{
                count++;
            }
        }

        return count;
    }
}
