package com.study.leecode;

import java.util.Arrays;
import java.util.Stack;

public class 单调栈_左边或右边第一个比自己小 {

    //左边第一个比自己小 单调增
    public static int[] method1(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            //当前元素小于栈顶元素 打破规则
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }

            //弹出之后如果stack为空说明左边没有比自己小的元素 标志位 -1 表示左边没有比自己小的元素
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                //否则索引为i的元素 ， 左边第一个比自己小的元素就是stack.peek()所在的元素
                res[i] = arr[stack.peek()];
            }

            stack.push(i);
        }
        return res;
    }

    //右边第一个比自己小 单调增 从右到左
    public static int[] method2(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = arr[stack.peek()];
            }

            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 7, 1};
        int[] method = method1(arr);
        System.out.println("左边比自己小");
        System.out.println(Arrays.toString(method));
        System.out.println("右边比自己小");
        int[] method2 = method2(arr);
        System.out.println(Arrays.toString(method2));
    }
}
