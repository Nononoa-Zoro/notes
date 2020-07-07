package com.study.leecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 招商2 {

    public static class Item {
        int len;
        int weight;

        public Item(int len, int weight) {
            this.len = len;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        while (cases-- > 0) {
            int n = scanner.nextInt();
            int[] len_arr = new int[n];
            int[] weight_arr = new int[n];
            for (int i = 0; i < n; i++) {
                len_arr[i] = scanner.nextInt();
            }
            for (int i = 0; i < n; i++) {
                weight_arr[i] = scanner.nextInt();
            }

            Item[] items = new Item[n];
            for (int i = 0; i < items.length; i++) {
                items[i] = new Item(len_arr[i], weight_arr[i]);
            }

            int res = solve(items);
            System.out.println(res);
        }
    }

    public static int solve(Item[] items) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.len < o2.len) {
                    return -1;
                } else if (o1.len == o2.len) {
                    return o1.weight - o2.weight;
                } else {
                    return 1;
                }
            }
        });

        int cost1 = 1;
        for (int i = 1; i < items.length; i++) {
            if (items[i].weight < items[i-1].weight) {
                cost1++;
            }
        }

        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.weight < o2.weight) {
                    return -1;
                } else if (o1.weight == o2.weight) {
                    return o1.len - o2.len;
                } else {
                    return 1;
                }
            }
        });

        int cost2 = 1;
        for (int i = 1; i < items.length; i++) {
            if (items[i].len < items[i-1].len) {
                cost2++;
            }
        }

        return Math.min(cost1, cost2);

    }
}
