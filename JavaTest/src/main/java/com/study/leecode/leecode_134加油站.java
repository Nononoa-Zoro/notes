package com.study.leecode;

public class leecode_134加油站 {

    public static void main(String[] args) {
        int[] gas = {4,5,2,6,5,3};
        int[] cost = {3,2,7,3,2,9};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int len = gas.length;


        for (int i = 0; i < len; i++) {

            if (gas[i] >= cost[i]) {
                int start = i;
                int pre = start == 0 ? len - 1 : start - 1;
                int oil = gas[i];
                while (start != pre && oil >= cost[start]) {
                    oil = oil - cost[start] + gas[(start + 1) % len];
                    start = (start + 1) % len;
                }
                if (start == pre&&oil>=cost[start%len]) return (pre + 1) % len;
            }
        }
        return -1;
    }
}
