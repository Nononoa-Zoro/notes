package com.study.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定多个会议的开始和结束时间，现在只有一个会议室，问：最多可以安排多少个会议？
 * 思路：
 * 将所有的会议按照结束时间从小到大排列
 * 以第一个会议的结束时间作为参考标准，如果下一个会议的开始时间大于结束时间，则可以安排，同时更新结束时间
 */
public class 一个会议室最多安排多少个会议 {

    public static class Meeting{
        int start;
        int end;
        public Meeting(int start,int end){
            this.start=start;
            this.end=end;
        }
    }


    public static class MeetingComparator implements Comparator<Meeting>{
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end-o2.end;
        }
    }

    public static int solve(Meeting[] meetings){
        Arrays.sort(meetings,new MeetingComparator());
        int res =0;
        int end = meetings[0].end;
        for(int i=1;i<meetings.length;i++){
            if(meetings[i].start>end){
                res++;
                end=meetings[i].end;
            }
        }
        return res;
    }
}
