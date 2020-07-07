package com.study.leecode;

import java.util.*;

/**
 * 最长上升子序列问题
 */
public class ali2 {
    static class Data {
        int start;
        int end;
        int length;

        public Data(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        scanner.nextLine();

        List<Data> list = new ArrayList<>();

        while (len-- > 0) {
            String s = scanner.nextLine();
            int start = s.charAt(0) - 'a';
            int end = s.charAt(s.length() - 1) - 'a';
            list.add(new Data(start, end, s.length()));
        }

        Collections.sort(list, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.start-o2.start;
            }
        });

        int res = 0;
        int[] dp = new int[list.size()];

        //dp[i] 表示以当前元素结尾的
        dp[0] = list.get(0).length;
        for (int i = 1; i < list.size(); i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (list.get(i).start>=list.get(j).end) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + list.get(i).length;
            res = Math.max(max, dp[i]);
        }

        System.out.println(res);


    }





}
