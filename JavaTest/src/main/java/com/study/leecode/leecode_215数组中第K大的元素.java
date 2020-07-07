package com.study.leecode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class leecode_215数组中第K大的元素 {


    static int index=2;

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 7, 9};

        int res = help(arr, 2);

        int res1 = bubbleSort(arr, 2);

        System.out.println(res);
        System.out.println(">>>>>>>>>>>>>>");
        System.out.println(res1);
        System.out.println(">>>>>>>>>>>>>>");
        int[] arr1 = {5, 3, 8, 7, 9};
        solve(arr1, 0, arr.length - 1);
        System.out.println(arr1[index-1]);
    }


    //冒泡找第K大
    //一次冒泡之后0-i是从小到大排列的
    //所以只需要冒泡k次
    public static int bubbleSort(int[] arr, int k) {
        boolean flag = true;
        for (int i = 0; i < arr.length && flag; i++) {
            flag = false;
            //一轮遍历确定一个元素的最终位置
            for (int j = arr.length - 2; j >= i; j--) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    swap(arr, j, j + 1);
                }
            }
            if (i == k - 1) return arr[i];
        }
        return -1;
    }


    //使用堆实现
    private static int help(int[] nums, int k) {

        //大根堆
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (Integer i : nums) {
            queue.add(i);
        }

        while (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }


    public static void solve(int[] arr,int left,int right){
        if(left<right){
            int privot = partition(arr,left,right);
            solve(arr,left,privot-1);
            solve(arr,privot+1,right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int privot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= privot) right--;
            swap(arr, left, right);
            while (left < right && arr[left] <= privot) left++;
            swap(arr, left, right);
        }
        return left;

    }

    private static void swap(int[] arr, int left, int right) {

        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
