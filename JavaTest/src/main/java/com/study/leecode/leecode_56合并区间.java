package com.study.leecode;

import java.util.*;

public class leecode_56合并区间 {

    static class Interval {
        int start;
        int end;

        public Interval(int x, int y) {
            this.start = x;
            this.end = y;
        }

    }


    public static List<Interval> merge1(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        LinkedList<Interval> list = new LinkedList<>();

        for (Interval cur : intervals) {
            if (list.isEmpty() || list.getLast().end < cur.start) {
                list.add(cur);
            } else {
                list.getLast().end = Math.max(list.getLast().end, cur.end);
            }
        }
        return list;
    }

    public static int[][] merge(int[][] intervals) {

        LinkedList<Interval> list = new LinkedList<>();
        int row = intervals.length;
        int col = intervals[0].length;
        for (int i = 0; i < row; i++) {
            Interval interval = new Interval(intervals[i][0], intervals[i][1]);
            list.add(interval);
        }
        List<Interval> merged = merge1(list);
        int[][] arr = new int[merged.size()][2];
        for(int i=0;i<arr.length;i++){
                arr[i][0]=merged.get(i).start;
                arr[i][1]=merged.get(i).end;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        int[][] merge = merge(intervals);
        for(int[] row:merge){
            for(int i:row){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}
