package com.study.leecode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class 单调栈_左边或右边第一个比自己大 {

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 5, 3, 2, 9, 7};
        System.out.println(Arrays.toString(arr));
        int[] method1 = method1(arr);
        System.out.println("右边第一个比自己大");
        System.out.println(Arrays.toString(method1));
        int[] method2 = method2(arr);
        System.out.println("左边第一个比自己大");
        System.out.println(Arrays.toString(method2));
    }


    //右边第一个比自己大
    public static int[] method1(int[] arr) {
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                res[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return res;
    }

    //左边第一个比自己大
    public static int[] method2(int[] arr) {
        int[] res = new int[arr.length];
        Arrays.fill(res,-1);
        Stack<Integer> stack = new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while (!stack.isEmpty()&&arr[i]>arr[stack.peek()]){
                res[stack.pop()]=arr[i];
            }
            stack.push(i);
        }
        return res;
    }
}
