package com.study.leecode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 携程1 {

    public static class Phone {
        int start;
        int end;

        public Phone(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int calcMinStaff(Phone[] arr) {
        if(arr==null||arr.length==0)return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(arr[0].end);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(arr[i].end);
        }
        return queue.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Phone[] arr = new Phone[n];
        for (int i = 0; i < arr.length; i++) {
            String[] s = scanner.nextLine().split(",");
            Phone phone = new Phone(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            arr[i] = phone;
        }
        System.out.println(calcMinStaff(arr));

    }
}
