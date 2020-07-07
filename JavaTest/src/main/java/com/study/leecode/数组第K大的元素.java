package com.study.leecode;

import java.util.*;

public class 数组第K大的元素 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,10,7};
        int res = findK(arr, 2);
        System.out.println(res);
    }
    public static int findK(int[] arr,int k){


        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int i:arr){
            heap.add(i);
        }
        while (k-->1){
            heap.poll();
        }

        return heap.poll();


    }
}
