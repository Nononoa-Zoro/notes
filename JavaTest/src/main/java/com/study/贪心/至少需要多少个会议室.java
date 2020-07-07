package com.study.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目描述是这样的：给定一堆会议的起始和终止时间，问最少需要多少间会议室.
 * 比如输入为 [[0, 30],[5, 10],[15, 20]]，输出为 2，输入为 [[7,10],[2,4]]，输出为 1。
 */
public class 至少需要多少个会议室 {


    public static void main(String[] args) {
        Meeting[] meetings = new Meeting[3];
        meetings[0]=new Meeting(0,30);
        meetings[1]=new Meeting(5,10);
        meetings[2]=new Meeting(15,20);
        int res = minMeetingRomm(meetings);
        System.out.println(res);
    }


    public static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int minMeetingRomm(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        //按照会议开始时间排序
        Arrays.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.start - o2.start;
            }
        });

        //存放已经在使用的会议室的会议结束时间
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //初始化为第一个会议的结束时间
        queue.offer(meetings[0].end);

        for (int i = 1; i < meetings.length; i++) {
            //如果当前会议的开始时间大于已经在用的会议的最小结束时间 则表示当前会议可以复用一间会议室
            if (meetings[i].start > queue.peek()) {
                queue.poll();
            }
            //否则新增一间会议室
            queue.offer(meetings[i].end);
        }

        //最后只需要统计有多少在用的会议室
        return queue.size();
    }

}
