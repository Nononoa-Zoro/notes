package com.study.leecode;

import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.util.Stack;

public class leecode_84柱状图的最大矩阵面积 {

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr));
    }

    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];

        Stack<Integer> stack = new Stack<>();
        //左边第一个比自己小
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = -1;//不存在
            } else {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) stack.pop();
        //右边第一个比自己小
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = len;//不存在
            } else {
                right[i] = stack.peek();
            }
            stack.push(i);
        }

        int area = 0;
        for (int i = 0; i < len; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }
}
